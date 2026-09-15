package github.alexzhirkevich.studentbsuby.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import github.alexzhirkevich.studentbsuby.MainActivityEvent
import github.alexzhirkevich.studentbsuby.MainActivityViewModel
import github.alexzhirkevich.studentbsuby.repo.ApplicationVersion
import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.back
import github.alexzhirkevich.studentbsuby.resources.exit
import github.alexzhirkevich.studentbsuby.resources.update
import github.alexzhirkevich.studentbsuby.resources.update_proposal
import github.alexzhirkevich.studentbsuby.resources.update_proposal_text
import github.alexzhirkevich.studentbsuby.resources.update_proposal_what_new
import github.alexzhirkevich.studentbsuby.resources.update_required
import github.alexzhirkevich.studentbsuby.resources.update_required_text
import github.alexzhirkevich.studentbsuby.resources.update_required_why
import github.alexzhirkevich.studentbsuby.resources.update_required_why_text
import org.jetbrains.compose.resources.stringResource


@Composable
fun UpdateRequiredDialog(mainActivityViewModel: MainActivityViewModel) {

    UpdateDialog(
        title = stringResource(Res.string.update_required),
        text = stringResource(Res.string.update_required_text),
        desc = stringResource(Res.string.update_required_why),
        destText = stringResource(Res.string.update_required_why_text),
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        buttonClose = stringResource(Res.string.exit),
        mainActivityViewModel = mainActivityViewModel
    )
}

@Composable
fun UpdateProposalDialog(
    applicationVersion: ApplicationVersion,
    mainActivityViewModel: MainActivityViewModel
) {
    UpdateDialog(
        title = stringResource(Res.string.update_proposal),
        text = stringResource(Res.string.update_proposal_text),
        desc = stringResource(Res.string.update_proposal_what_new),
        destText = applicationVersion.desc,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        buttonClose = stringResource(Res.string.exit),
        mainActivityViewModel = mainActivityViewModel
    )
}

@Composable
fun UpdateDialog(
    title: String,
    text: String,
    desc: String,
    destText: String,
    properties: DialogProperties,
    buttonClose: String,
    mainActivityViewModel: MainActivityViewModel,
) {
    var descVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var dialogVisible by rememberSaveable {
        mutableStateOf(true)
    }

    if (dialogVisible) {
        Dialog(
            onDismissRequest = {  },
            properties = properties
        ) {
            Card(
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = title,
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    AnimatedContent(targetState = descVisible) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            if (!it) {
                                Text(
                                    text = text,
                                    style = MaterialTheme.typography.body1,
                                    textAlign = TextAlign.Center,
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                ClickableText(
                                    style = MaterialTheme.typography.body1.copy(
                                        color = MaterialTheme.colors.primary
                                    ),
                                    text = AnnotatedString(desc)
                                ) {
                                    descVisible = true
                                }
                            } else {
                                Text(
                                    text = destText,
                                    style = MaterialTheme.typography.body1,
                                    textAlign = TextAlign.Center,
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                ClickableText(
                                    style = MaterialTheme.typography.body1.copy(
                                        color = MaterialTheme.colors.primary
                                    ),
                                    text = AnnotatedString(stringResource(Res.string.back))
                                ) {
                                    descVisible = false
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        TextButton(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                mainActivityViewModel.handle(
                                    MainActivityEvent.ExitClicked
                                )
                                dialogVisible = false
                            }
                        ) {
                            Text(text = buttonClose)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                mainActivityViewModel.handle(
                                    MainActivityEvent.UpdateClicked)
                            },
                        ) {
                            Text(
                                text = stringResource(Res.string.update),
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                }
            }
        }
    }
}
