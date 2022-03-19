package github.alexzhirkevich.studentbsuby.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val username : String = "",
    val name : String = "",
    val faculty : String = "",
    val info : String = "",
    val avgGrade : String ="",
)

val User.course : Int?
    get() = info[0].digitToIntOrNull()

val User.group : String
get() = info.substringAfter("группа ")
    .substringBefore(',')