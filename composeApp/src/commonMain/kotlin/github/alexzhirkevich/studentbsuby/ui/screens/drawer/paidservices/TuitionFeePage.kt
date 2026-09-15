package github.alexzhirkevich.studentbsuby.ui.screens.drawer.paidservices

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import github.alexzhirkevich.studentbsuby.data.models.TuitionFeePayment
import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.currency
import github.alexzhirkevich.studentbsuby.resources.deadline
import github.alexzhirkevich.studentbsuby.resources.fine_days
import github.alexzhirkevich.studentbsuby.resources.fine_size
import github.alexzhirkevich.studentbsuby.resources.full_price
import github.alexzhirkevich.studentbsuby.resources.left
import github.alexzhirkevich.studentbsuby.resources.sum
import github.alexzhirkevich.studentbsuby.resources.tuition_fees_empty
import github.alexzhirkevich.studentbsuby.util.communication.collectAsState
import github.alexzhirkevich.studentbsuby.util.formatTimestamp
import org.jetbrains.compose.resources.stringResource

@ExperimentalFoundationApi
@Composable
fun TuitionFeePage(
    viewModel : PaidServicesViewModel
) {
    ReceiptsPage(
        receipts = viewModel.tutionFeeCommunication.collectAsState().value,
        header = { it.year },
        complete = { it.date != null },
        emptyErrorMsg = stringResource(Res.string.tuition_fees_empty)
    ) {
        TuitionFeeReceiptWidget(
            payment = it,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
private fun TuitionFeeReceiptWidget(
    payment: TuitionFeePayment,
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
                Text(text = value)
            }
        }
    }
    Card(
        elevation = 3.dp,
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.secondary,
    ) {
        Column(Modifier.padding(10.dp)) {
            if (payment.date != null && payment.price != null) {
                Text(
                    text = formatTimestamp(payment.date),
                    style = MaterialTheme.typography.body1
                )
                BillRow(
                    name = stringResource(Res.string.sum),
                    value = payment.price.toString() + " " + stringResource(Res.string.currency)
                )
                if (payment.fineDays != 0) {
                    BillRow(
                        name = stringResource(Res.string.fine_days),
                        value = payment.fineDays.toString()
                    )
                    BillRow(
                        name = stringResource(Res.string.fine_size),
                        value = payment.fineSize.toString() + " " + stringResource(Res.string.currency)
                    )
                }
                BillRow(
                    name = stringResource(Res.string.deadline),
                    value = formatTimestamp(payment.deadline)
                )

                BillRow(
                    name = stringResource(Res.string.full_price),
                    value = payment.fullPrice.toString() + " " + stringResource(Res.string.currency)
                )

                BillRow(
                    name = stringResource(Res.string.left),
                    value = payment.left.toString() + " " + stringResource(Res.string.currency)
                )
            } else {
                Text(
                    text = formatTimestamp(payment.deadline),
                    style = MaterialTheme.typography.body1
                )
                BillRow(
                    name = stringResource(Res.string.left),
                    value = payment.left.toString() + " " + stringResource(Res.string.currency)
                )
            }
        }
    }
}
