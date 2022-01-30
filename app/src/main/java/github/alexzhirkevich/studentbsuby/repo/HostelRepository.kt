package github.alexzhirkevich.studentbsuby.repo

import android.content.SharedPreferences
import androidx.annotation.IntRange
import github.alexzhirkevich.studentbsuby.api.ProfileApi
import github.alexzhirkevich.studentbsuby.api.isSessionExpired
import github.alexzhirkevich.studentbsuby.dao.HostelDao
import github.alexzhirkevich.studentbsuby.data.models.HostelAdvert
import github.alexzhirkevich.studentbsuby.util.exceptions.EmptyResponseException
import github.alexzhirkevich.studentbsuby.util.exceptions.FailResponseException
import github.alexzhirkevich.studentbsuby.util.exceptions.IncorrectResponseException
import github.alexzhirkevich.studentbsuby.util.exceptions.SessionExpiredException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.jsoup.Jsoup
import javax.inject.Inject

sealed interface HostelState{
    class Provided(val address : String) : HostelState
    class NotProvided(val adverts : List<HostelAdvert>) : HostelState
}

private const val PREF_HOSTEL_ADDRESS_ = "PREF_HOSTEL_ADDRESS_"

class HostelRepository @Inject constructor(
    private val loginRepository: LoginRepository,
    private val profileApi : ProfileApi,
    private val hostelDao : HostelDao,
    private val preferences : SharedPreferences
) {

    private val images = listOf("" +
            "https://studgorodok.bsu.by/images/ZdUhc72c4d4.jpg",
            "https://studgorodok.bsu.by/images/9RXRr6UQh8w.jpg",
            "https://studgorodok.bsu.by/images/JzLRN6EAwGo.jpg",
            "https://studgorodok.bsu.by/images/c_FM8MeHBZU.jpg",
            "https://studgorodok.bsu.by/images/0e2mZTHEqVo.jpg",
            "https://studgorodok.bsu.by/images/oAbSJkvHW6E.jpg",
            "https://studgorodok.bsu.by/images/hAoV62hpZow.jpg",
            "https://studgorodok.bsu.by/images/yZOc-8py8XQ.jpg",
            "https://www.pac.by/upload/iblock/847/2.JPG",
            "https://studgorodok.bsu.by/images/W7zroZuO5Hk.jpg",
            "https://studgorodok.bsu.by/images/J9Wb61gXXSY.jpg"
    )

    fun getMapAddress(address: String) =
        "Минск, " + address.substringBefore(',')


    @IntRange(from = 0, to = 11)
    fun getHostelNumber(address: String) : Int{
        return address.split(" ",",").let {
            val i = it.indexOf("Общежитие")
            if (i != -1 && it.size>=i){
                it[i+1].toIntOrNull() ?: 0
            } else 0
        }
    }

    fun getImageForHostel(@IntRange(from = 0, to = 11) number : Int) : String {
        return if (number-1 == 0 || number-1 !in images.indices)
            "" else images[number-1]
    }

    fun getHostelState() : Flow<HostelState> = flow {
        val cached = getFromCache().getOrNull()?.also {
            emit(it)
        }
        getFromWeb().let {
            emit(it)
            if (cached != it) {
                saveToCache(it)
            }
        }
    }


    private suspend fun getFromWeb() : HostelState{
        val resp = profileApi.hostel()
        if (!resp.isSuccessful)
            throw FailResponseException(resp.code())

        if (resp.body()?.isSessionExpired == true)
            throw SessionExpiredException()

        val bytes = resp.body()?.byteStream()?.readBytes()
            ?: throw EmptyResponseException()

        val jsoup = Jsoup.parse(String(bytes))

        jsoup.getElementById("ctl00_ctl00_ContentPlaceHolder0_ContentPlaceHolder1_lbHouse2")
            ?.text()?.let {
                return HostelState.Provided(it)
            }

        val table =    jsoup
            .getElementById("ctl00_ctl00_ContentPlaceHolder0_ContentPlaceHolder1_GridView1")
        val numbers = table.getElementsByAttributeValue("align","center")
            ?.filter { it.hasAttr("nowrap") }
            ?.map { it.text() }

        val other = table.getElementsByAttributeValue("align","left")
            ?.filter { !it.hasAttr("scope") }
            ?.map { it.text() }
            ?.chunked(8)

        println(other)

        val ads = other
            ?.mapIndexedNotNull { index, it ->
                if (it.size == 8)
                    HostelAdvert(
                        number = numbers?.getOrNull(index)?.toIntOrNull() ?: 0,
                        phone = it[0].takeIf { "nbsp" !in it && it.isNotBlank() },
                        publisher = it[1].takeIf { "nbsp" !in it && it.isNotBlank()},
                        address = it[2].takeIf { "nbsp" !in it && it.isNotBlank()},
                        conditions = it[3].takeIf { "nbsp" !in it && it.isNotBlank()},
                        price = it[4].takeIf { "nbsp" !in it && it.isNotBlank()},
                        currency = it[5].takeIf { "nbsp" !in it && it.isNotBlank()},
                        publishDate = it[6].takeIf { "nbsp" !in it && it.isNotBlank()} ?: "",
                        note = it[7].takeIf { "nbsp" !in it && it.isNotBlank()},
                    )
                else null
            } ?: throw IncorrectResponseException()

        return HostelState.NotProvided(ads)
    }

    private suspend fun saveToCache(hostelState: HostelState)= kotlin.runCatching {
        val username = loginRepository.cachedLogin.takeIf(String::isNotBlank)
            ?: return@runCatching
        when (hostelState) {
            is HostelState.Provided ->
                preferences.edit()
                    .putString(PREF_HOSTEL_ADDRESS_ + username, hostelState.address)
                    .apply()
            is HostelState.NotProvided ->
                with(hostelDao) {
                    clear()
                    hostelState.adverts.forEach {
                        insert(it)
                    }
                }
        }
    }

    private suspend fun getFromCache()  = kotlin.runCatching {
        loginRepository.cachedLogin.takeIf(String::isNotBlank)?.let {
            val providedAddress = preferences.getString(PREF_HOSTEL_ADDRESS_ + it, "")
            if (!providedAddress.isNullOrBlank()) {
                HostelState.Provided(providedAddress)
            } else {
                hostelDao.getAll().takeIf(Collection<*>::isNotEmpty)?.let {
                    HostelState.NotProvided(it)
                }
            }
        }
    }
}