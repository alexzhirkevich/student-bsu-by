package github.alexzhirkevich.studentbsuby.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.russhwolf.settings.NSUserDefaultsSettings
import platform.Foundation.NSUserDefaults

@Composable
actual fun rememberThemeSelector() : ThemeSelector {
    return remember {
        ThemeSelectorImpl.create(
            NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults)
        )
    }
}
