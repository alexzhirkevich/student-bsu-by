package github.alexzhirkevich.studentbsuby.ui.theme

import android.app.Activity
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
internal actual fun SystemBarAppearance(isDark: Boolean) {

    val activity = LocalContext.current as Activity

    LaunchedEffect(isDark) {
        activity.window.apply {
          //  navigationBarColor = ContextCompat.getColor(activity, navBarColor)
            decorView.systemUiVisibility = if (!isDark)
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            else 0
//            statusBarColor = android.graphics.Color.WHITE
        }
    }
}
