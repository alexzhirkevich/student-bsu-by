package github.alexzhirkevich.studentbsuby.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import github.alexzhirkevich.studentbsuby.ui.theme.values.Colors
import github.alexzhirkevich.studentbsuby.ui.theme.values.Shapes
import github.alexzhirkevich.studentbsuby.ui.theme.values.typography

private val DarkColorPalette = darkColors(
    primary = Color.White,
    primaryVariant = Color(0xFF252525),
    secondary = Color(0xff1d1d1d),
    secondaryVariant = Color.White,
    surface= Colors.White,
    background = Color(0xff101010),
    error = Colors.Red,
    onError = Colors.White,
    onSecondary = Colors.White,
    onPrimary = Color.Black
)

private val LightColorPalette = lightColors(
    primary = Colors.Blue,
    primaryVariant = Colors.DarkBlue,
    secondary = Colors.White,
    secondaryVariant = Color.White,
    background = Colors.GrayBackground,
    error = Colors.Red,
    onError = Colors.White,
    onPrimary = Color.White,
)


@Composable
fun StudentbsubyTheme(
    content: @Composable () -> Unit
) {

    val themeSelector = rememberThemeSelector()

    val isDark = if (themeSelector.currentTheme.value == Theme.System)
        isSystemInDarkTheme()
    else themeSelector.currentTheme.value == Theme.Dark

    SystemBarAppearance(isDark)

    val colors = if (isDark) DarkColorPalette else LightColorPalette

    CompositionLocalProvider(
        LocalThemeSelector provides themeSelector,
    ) {
        ProvideTextStyle(LocalTextStyle.current.copy(
            color = if (isDark) Color.White else Color.Black
        )) {
            MaterialTheme(
                colors = colors,
                typography = typography(colors.onBackground),
                shapes = Shapes,
                content = content
            )
        }
    }
}

@Composable
internal expect fun SystemBarAppearance(isDark: Boolean)
