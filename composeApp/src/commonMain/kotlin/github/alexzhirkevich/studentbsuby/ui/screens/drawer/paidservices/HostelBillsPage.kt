package github.alexzhirkevich.studentbsuby.ui.screens.drawer.paidservices

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.something_gone_wrong
import github.alexzhirkevich.studentbsuby.resources.empty
import github.alexzhirkevich.studentbsuby.resources.hostel_bills_empty
import github.alexzhirkevich.studentbsuby.data.models.Bill
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.formatTimestamp
import github.alexzhirkevich.studentbsuby.util.communication.collectAsState
import org.jetbrains.compose.resources.stringResource

@Composable
fun HostelBillsPage(viewModel : PaidServicesViewModel){
    val bills by viewModel.hostelBillsCommunication.collectAsState()

    when (val b = bills) {
        is DataState.Success -> SuccessHostelBillsPage(
            bills = b.value
        )
        is DataState.Loading -> PaidServicesLoadingPage()
        is DataState.Error -> PaidServicesErrorPage(
            title = stringResource(Res.string.something_gone_wrong),
            error = stringResource(b.message)
        )
        is DataState.Empty -> PaidServicesErrorPage(
            title = stringResource(Res.string.empty),
            error = stringResource(Res.string.hostel_bills_empty)
        )
    }
}

@Composable
private fun SuccessHostelBillsPage(
    bills: List<Bill>
) {
    SelectionContainer {
        LazyColumn(
            Modifier.fillMaxSize()
        ) {
            items(bills.size) {
                HostelBillWidget(
                    bill = bills[it],
                    modifier = Modifier.padding(5.dp)
                )
            }
            item { Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars)) }
        }
    }
}

@Composable
private fun HostelBillWidget(
    bill : Bill,
    modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.secondary,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.body1) {
                Text(text = formatTimestamp(bill.deadline))
                Text(text = bill.price.toString() + " BYN")
            }
        }
    }
}
