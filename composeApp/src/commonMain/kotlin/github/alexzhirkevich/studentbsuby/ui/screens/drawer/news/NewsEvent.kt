package github.alexzhirkevich.studentbsuby.ui.screens.drawer.news

import github.alexzhirkevich.studentbsuby.util.Event

sealed interface NewsEvent : Event {
    data object UpdateRequested : NewsEvent
}
