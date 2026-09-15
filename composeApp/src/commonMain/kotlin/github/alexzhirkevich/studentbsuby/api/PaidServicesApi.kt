package github.alexzhirkevich.studentbsuby.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse

interface PaidServicesApi {

    suspend fun info() : HttpResponse

    suspend fun tuitionFee() : HttpResponse

    suspend fun academicDebt() : HttpResponse

    suspend fun hostelBills() : HttpResponse

    suspend fun common() : HttpResponse
}

class PaidServicesApiImpl(private val client : HttpClient) : PaidServicesApi {

    override suspend fun info(): HttpResponse =
        client.get("PersonalCabinet/Pay/Info")

    override suspend fun tuitionFee(): HttpResponse =
        client.get("PersonalCabinet/Pay/report") {
            header("User-Agent", "Mozilla")
        }

    override suspend fun academicDebt(): HttpResponse =
        client.get("PersonalCabinet/Pay/reportAkadem") {
            header("User-Agent", "Mozilla")
        }

    override suspend fun hostelBills(): HttpResponse =
        client.get("PersonalCabinet/Pay/HousePay") {
            header("User-Agent", "Mozilla")
        }

    override suspend fun common(): HttpResponse =
        client.get("PersonalCabinet/Pay/reportOther") {
            header("User-Agent", "Mozilla")
        }
}
