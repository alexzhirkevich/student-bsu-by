package github.alexzhirkevich.studentbsuby

import android.app.Activity
import github.alexzhirkevich.studentbsuby.util.Event

sealed interface MainActivityEvent : Event {
    class TestForAppUpdate(val activity: Activity) : MainActivityEvent

    class ExitClicked(val activity: Activity) : MainActivityEvent
    class UpdateClicked(val activity: Activity) : MainActivityEvent
}