package github.alexzhirkevich.studentbsuby.ui.screens.settings

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.appearance
import github.alexzhirkevich.studentbsuby.resources.other
import github.alexzhirkevich.studentbsuby.resources.setting_collect_crashlytics
import github.alexzhirkevich.studentbsuby.resources.setting_collect_crashlytics_helper
import github.alexzhirkevich.studentbsuby.resources.setting_collect_statistics
import github.alexzhirkevich.studentbsuby.resources.setting_collect_statistics_helper
import github.alexzhirkevich.studentbsuby.resources.settings
import github.alexzhirkevich.studentbsuby.resources.settings_dark_theme_forsed
import github.alexzhirkevich.studentbsuby.resources.settings_dark_theme_system
import github.alexzhirkevich.studentbsuby.resources.share_logs
import github.alexzhirkevich.studentbsuby.resources.share_logs_helper
import github.alexzhirkevich.studentbsuby.ui.common.HtmlText
import github.alexzhirkevich.studentbsuby.ui.common.NavigationMenuButton
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.CollapsingToolbarScaffold
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.ScrollStrategy
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.rememberCollapsingToolbarScaffoldState
import github.alexzhirkevich.studentbsuby.ui.theme.LocalThemeSelector
import github.alexzhirkevich.studentbsuby.ui.theme.Theme
import github.alexzhirkevich.studentbsuby.util.PlatformInfo
import github.alexzhirkevich.studentbsuby.util.communication.collectAsState
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen(
    onBackClicked: () -> Unit,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val scaffoldState = rememberCollapsingToolbarScaffoldState()

    Column {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .windowInsetsTopHeight(WindowInsets.statusBars)
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
                Toolbar(onBackClicked)
            }) {
            Body(viewModel = viewModel)
        }
    }
}

@Composable
private fun Toolbar(onBackClicked: () -> Unit) {
    Column {
        TopAppBar(
            backgroundColor = animateColorAsState(
                MaterialTheme.colors.secondary).value
        ) {
            NavigationMenuButton(
                icon = Icons.Default.ArrowBack,
                contentDescription = "Back",
                onClick = onBackClicked
            )
            Text(
                text = stringResource(Res.string.settings),
                color = animateColorAsState(MaterialTheme.colors.onSecondary).value,
                style = MaterialTheme.typography.subtitle1
            )
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(animateColorAsState(LocalContentColor.current.copy(.05f)).value))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Body(viewModel: SettingsViewModel) {
    val platformInfo = koinInject<PlatformInfo>()

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        val themeSelector = LocalThemeSelector.current
        val isLight = MaterialTheme.colors.isLight
        GroupName(name = stringResource(Res.string.appearance))
        TogglePreference(
            title = Res.string.settings_dark_theme_system,
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
            title = Res.string.settings_dark_theme_forsed,
            enabled = themeSelector.currentTheme.value != Theme.System,
            checked = !MaterialTheme.colors.isLight
        ) {
            themeSelector.setTheme(
                if (it) Theme.Dark else Theme.Light
            )
        }

        val state by viewModel.state.collectAsState()

        GroupName(name = stringResource(Res.string.other))
        TogglePreference(
            title = Res.string.setting_collect_statistics,
            helper = AnnotatedString(stringResource(Res.string.setting_collect_statistics_helper)),
            checked = state.collectStatistic,
            onChanged = {
                viewModel.handle(SettingsEvent.CollectStatistic(it))
            }
        )
        TogglePreference(
            title = Res.string.setting_collect_crashlytics,
            helper = AnnotatedString(stringResource(Res.string.setting_collect_crashlytics_helper)),
            checked = state.collectCrashlytics,
            onChanged = {
                viewModel.handle(SettingsEvent.CollectCrashlytics(it))
            }
        )

        ButtonPreference(
            title = Res.string.share_logs,
            helper = Res.string.share_logs_helper
        ) {
            viewModel.handle(SettingsEvent.ShareLogs)
        }
        Text(
            text = platformInfo.versionName,
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
        )
        Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ButtonPreference(
    title: StringResource,
    helper: StringResource? = null,
    enabled: Boolean = true,
    onClick: () -> Unit
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
                text = stringResource(title),
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TogglePreference(
    title: StringResource,
    helper: AnnotatedString? = null,
    onHelperClicked: (Int) -> Unit = {},
    enabled: Boolean = true,
    checked: Boolean,
    onChanged: (Boolean) -> Unit
) {
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
                    text = stringResource(title),
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
            @Suppress("DEPRECATION")
            ClickableText(
                modifier = Modifier.padding(15.dp),
                text = it,
                style = MaterialTheme.typography.caption,
                onClick = onHelperClicked
            )
        }
    }
}
