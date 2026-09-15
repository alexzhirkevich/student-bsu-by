package github.alexzhirkevich.studentbsuby.services.firebase

class RemoteConfigClientImpl : RemoteConfigClient {

    override suspend fun fetchAndActivate(): Boolean = false

    override fun getString(key: String): String = ""
}
