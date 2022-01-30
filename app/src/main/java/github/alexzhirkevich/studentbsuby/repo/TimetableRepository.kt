package github.alexzhirkevich.studentbsuby.repo

import github.alexzhirkevich.studentbsuby.api.TimetableApi
import github.alexzhirkevich.studentbsuby.api.dayOfWeek
import github.alexzhirkevich.studentbsuby.api.isSessionExpired
import github.alexzhirkevich.studentbsuby.dao.LessonsDao
import github.alexzhirkevich.studentbsuby.data.models.Lesson
import github.alexzhirkevich.studentbsuby.util.exceptions.FailResponseException
import github.alexzhirkevich.studentbsuby.util.exceptions.SessionExpiredException
import github.alexzhirkevich.studentbsuby.util.exceptions.UsernameNotFoundException
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import okhttp3.internal.toImmutableList
import org.jsoup.Jsoup
import javax.inject.Inject


class TimetableRepository @Inject constructor(
    private val loginRepository: LoginRepository,
    private val timetableApi: TimetableApi,
    private val lessonsDao : LessonsDao
) {

    @FlowPreview
    fun getTimetable(fromWebOnly : Boolean = false) : Flow<List<List<Lesson>>> = flow {
        val cached = if (!fromWebOnly) getTimetableFromCache().also {
            if (it.any(Collection<*>::isNotEmpty))
                emit(it)
        } else null

        emit(getTimetableFromWeb().also {
            if (cached != it && it.any(Collection<*>::isNotEmpty))
                saveTimetableToCache(it)
        })
    }

    private suspend fun getTimetableFromWeb(): List<List<Lesson>> {

        val username = loginRepository.cachedLogin.takeIf(String::isNotBlank)
            ?: throw UsernameNotFoundException()

        return coroutineScope {
            (0..6).map { day ->
                async {
                    val resp = timetableApi.timetable(timetableApi.dayOfWeek(day))
                    if (!resp.isSuccessful)
                        throw FailResponseException(resp.code())

                    if (resp.body()?.isSessionExpired == true)
                        throw SessionExpiredException()

                    val bytes = resp.body()?.byteStream()?.readBytes() ?: return@async emptyList()
                    val html =
                        '<' + String(bytes).substringAfter('<').substringBeforeLast('>') + '>'
                    val jsoup = Jsoup.parse(html)

                    val numbers = jsoup.getElementsByClass("styleNumber").mapNotNull {
                        it.text().toIntOrNull() to (it.attr("rowspan").toIntOrNull() ?: 1)
                    }
                    val timesStart = jsoup.getElementsByClass("styleTimeIn").map {
                        it.text().orEmpty()
                    }
                    val timesEnd = jsoup.getElementsByClass("styleTimeOut").map {
                        it.text().orEmpty()
                    }
                    val names = jsoup.getElementsByClass("styleLesson").map {
                        it.text().orEmpty() to (it.attr("rowspan").toIntOrNull() ?: 1)
                    }
                    val types = jsoup.getElementsByClass("styleKindLesson").map {
                        it.text().orEmpty() to (it.attr("rowspan").toIntOrNull() ?: 1)
                    }
                    val audiences = jsoup.getElementsByClass("styleHall").map {
                        it.text().orEmpty() to (it.attr("rowspan").toIntOrNull() ?: 1)
                    }
                    val teachers = jsoup.getElementsByClass("styleTeacher").map {
                        it.text().orEmpty() to (it.attr("rowspan").toIntOrNull() ?: 1)
                    }

                    val spans = numbers.map { it.second }

                    val chunkedNames = chunk(spans, names).map {
                        it.joinToString(separator = " \\ ")
                    }
                    val chunkedTypes = chunk(spans, types).map {
                        it.joinToString(separator = " \\ ")
                    }
                    val chunkedAudiences = chunk(spans, audiences).map {
                        it.joinToString(separator = " \\ ")
                    }
                    val chunkedTeachers = chunk(spans, teachers).map {
                        it.joinToString(separator = " \\ ")
                    }

                    kotlin.runCatching {
                        numbers.indices.mapNotNull {
                            if (it in chunkedNames.indices && chunkedNames[it].isNotEmpty()) {
                                Lesson(
                                    id = 0,
                                    owner = username,
                                    dayOfWeek = day,
                                    number = if (it in numbers.indices)
                                        numbers[it].first ?: 0 else 0,
                                    name = chunkedNames[it],
                                    place = if (it in chunkedAudiences.indices)
                                        chunkedAudiences[it] else "",
                                    type = if (it in chunkedTypes.indices)
                                        chunkedTypes[it] else "",
                                    teacher = if (it in chunkedTeachers.indices)
                                        chunkedTeachers[it] else "",
                                    starts = if (it in timesStart.indices)
                                        timesStart[it] else "",
                                    ends = if (it in timesEnd.indices)
                                        timesEnd[it] else ""
                                )
                            } else null
                        }
                    }.getOrNull() ?: emptyList()
                }
            }.awaitAll().take(6).let {
                if (it.size == 6) it else it.toMutableList().apply {
                    repeat(6 - it.size) {
                        add(emptyList())
                    }
                    it.toImmutableList()
                }
            }
        }
    }

    private fun <T> chunk(spans : List<Int>, values : List<Pair<T,Int>>) : List<List<T>> {
        val mutableValues = values.toMutableList()
        return spans.map { chunkSize ->
            var currentChunkSize = 0
            val currentList = mutableListOf<T>()
            while (currentChunkSize<chunkSize && mutableValues.isNotEmpty()){
                val item = mutableValues.removeFirst()
                val diff = chunkSize - (currentChunkSize+item.second)
                if (diff<0){
                    mutableValues.add(0,item.first to -diff)
                }
                currentChunkSize += item.second
                currentList.add(item.first)
            }
            currentList
        }
    }

    private suspend fun getTimetableFromCache(): List<List<Lesson>>{
        return kotlin.runCatching {
            loginRepository.cachedLogin.takeIf(String::isNotBlank)?.let { username ->
                lessonsDao.getAll(username)
            }?.groupBy { it.dayOfWeek }?.values?.toList()
        }.getOrNull() ?: emptyList()
    }

    private suspend fun saveTimetableToCache(timetable : List<List<Lesson>> ){
        kotlin.runCatching {
            loginRepository.cachedLogin.takeIf(String::isNotBlank)?.let { username ->
                lessonsDao.clear(username)
                timetable.flatten().forEach {
                    lessonsDao.insert(it)
                }
            }
        }
    }
}