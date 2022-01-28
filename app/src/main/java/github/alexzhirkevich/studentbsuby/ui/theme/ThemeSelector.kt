package github.alexzhirkevich.studentbsuby.ui.theme

import androidx.compose.runtime.Composable

interface ThemeSelector {

    @Composable
    fun isInDarkTheme(): Boolean
    
    fun setTheme(theme: Theme)
}