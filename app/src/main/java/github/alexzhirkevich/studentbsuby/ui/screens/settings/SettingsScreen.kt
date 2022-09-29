package github.alexzhirkevich.studentbsuby.ui.screens.settings

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.pager.ExperimentalPagerApi
import de.charlex.compose.HtmlText
import de.charlex.compose.toAnnotatedString
import github.alexzhirkevich.studentbsuby.BuildConfig
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.ui.common.NavigationMenuButton
import github.alexzhirkevich.studentbsuby.ui.theme.LocalThemeSelector
import github.alexzhirkevich.studentbsuby.ui.theme.Theme
import github.alexzhirkevich.studentbsuby.util.communication.collectAsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun SettingsScreen(viewModel : SettingsViewModel = hiltViewModel()) {

    val scaffoldState = rememberCollapsingToolbarScaffoldState()

    Column {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .statusBarsHeight()
            .background(animateColorAsState(MaterialTheme.colors.secondary).value)
            .zIndex(1f)
        )
        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    animateColorAsState(MaterialTheme.colors.background).value
                ),
            state = scaffoldState,
            scrollStrategy = ScrollStrategy.EnterAlways,
            toolbar = {
              Toolbar()
            }) {
            Body(viewModel = viewModel)
        }
    }
}

@Composable
private fun Toolbar() {
    val activity = LocalContext.current as Activity

    Column {
        TopAppBar(
            backgroundColor = animateColorAsState(
                MaterialTheme.colors.secondary).value
        ) {
            NavigationMenuButton(
                icon = Icons.Default.ArrowBack,
                contentDescription = "Back",
                onClick = activity::onBackPressed
            )
            Text(
                text = stringResource(id = R.string.settings),
                color = animateColorAsState(MaterialTheme.colors.onSecondary)
                    .value,
                style = MaterialTheme.typography.subtitle1
            )
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(animateColorAsState(LocalContentColor.current.copy(.05f)).value))
    }
}

@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
private fun Body(viewModel: SettingsViewModel) {

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        val themeSelector = LocalThemeSelector.current
        val isLight = MaterialTheme.colors.isLight
        GroupName(name = stringResource(id = R.string.appearance))
        TogglePreference(
            title = R.string.settings_dark_theme_system,
            checked = themeSelector.currentTheme.value == Theme.System,
        ) {
            themeSelector.setTheme(
                when {
                    it -> Theme.System
                    isLight -> Theme.Light
                    else -> Theme.Dark
                }
            )
        }
        TogglePreference(
            title = R.string.settings_dark_theme_forsed,
            enabled = themeSelector.currentTheme.value != Theme.System,
            checked = !MaterialTheme.colors.isLight
        ) {
            themeSelector.setTheme(
                if (it) Theme.Dark else Theme.Light
            )
        }

//        GroupName(name = stringResource(id = R.string.notifications))
//        val helper = stringResource(R.string.settings_update_notifications_helper)
//        val whyRange =
//            stringResource(id = R.string.settings_update_notifications_helper_why).let { text ->
//            helper.indexOf(text, ignoreCase = true).let { it..it + text.length }
//        }
//        val autoStartRange =
//            stringResource(id = R.string.settings_update_notifications_helper_autostart).let { text ->
//                helper.indexOf(text, ignoreCase = true).let { it..it + text.length }
//            }
//        val backgroundRange =
//            stringResource(id = R.string.settings_update_notifications_helper_background).let { text ->
//                helper.indexOf(text, ignoreCase = true).let { it..it + text.length }
//            }
//        val attentionRange =
//            stringResource(id = R.string.settings_update_notifications_helper_attention).let { text ->
//                helper.indexOf(text, ignoreCase = true).let { it..it + text.length }
//            }

        val state by viewModel.state.collectAsState()
//        val activity = LocalContext.current as Activity
//        TogglePreference(
//            title = R.string.settings_update_notifications,
//            helper = AnnotatedString(
//                text = helper,
//                spanStyles = listOf(
//                    AnnotatedString.Range(
//                        SpanStyle(fontWeight = FontWeight.Bold),
//                      start = attentionRange.first,
//                      end = attentionRange.last
//                    ),
//                    AnnotatedString.Range(
//                        SpanStyle(
//                            color = MaterialTheme.colors.primary,
//                            textDecoration = TextDecoration.Underline
//                        ),
//                        start = autoStartRange.first,
//                        end = autoStartRange.last,
//                    ),
//                    AnnotatedString.Range(
//                        SpanStyle(
//                            color = MaterialTheme.colors.primary,
//                            textDecoration = TextDecoration.Underline
//                        ),
//                        start = backgroundRange.first,
//                        end = backgroundRange.last
//                    ),
//                    AnnotatedString.Range(
//                        SpanStyle(
//                            color = MaterialTheme.colors.primary,
//                            textDecoration = TextDecoration.Underline
//                        ),
//                        start = whyRange.first,
//                        end = whyRange.last
//                    )
//                ),
//            ),
//            onHelperClicked = {
//                if (it in autoStartRange) {
//                    viewModel.handle(SettingsEvent.AutoStartClicked(activity))
//                }
//                if (it in backgroundRange) {
//                    viewModel.handle(SettingsEvent.BackgroundActivityClicked(activity))
//                }
//                if (it in whyRange){
//                    viewModel.handle(SettingsEvent.DontKillMyApp)
//                }
//            },
//            checked = state.notificationsEnabled,
//            onChanged = {
//                viewModel.handle(SettingsEvent.NotificationsEnabled(it))
//            }
//        )

        GroupName(name = stringResource(id = R.string.other))
        TogglePreference(
            title = R.string.setting_collect_statistics,
            helper = stringResource(R.string.setting_collect_statistics_helper).toAnnotatedString(),
            checked = state.collectStatistic,
            onChanged = {
                viewModel.handle(SettingsEvent.CollectStatistic(it))
            }
        )
        TogglePreference(
            title = R.string.setting_collect_crashlytics,
            helper = stringResource(R.string.setting_collect_crashlytics_helper).toAnnotatedString(),
            checked = state.collectCrashlytics,
            onChanged = {
                viewModel.handle(SettingsEvent.CollectCrashlytics(it))
            }
        )

        ButtonPreference(
            title = R.string.share_logs,
            helper = R.string.share_logs_helper
        ) {
            viewModel.handle(SettingsEvent.ShareLogs)
        }
        Text(
            text = BuildConfig.VERSION_NAME,
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
        )
        Spacer(modifier = Modifier.navigationBarsWithImePadding())
    }
}

