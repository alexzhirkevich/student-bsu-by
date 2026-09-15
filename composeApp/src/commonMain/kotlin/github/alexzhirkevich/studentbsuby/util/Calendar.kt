package github.alexzhirkevich.studentbsuby.util

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

interface Calendar {
    val dayOfMonth : Int
    val dayOfWeek: Int
    val month : Int
    val year : Int
    fun time() : String

    class Base : Calendar {
        override val dayOfMonth = now().day

        override val dayOfWeek = when (now().dayOfWeek) {
            DayOfWeek.MONDAY -> 0
            DayOfWeek.TUESDAY -> 1
            DayOfWeek.WEDNESDAY -> 2
            DayOfWeek.THURSDAY -> 3
            DayOfWeek.FRIDAY -> 4
            DayOfWeek.SATURDAY -> 5
            else -> 6
        }

        override val month = now().month.ordinal

        override val year = now().year

        override fun time(): String = now().let {
                "${it.hour.let { if (it>9) it else "0$it" }}:" +
                        "${it.minute.let { if (it>9) it else "0$it" }}"
        }
    }
}

@OptIn(ExperimentalTime::class)
private fun now(): LocalDateTime =
    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
