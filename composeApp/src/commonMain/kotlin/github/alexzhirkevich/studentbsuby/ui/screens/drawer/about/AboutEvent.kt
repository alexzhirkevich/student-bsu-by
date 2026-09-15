package github.alexzhirkevich.studentbsuby.ui.screens.drawer.about

import github.alexzhirkevich.studentbsuby.util.Event

sealed interface AboutEvent : Event {
    data object EmailClicked : AboutEvent
    data object TgClicked : AboutEvent
}
