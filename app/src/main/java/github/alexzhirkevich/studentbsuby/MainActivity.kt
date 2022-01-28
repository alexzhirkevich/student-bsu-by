package github.alexzhirkevich.studentbsuby

import android.app.StatusBarManager
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import github.alexzhirkevich.studentbsuby.ui.screens.MainScreen
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.timetable.TimetableScreen
import github.alexzhirkevich.studentbsuby.ui.theme.StudentbsubyTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import android.R
import android.os.Build
import android.view.View

import android.view.WindowManager

import android.view.Window
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import github.alexzhirkevich.studentbsuby.ui.theme.LocalThemeSelector


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@FlowPreview
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            StudentbsubyTheme {

                val inDark = LocalThemeSelector.current.isInDarkTheme()
                LaunchedEffect(inDark) {
                    theme.applyStyle(
                        if (inDark) R.style.ThemeOverlay_Material_Dark
                        else R.style.ThemeOverlay_Material_Light, true
                    )
                }
                MainScreen()
            }
        }
    }
}
