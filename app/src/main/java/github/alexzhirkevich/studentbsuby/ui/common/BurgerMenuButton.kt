package github.alexzhirkevich.studentbsuby.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BurgerMenuButton(onClick :() -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .padding(3.dp)
//            .align(Alignment.CenterVertically)
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            tint = MaterialTheme.colors.onSecondary,
            contentDescription = "Menu"
        )
    }
}