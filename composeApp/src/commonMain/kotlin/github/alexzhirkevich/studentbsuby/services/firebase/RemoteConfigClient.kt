package github.alexzhirkevich.studentbsuby.services.firebase

interface RemoteConfigClient {

    /**
     * @return false if config could not be fetched and activated
     */
    suspend fun fetchAndActivate(): Boolean

    fun getString(key: String): String
}
