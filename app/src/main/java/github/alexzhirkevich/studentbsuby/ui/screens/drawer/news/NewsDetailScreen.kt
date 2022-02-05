package github.alexzhirkevich.studentbsuby.ui.screens.drawer.news

import android.app.Activity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsHeight
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.ui.common.BsuProgressBar
import github.alexzhirkevich.studentbsuby.ui.theme.values.Colors
import github.alexzhirkevich.studentbsuby.util.bsuBackgroundPattern
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun NewsDetailsScreen(
    viewModel: NewsViewModel = hiltViewModel(), id : Int
) {

    val scope = rememberCoroutineScope()

    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState())
    ) {

        BsuProgressBar(
            tint = MaterialTheme.colors.primary,
            size = 100.dp,
            modifier = Modifier.align(Alignment.Center)
        )


        val bgColor = Colors.GrayBackground
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.secondary)
                .navigationBarsWithImePadding(),
            factory = {
                WebView(it).apply {
                    setBackgroundColor(bgColor.toArgb())
                    layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    scope.launch {
                        viewModel.setupWebView(id, this@apply)
                    }
                }
            })
    }
}