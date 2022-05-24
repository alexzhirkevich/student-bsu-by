package github.alexzhirkevich.studentbsuby

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.view.WindowCompat
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import github.alexzhirkevich.studentbsuby.repo.ApplicationVersion
import github.alexzhirkevich.studentbsuby.ui.screens.MainScreen
import github.alexzhirkevich.studentbsuby.ui.theme.StudentbsubyTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.onebone.toolbar.ExperimentalToolbarApi


@ExperimentalToolbarApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel.provideActivity(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)

//        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//        val uri = Uri.fromParts("package", packageName, null)
//        intent.data = uri
//        startActivity(intent)

        setContent {
            StudentbsubyTheme {
                MainScreen()
                if (mainActivityViewModel.showUpdateDialog.value){
                    UpdateRequiredDialog()
                }
            }
        }
    }

    

    @Composable
    fun UpdateRequiredDialog() {

        UpdateDialog(
            title = stringResource(id = R.string.update_required),
            text = stringResource(R.string.update_required_text),
            desc = stringResource(id = R.string.update_required_why),
            destText = stringResource(id = R.string.update_required_why_text),
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            buttonClose = stringResource(id = R.string.exit),
            onCloseClick = {
                finish()
                false
            }
        )
    }

    @Composable
    fun UpdateProposalDialog(applicationVersion: ApplicationVersion) {
        UpdateDialog(
            title = stringResource(id = R.string.update_proposal),
            text = stringResource(R.string.update_proposal_text),
            desc = stringResource(id = R.string.update_proposal_what_new),
            destText = applicationVersion.desc,
            buttonClose = stringResource(id = R.string.exit),
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onCloseClick = { true }
        )
    }

    @Composable
    fun UpdateDialog(
        title : String,
        text : String,
        desc : String,
        destText : String,
        properties: DialogProperties,
        buttonClose : String,
        onCloseClick : () -> Boolean,
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
                                        text = AnnotatedString(stringResource(id = R.string.back))
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
                                    if (onCloseClick())
                                        dialogVisible = false
                                }
                            ) {
                                Text(text = buttonClose)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Button(
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    mainActivityViewModel.onUpdateClicked(this@MainActivity)
                                },
                            ) {
                                Text(
                                    text = stringResource(id = R.string.update),
                                    color = MaterialTheme.colors.onPrimary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}