package github.alexzhirkevich.studentbsuby.util

import org.jetbrains.compose.resources.StringResource

interface ResourceManager {

    suspend fun getString(res: StringResource): String
    suspend fun getString(res: StringResource, vararg args: Any): String

    class Base : ResourceManager {

        override suspend fun getString(res: StringResource): String =
            org.jetbrains.compose.resources.getString(res)

        override suspend fun getString(res: StringResource, vararg args: Any): String =
            org.jetbrains.compose.resources.getString(res, *args)
    }
}
