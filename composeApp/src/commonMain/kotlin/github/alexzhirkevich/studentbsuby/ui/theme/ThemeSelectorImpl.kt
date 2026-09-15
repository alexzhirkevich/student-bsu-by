package github.alexzhirkevich.studentbsuby.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.russhwolf.settings.ObservableSettings
import github.alexzhirkevich.studentbsuby.ui.theme.Theme.Dark

private const val PREF_SETTINGS_isInSystemTheme = "PREF_SETTINGS_isInSystemTheme"
private const val PREF_SETTINGS_isInDarkThemeForced = "PREF_SETTINGS_isInDarkThemeForced"

class ThemeSelectorImpl private constructor(
    private val preferences: ObservableSettings,
    initialTheme : Theme
    ) : ThemeSelector{

    override val currentTheme = mutableStateOf(initialTheme)


    override fun setTheme(theme: Theme) {
        setInSystemTheme(preferences, theme == Theme.System)
        currentTheme.value = theme

        if (theme == Dark){
            setInDarkTheme(preferences,true)
        }
        if (theme == Theme.Light){
            setInDarkTheme(preferences,false)
        }
    }

    companion object {

        fun create(preferences: ObservableSettings) : ThemeSelectorImpl {
            val inSystem = isInSystemTheme(preferences)
            val theme = when {
                inSystem -> Theme.System
                preferences.getBoolean(PREF_SETTINGS_isInDarkThemeForced, false) -> Dark
                else -> Theme.Light
            }
            return ThemeSelectorImpl(preferences, theme)
        }

        private fun isInSystemTheme(prefs: ObservableSettings) : Boolean =
            prefs.getBoolean(PREF_SETTINGS_isInSystemTheme, true)

        private fun setInSystemTheme(preferences: ObservableSettings, inSystemTheme : Boolean) {
            preferences.putBoolean(PREF_SETTINGS_isInSystemTheme, inSystemTheme)
        }

        private fun setInDarkTheme(preferences: ObservableSettings,isInDarkTheme: Boolean) {
            if (isInSystemTheme(preferences)) {
                setInSystemTheme(preferences,false)
            }
            preferences.putBoolean(PREF_SETTINGS_isInDarkThemeForced, isInDarkTheme)
        }
    }

}

@Composable
expect fun rememberThemeSelector() : ThemeSelector
