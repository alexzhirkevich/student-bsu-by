package github.alexzhirkevich.studentbsuby.services.store

interface UpdateLauncher {

    suspend fun tryUpdate(
        immediate : Boolean,
        onFailedToInAppUpdate : () -> Unit,
    )
}
