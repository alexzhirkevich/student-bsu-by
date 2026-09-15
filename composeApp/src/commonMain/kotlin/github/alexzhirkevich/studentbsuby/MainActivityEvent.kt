package github.alexzhirkevich.studentbsuby

import github.alexzhirkevich.studentbsuby.util.Event

sealed interface MainActivityEvent : Event {
    object Initialized : MainActivityEvent

    object ExitClicked : MainActivityEvent
    object UpdateClicked : MainActivityEvent
}
