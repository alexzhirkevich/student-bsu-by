package github.alexzhirkevich.studentbsuby.ui.theme

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.russhwolf.settings.SharedPreferencesSettings

@Composable
actual fun rememberThemeSelector() : ThemeSelector {
    val context = LocalContext.current
    return remember {
        ThemeSelectorImpl.create(
            SharedPreferencesSettings(
                context.getSharedPreferences(
                    "${context.packageName}_preferences",
                    Context.MODE_PRIVATE
                )
            )
        )
    }
}
