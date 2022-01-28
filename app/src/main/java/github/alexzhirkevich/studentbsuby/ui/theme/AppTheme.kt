package github.alexzhirkevich.studentbsuby.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.google.accompanist.insets.ProvideWindowInsets
import github.alexzhirkevich.studentbsuby.ui.theme.values.*
import github.alexzhirkevich.studentbsuby.ui.theme.values.Colors

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = Color(0xff383b40),
    primaryVariant = Colors.White,
    secondary = Color(0xFF27292d),
    secondaryVariant = Color.White,
    surface= Colors.White,
    background = Color(0xFF1f2023),
    error = Colors.Red,
    onError = Colors.Green,
    onSecondary = Colors.White,
    onPrimary = Colors.White
)


private val LightColorPalette = lightColors(
    primary = Colors.Blue,
    primaryVariant = Colors.DarkBlue,
    secondary = Colors.White,
    secondaryVariant = Color.White,
    background = Colors.GrayBackground,
    error = Colors.Red,
    onError = Colors.Green
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)


@Composable
fun StudentbsubyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {

    CompositionLocalProvider(
        LocalThemeSelector provides rememberThemeSelector(),
    ) {

        val colors = if (LocalThemeSelector.current.isInDarkTheme()) {
            DarkColorPalette
        } else {
            LightColorPalette
        }
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
        ) {
            ProvideWindowInsets {
                ProvideTextStyle(MaterialTheme.typography.body1) {
                    content()
                }
            }
        }
    }
}