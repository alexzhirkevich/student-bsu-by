package github.alexzhirkevich.studentbsuby.ui.screens.drawer.subjects

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceEvenly
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.data.models.Subject
import github.alexzhirkevich.studentbsuby.util.bsuBackgroundPattern

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SubjectWidget(
    subject : Subject,
    isOpened : Boolean = false,
    withAnimation : Boolean = false,
    modifier: Modifier = Modifier,
    onClick : () -> Unit = {}
) {

    var animationEnabled by rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier,
        elevation = 3.dp,
        backgroundColor = MaterialTheme.colors.secondary,
        onClick = {
            animationEnabled = true
//            isOpenedInternal = !isOpenedInternal
            onClick()
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.primary)
                    .bsuBackgroundPattern(
                        color = MaterialTheme.colors.background.copy(alpha = .05f),
                        clip = true
                    )
                    .padding(vertical = 10.dp, horizontal = 5.dp)
                    .zIndex(2f)
            ) {

                Text(
                    text = subject.name,
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }


            AnimatedVisibility(
                modifier=  Modifier.zIndex(1f),
                visible = isOpened,
            ) {
                HoursPanel(subject = subject,modifier = Modifier
                    .fillMaxWidth()
                    .animateEnterExit(
                        enter = if (animationEnabled)
                            slideInVertically() else EnterTransition.None,
                        exit = if (animationEnabled)
                            slideOutVertically() else ExitTransition.None
                    ))
            }

            if (subject.hasCredit || subject.hasExam) {
                Column(
                    Modifier.padding(10.dp)
                ) {
                    if (subject.hasCredit) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text(
                                text = stringResource(R.string.zachet),
                            )

                            val (icon, color) = when {
                                subject.creditPassed != null ->
                                    if (subject.creditPassed)
                                        Icons.Default.TaskAlt to MaterialTheme.colors.onError
                                    else Icons.Default.HighlightOff to MaterialTheme.colors.error
                                else -> Icons.Default.Schedule to MaterialTheme.colors.surface
                            }


                            if (subject.creditMark == null) {
                                Icon(imageVector = icon, tint = color, contentDescription = "")
                            }else {
                                Text(
                                    text = subject.creditMark.toString(),
                                    style = MaterialTheme.typography.subtitle1,
                                    color = when {
                                        subject.creditMark in 1..3 -> MaterialTheme.colors.error
                                        subject.creditMark > 3 -> MaterialTheme.colors.onError
                                        else -> MaterialTheme.colors.surface
                                    },
                                    modifier = Modifier.padding(end = 5.dp)
                                )
                            }
                            if (subject.creditRetakes != 0) {
                                Box() {
                                    Icon(
                                        imageVector = Icons.Default.Replay,
                                        contentDescription = "",
                                        tint = MaterialTheme.colors.error,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                    Text(
                                        text = subject.creditRetakes.toString(),
                                        style = MaterialTheme.typography.caption,
                                        color = MaterialTheme.colors.error,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }

                    if (subject.hasExam) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Icons.Default.Replay10
                            Text(
                                text = stringResource(R.string.exam),
                                style = MaterialTheme.typography.body1
                            )

//                            val (icon, mark) = when {
//                                subject.examMark != null ->
//                                    Icons.Default.RadioButtonUnchecked to subject.examMark
//                                else -> Icons.Default.Schedule to 0
//                            }

                            Spacer(modifier = Modifier.weight(1f))

                            if(subject.examMark != null) {
                                Text(
                                    text = subject.examMark.toString(),
                                    style = MaterialTheme.typography.subtitle1,
                                    color = when {
                                        subject.examMark in 1..3 -> MaterialTheme.colors.error
                                        subject.examMark > 3 -> MaterialTheme.colors.onError
                                        else -> MaterialTheme.typography.body1.color
                                    },
                                    modifier = Modifier.padding(end = 5.dp)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Schedule,
                                    tint = MaterialTheme.typography.body1.color,
                                    contentDescription = "")
                            }

                        }
                        if (subject.examRetakes != 0) {
                            Box() {
                                Icon(
                                    imageVector = Icons.Default.Replay,
                                    contentDescription = "",
                                    modifier = Modifier.align(Alignment.Center)
                                )
                                Text(
                                    text = subject.examMark.toString(),
                                    style = MaterialTheme.typography.caption,
                                    color = MaterialTheme.typography.body1.color,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HoursPanel(subject: Subject,modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val hours = listOf(
            stringResource(R.string.lectures) to subject.lectures,
            stringResource(R.string.pract) to subject.practice,
            stringResource(R.string.labs) to subject.labs,
            stringResource(R.string.seminars) to subject.seminars,
            stringResource(R.string.facults) to subject.facults,
            stringResource(R.string.ksr) to subject.ksr,
        ).filter { it.second > 0 }

        val chunkedHours = when {
            hours.size <= 3 -> listOf(hours)
            hours.size == 4 -> hours.chunked(2)
            else -> hours.chunked(3)
        }
        chunkedHours.forEach { group ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
                verticalAlignment = CenterVertically,
                horizontalArrangement = SpaceEvenly,
            ) {
                group.forEach {
                    if (it.second != 0) {
                        HoursItem(name = it.first, hrs = it.second)
                    }
                }
            }
        }
    }
}


@Composable
private fun HoursItem(name : String, hrs : Int){

    with (LocalDensity.current) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = CircleShape,
                backgroundColor = MaterialTheme.colors.background,
                elevation = 5.dp
            ) {
                Box(
                    modifier = Modifier.size(
                        MaterialTheme.typography.body1
                            .fontSize.toDp() * 2.5f
                    )
                ) {

                    Text(
                        text = hrs.toString(),
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .align(Center)
                    )
                }

            }
            Text(text = name, style = MaterialTheme.typography.caption)
        }
    }
}