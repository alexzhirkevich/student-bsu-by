package github.alexzhirkevich.studentbsuby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import github.alexzhirkevich.studentbsuby.util.CurrentActivityHolder

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CurrentActivityHolder.set(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            App()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        CurrentActivityHolder.clear(this)
    }
}
