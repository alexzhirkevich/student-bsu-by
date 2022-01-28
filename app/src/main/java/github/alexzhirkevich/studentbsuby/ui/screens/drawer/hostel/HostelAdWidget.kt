package github.alexzhirkevich.studentbsuby.ui.screens.drawer.hostel

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.data.models.HostelAdvert

@ExperimentalMaterialApi
@Composable
fun HostelAdWidget(
    ad : HostelAdvert,
    modifier: Modifier = Modifier,
    onCallClicked : () -> Unit,
    onLocateClicked : () -> Unit,
) {

    Card(
        modifier = modifier,
        elevation = 3.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Row(Modifier.fillMaxWidth()) {

                Text(
                    text = ad.address ?: stringResource(id = R.string.unknown_adress),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = ad.publishDate,
                    style = MaterialTheme.typography.caption
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column() {
                    ad.conditions?.let {
                        InfoText(stringResource(id = R.string.conditions), it)
                    }

                    ad.price?.let {
                        InfoText(stringResource(id = R.string.price), it)
                    }

                    ad.note?.let {
                        InfoText(stringResource(id = R.string.note), it)
                    }
                    ad.phone?.let {
                        InfoText(stringResource(id = R.string.phone), it)
                    }

                }
                Spacer(modifier = Modifier.width(10.dp))
                if (!ad.phone.isNullOrBlank()) {
                    IconButton(
                        onClick = onCallClicked,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            tint = MaterialTheme.colors.primary,
                            contentDescription = "Call"
                        )
                    }
                }
                if (!ad.address.isNullOrBlank()) {
                    IconButton(
                        onClick = onLocateClicked,
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            tint = MaterialTheme.colors.primary,
                            contentDescription = "Locate"
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun InfoText(
    title : String,
    text : String,
    modifier: Modifier = Modifier
) {
    Text(
        text = AnnotatedString(
            "$title: $text",
            spanStyles = listOf(
                AnnotatedString.Range(
                    SpanStyle(fontWeight = FontWeight.SemiBold),
                    start = 0,
                    end = title.length + 1
                ),
            )
        )
    )
}