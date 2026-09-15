package github.alexzhirkevich.studentbsuby.ui.screens.drawer.paidservices

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.bills
import github.alexzhirkevich.studentbsuby.resources.history
import org.jetbrains.compose.resources.stringResource

@Composable
fun LazyItemScope.Header(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)) {
        Box(
            Modifier
                .align(Alignment.Center)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colors.background.copy(alpha = .9f))
                .padding(vertical = 5.dp, horizontal = 10.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun <T> SuccessReceiptsPage(
    receipts: List<T>,
    header: ((T) -> String)? = null,
    complete: (T) -> Boolean,
    widget: @Composable (T) -> Unit
) {
    SelectionContainer {
        fun LazyListScope.Items(items: List<T>) {
            header?.let { h ->
                items.groupBy(h).forEach { (key, value) ->
                    stickyHeader {
                        Header(text = key)
                    }
                    items(value.size) { idx ->
                        widget(value[idx])
                    }
                }
            } ?: items(items.size) { idx ->
                widget(items[idx])
            }
        }

        LazyColumn(
            Modifier.fillMaxSize()
        ) {
            receipts.groupBy(complete).let { grouped ->
                grouped[false]?.let { billsList ->
                    stickyHeader {
                        Header(text = stringResource(Res.string.bills))
                    }
                    Items(billsList)
                }

                grouped[true]?.let { historyList ->
                    stickyHeader {
                        Header(text = stringResource(Res.string.history))
                    }
                    Items(historyList)
                    item {
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
            item { Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars)) }
        }
    }
}
