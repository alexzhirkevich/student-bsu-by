package github.alexzhirkevich.studentbsuby.ui.screens.drawer.paidservices

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.additionally
import github.alexzhirkevich.studentbsuby.resources.currency
import github.alexzhirkevich.studentbsuby.resources.deadline
import github.alexzhirkevich.studentbsuby.resources.empty
import github.alexzhirkevich.studentbsuby.resources.left
import github.alexzhirkevich.studentbsuby.resources.something_gone_wrong
import github.alexzhirkevich.studentbsuby.resources.sum
import github.alexzhirkevich.studentbsuby.data.models.Receipt
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.formatTimestamp
import org.jetbrains.compose.resources.stringResource

@ExperimentalFoundationApi
@Composable
fun <T> ReceiptsPage(
    receipts: DataState<List<T>>,
    header: ((T) -> String)? = null,
    complete: (T) -> Boolean,
    emptyErrorMsg: String,
    widget: @Composable (T) -> Unit
) {
    when (receipts) {
        is DataState.Success -> SuccessReceiptsPage(
            receipts = receipts.value,
            header = header,
            complete = complete
        ) {
            widget(it)
        }
        is DataState.Loading -> PaidServicesLoadingPage()
        is DataState.Error -> PaidServicesErrorPage(
            title = stringResource(Res.string.something_gone_wrong),
            error = stringResource(receipts.message)
        )
        is DataState.Empty -> PaidServicesErrorPage(
            title = stringResource(Res.string.empty),
            error = emptyErrorMsg
        )
    }
}

@Composable
fun ReceiptWidget(
    receipt: Receipt,
    modifier: Modifier = Modifier
) {
    @Composable
    fun BillRow(name: String, value: String) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.caption
                .copy(color = MaterialTheme.colors.onSecondary)) {
                Text(text = "$name:")
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = value,
                    textAlign = TextAlign.End
                )
            }
        }
    }
    Card(
        elevation = 3.dp,
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.secondary,
    ) {
        Column(Modifier.padding(10.dp)) {
            if (receipt.date != null && receipt.price != null) {
                Text(
                    text = formatTimestamp(receipt.date),
                    style = MaterialTheme.typography.body1
                )
                BillRow(
                    name = stringResource(Res.string.sum),
                    value = receipt.price.toString() + " " + stringResource(Res.string.currency)
                )

                BillRow(
                    name = stringResource(Res.string.deadline),
                    value = formatTimestamp(receipt.deadline)
                )
                BillRow(
                    name = stringResource(Res.string.left),
                    value = receipt.left.toString() + " " + stringResource(Res.string.currency)
                )
            } else {
                Text(
                    text = formatTimestamp(receipt.deadline),
                    style = MaterialTheme.typography.body1
                )
                BillRow(
                    name = stringResource(Res.string.left),
                    value = receipt.left.toString() + " " + stringResource(Res.string.currency)
                )
            }
            receipt.info?.let {
                BillRow(
                    name = stringResource(Res.string.additionally),
                    value = it
                )
            }
        }
    }
}
