package github.alexzhirkevich.studentbsuby.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp

/**
 * Replicates the sw600dp resource qualifier: smallest dimension
 * of the window is at least 600dp, regardless of orientation.
 */
@Composable
fun isTablet(): Boolean {
    val containerSize = LocalWindowInfo.current.containerSize
    return with(LocalDensity.current) {
        minOf(containerSize.width, containerSize.height).toDp()
    } >= 600.dp
}