@Composable
fun GroupName(name : String) {
    Text(
        modifier = Modifier.padding(10.dp),
        text = name,
        style = MaterialTheme.typography.body2,
    )
}

@ExperimentalMaterialApi
@Composable
fun ButtonPreference(
    @StringRes title : Int,
    @StringRes helper : Int?=null,
    enabled : Boolean = true,
    onClick : () -> Unit
) {

    Column(Modifier.fillMaxWidth()) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = animateColorAsState(
                MaterialTheme.colors.secondary).value,
            shape = RectangleShape,
            enabled = enabled,
            onClick = onClick
        ) {
            Text(
                modifier = Modifier.padding(15.dp),
                text = stringResource(id = title),
                style = MaterialTheme.typography.body1
            )
        }
        helper?.let {
            HtmlText(
                modifier = Modifier.padding(15.dp),
                textId = it,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun TogglePreference(
    @StringRes title : Int,
    helper : AnnotatedString?=null,
    onHelperClicked : (Int) -> Unit = {},
    enabled : Boolean = true,
    checked : Boolean,
    onChanged : (Boolean) -> Unit) {

    Column(Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = animateColorAsState(
                MaterialTheme.colors.secondary).value,
            shape = RectangleShape,
            enabled = enabled,
            onClick = {
                onChanged(!checked)
            }
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 15.dp),
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.width(15.dp))
                Switch(
                    checked = checked,
                    onCheckedChange = onChanged,
                    enabled = enabled,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = animateColorAsState(
                            MaterialTheme.colors.primary).value,
                        checkedTrackAlpha = .5f,
                        uncheckedThumbColor = animateColorAsState(
                            MaterialTheme.colors.background).value,
                        disabledCheckedThumbColor = animateColorAsState(
                            MaterialTheme.colors.primary.copy(alpha = .5f)).value,
                        disabledUncheckedThumbColor = animateColorAsState(
                            MaterialTheme.colors.background.copy(alpha = .5f)).value,
                    )
                )
            }
        }
        helper?.let {
            ClickableText(
                modifier = Modifier.padding(15.dp),
                text = it,
                style = MaterialTheme.typography.caption,
                onClick = onHelperClicked
            )
        }
    }
}