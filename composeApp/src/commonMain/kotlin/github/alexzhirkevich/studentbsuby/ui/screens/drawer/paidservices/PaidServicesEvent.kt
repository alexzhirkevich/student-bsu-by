package github.alexzhirkevich.studentbsuby.ui.screens.drawer.paidservices

import github.alexzhirkevich.studentbsuby.util.Event

sealed interface PaidServicesEvent : Event {
    data object UpdateRequested : PaidServicesEvent
    data object EripHelpClicked : PaidServicesEvent
}
