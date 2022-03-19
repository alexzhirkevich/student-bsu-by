package github.alexzhirkevich.studentbsuby.repo

import androidx.annotation.IntRange
import github.alexzhirkevich.studentbsuby.api.TimetableApi
import github.alexzhirkevich.studentbsuby.api.await
import github.alexzhirkevich.studentbsuby.api.dayOfWeek
import github.alexzhirkevich.studentbsuby.dao.LessonsDao
import github.alexzhirkevich.studentbsuby.data.models.*
import github.alexzhirkevich.studentbsuby.util.exceptions.UsernameNotFoundException
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.toImmutableList
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.jsoup.Jsoup
import java.io.InputStream
import javax.inject.Inject
import kotlin.math.roundToInt


class TimetableRepository @Inject constructor(
    private val usernameProvider: UsernameProvider,
    private val userRepository: UserRepository,
    private val timetableApi: TimetableApi,
    private val lessonsDao : LessonsDao,
    private val httpClient: OkHttpClient
) : CacheWebRepository<List<List<Lesson>>>() {

    override suspend fun getFromWeb(): List<List<Lesson>> {
        val username = usernameProvider.username.takeIf(String::isNotBlank)
            ?: throw UsernameNotFoundException()
        // TODO: refactor
//        return coroutineScope {
//            (0..6).map { day ->
//                async {
//
//                    val string = timetableApi.timetable(timetableApi.dayOfWeek(day)).html()
//                    val html =
//                        '<' + string.substringAfter('<').substringBeforeLast('>') + '>'
//                    val jsoup = Jsoup.parse(html)
//
//                    val numbers = jsoup.getElementsByClass("styleNumber").mapNotNull {
//                        it.text().toIntOrNull() to (it.attr("rowspan").toIntOrNull() ?: 1)
//                    }
//                    val timesStart = jsoup.getElementsByClass("styleTimeIn").map {
//                        it.text().orEmpty()
//                    }
//                    val timesEnd = jsoup.getElementsByClass("styleTimeOut").map {
//                        it.text().orEmpty()
//                    }
//                    val names = jsoup.getElementsByClass("styleLesson").map {
//                        it.text().orEmpty() to (it.attr("rowspan").toIntOrNull() ?: 1)
//                    }
//                    val types = jsoup.getElementsByClass("styleKindLesson").map {
//                        it.text().orEmpty() to (it.attr("rowspan").toIntOrNull() ?: 1)
//                    }
//                    val audiences = jsoup.getElementsByClass("styleHall").map {
//                        it.text().orEmpty() to (it.attr("rowspan").toIntOrNull() ?: 1)
//                    }
//                    val teachers = jsoup.getElementsByClass("styleTeacher").map {
//                        it.text().orEmpty() to (it.attr("rowspan").toIntOrNull() ?: 1)
//                    }
//
//                    val spans = numbers.map { it.second }
//
//                    val chunkedNames = chunk(spans, names).map {
//                        it.joinToString(separator = " \\ ")
//                    }
//                    val chunkedTypes = chunk(spans, types).map {
//                        it.joinToString(separator = " \\ ")
//                    }
//                    val chunkedAudiences = chunk(spans, audiences).map {
//                        it.joinToString(separator = " \\ ")
//                    }
//                    val chunkedTeachers = chunk(spans, teachers).map {
//                        it.joinToString(separator = " \\ ")
//                    }
//
//                    kotlin.runCatching {
//                        numbers.indices.mapNotNull {
//                            if (it in chunkedNames.indices && chunkedNames[it].isNotEmpty()) {
//                                Lesson(
//                                    id = 0,
//                                    owner = username,
//                                    dayOfWeek = day,
//                                    number = if (it in numbers.indices)
//                                        numbers[it].first ?: 0 else 0,
//                                    name = chunkedNames[it],
//                                    place = if (it in chunkedAudiences.indices)
//                                        chunkedAudiences[it] else "",
//                                    type = if (it in chunkedTypes.indices)
//                                        chunkedTypes[it] else "",
//                                    teacher = if (it in chunkedTeachers.indices)
//                                        chunkedTeachers[it] else "",
//                                    starts = if (it in timesStart.indices)
//                                        timesStart[it] else "",
//                                    ends = if (it in timesEnd.indices)
//                                        timesEnd[it] else ""
//                                )
//                            } else null
//                        }
//                    }.getOrNull() ?: emptyList()
//                }
//            }.awaitAll().take(6).let {
//                if (it.size == 6) it else it.toMutableList().apply {
//                    repeat(6 - it.size) {
//                        add(emptyList())
//                    }
//                    it.toImmutableList()
//                }
//            }
//        }.takeIf(Collection<*>::isEmpty)?.let {
//            FacultyTimetableProvider.forUser(userRepository.getFromWeb(), httpClient).get()
//        }.orEmpty()
        return FacultyTimetableProvider.forUser(userRepository.getFromWeb(), httpClient).get()
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

    override suspend fun getFromCache(): List<List<Lesson>>{
        return kotlin.runCatching {
            usernameProvider.username.takeIf(String::isNotBlank)?.let { username ->
                lessonsDao.getAll(username)
            }?.groupBy { it.dayOfWeek }?.values?.toList()
        }.getOrNull() ?: emptyList()
    }

    override suspend fun saveToCache(value : List<List<Lesson>> ){
        kotlin.runCatching {
            usernameProvider.username.takeIf(String::isNotBlank)?.let { username ->
                lessonsDao.clear(username)
                value.flatten().forEach {
                    lessonsDao.insert(it)
                }
            }
        }
    }
}

sealed class FacultyTimetableProvider(
    protected val user: User,
    protected val httpClient: OkHttpClient
    ) {

    abstract suspend fun get(): List<List<Lesson>>

    companion object {
        fun forUser(user: User, httpClient: OkHttpClient): FacultyTimetableProvider =
            when (user.faculty) {
                FpmTimetableProvider.NAME -> FpmTimetableProvider(user, httpClient)
                else -> throw IllegalArgumentException("Unknown faculty: ${user.faculty}")
            }
    }
}

class FpmTimetableProvider(user: User, client: OkHttpClient)
    : FacultyTimetableProvider(user,client) {

    override suspend fun get(): List<List<Lesson>> {
        return user.course?.let { download(it) }.orEmpty()
    }

    private suspend fun download(@IntRange(from = 1, to = 4) course: Int): List<List<Lesson>> {
        val link = when (course) {
            1 -> "http://fpmi.bsu.by/sm_full.aspx?guid=58073"
            2 -> "http://fpmi.bsu.by/sm_full.aspx?guid=58083"
            3 -> "http://fpmi.bsu.by/sm_full.aspx?guid=58093"
            4 -> "http://fpmi.bsu.by/sm_full.aspx?guid=61023"
            else -> throw IllegalArgumentException("Invalid course: $course")
        }
        return httpClient.newCall(Request.Builder()
            .url(link).get().build()
        ).await().body?.byteStream()?.let { parse(it,user.group) }.orEmpty()
    }

    private fun parse(stream: InputStream, group : String): List<List<Lesson>> {
        return kotlin.runCatching {
            val sheet = XSSFWorkbook(stream).getSheetAt(0)
            val columnIndex = sheet.first { it.any{it.toString().endsWith("группа")} }.indexOfFirst {
                it.toString().removeSuffix("группа").trim() == group
            }
            if (columnIndex == -1)
                return@runCatching emptyList()

            val table = sheet.asIterable()
                .map {
                    it.map { it.toString().trim() }.toMutableList()
                }
            with(sheet.mergedRegions) {
                for (i in table.indices) {
                    for (j in table[i].indices) {
                        kotlin.runCatching {
                            find { it.isInRange(i, j) }?.let { range ->
                                table[i][j] = table.subList(range.firstRow, range.lastRow + 1)
                                    .map { it.subList(range.firstColumn, range.lastColumn + 1) }
                                    .flatten()
                                    .find(String::isNotBlank).orEmpty()
                            }
                        }
                    }
                }
                forEach { range ->
                    kotlin.runCatching {
                        if (range.lastRow + 1 !in table.indices)
                            return@forEach
                        with(table[range.lastRow + 1]) {
                            val reg = subList(range.firstColumn, range.lastColumn + 1)
                                .filter(String::isNotBlank)
                            if (reg.size == 1) {
                                (range.firstColumn..range.lastColumn).forEach {
                                    this[it] = reg.first()
                                }
                            }
                        }
                    }
                }
            }

            val weekdays =
                listOf("понедельник", "вторник", "среда", "четверг", "пятница", "суббота")
            val tableByWeekdaysAndTime = table
                .dropWhile {
                    kotlin.runCatching { '-' !in it[1] }.getOrElse { true }
                } //drop while row doesn't contains timetable
                .groupBy { it[0] } //group rows by day of week
                .mapValues {
                    it.value
                        .groupBy { it[1] } //group rows by time
                        .mapValues { // take 2 columns for group
                            it.value
                                .takeWhile { it.size > columnIndex + 1 }
                                .map {
                                        val a = it[columnIndex]
                                        val b = it[columnIndex + 1]
                                        if (a == b) a else "$a\\$b"
                                }
                        }.mapNotNull { entry ->
                            entry.value.takeIf { it.any(String::isNotBlank) }?.let {
                                entry.key to entry.value
                            }
                        }.toMap()
                }.toMutableMap()

            with(tableByWeekdaysAndTime) {
                keys.filterNot { it in weekdays }.forEach(this::remove)
            }
            println(tableByWeekdaysAndTime)
            return@runCatching tableByWeekdaysAndTime.map { kv ->
                var i = 0
                kv.value.mapNotNull {
                    if (it.value.first().isBlank())
                        null
                    else {
                        val (start, end) = it.key.split('-')
                        Lesson(
                            owner = user.username,
                            dayOfWeek = weekdays.indexOf(kv.key),
                            number = i++,
                            name = it.value.first(),
                            type = "",
                            teacher = kotlin.runCatching { when (it.value.size) {
                                4 -> it.value[2]
                                else -> it.value[3]
                            }}.getOrElse { "" },
                            place = it.value.last().let {
                                it.toFloatOrNull()?.toInt()?.toString() ?: it
                            },
                            starts = start,
                            ends = end
                        )
                    }
                }
            }
        }.getOrElse {
            throw Exception("Failed to parse timetable", it)
        }
    }

    companion object {
        const val NAME = "Факультет прикладной математики и информатики"
    }
}