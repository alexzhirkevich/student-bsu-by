package github.alexzhirkevich.studentbsuby.ui.screens.settings

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.pager.ExperimentalPagerApi
import de.charlex.compose.HtmlText
import github.alexzhirkevich.studentbsuby.BuildConfig
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.ui.common.NavigationMenuButton
import github.alexzhirkevich.studentbsuby.ui.theme.LocalThemeSelector
import github.alexzhirkevich.studentbsuby.ui.theme.Theme
import github.alexzhirkevich.studentbsuby.ui.theme.ThemeSelector
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
            .background(MaterialTheme.colors.secondary)
            .zIndex(1f)
        )
        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
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

    Column() {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.secondary
        ) {
            NavigationMenuButton(
                icon = Icons.Default.ArrowBack,
                contentDescription = "Back",
                onClick = activity::onBackPressed
            )
            Text(
                text = stringResource(id = R.string.settings),
                color = MaterialTheme.colors.onSecondary,
                style = MaterialTheme.typography.subtitle1
            )
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(LocalContentColor.current.copy(.05f)))
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
            enabled= themeSelector.currentTheme.value != Theme.System,
            checked = !MaterialTheme.colors.isLight
        ) {
            themeSelector.setTheme(
                if (it) Theme.Dark else Theme.Light
            )
        }

        GroupName(name = stringResource(id = R.string.notifications))
        TogglePreference(
            title = R.string.settings_update_notifications,
            helper = R.string.settings_update_notifications_helper,
            checked = viewModel.notificationsEnabled.value,
            onChanged = viewModel::setNotificationsEnabled)

        GroupName(name = stringResource(id = R.string.other))
        TogglePreference(
            title = R.string.setting_collect_statistics,
            helper = R.string.setting_collect_statistics_helper,
            checked = viewModel.collectStatistics.value,
            onChanged = viewModel::setCollectStatistics)
        TogglePreference(
            title = R.string.setting_collect_crashlytics,
            helper = R.string.setting_collect_crashlytics_helper,
            checked = viewModel.collectCrashlytics.value,
            onChanged = viewModel::setCollectCrashlytics)

        val context = LocalContext.current
        ButtonPreference(
            title = R.string.share_logs,
            helper = R.string.share_logs_helper
        ){
            viewModel.shareLogs(context)
        }
        Text(
            text = BuildConfig.VERSION_NAME,
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
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
            backgroundColor = MaterialTheme.colors.secondary,
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
    @StringRes helper : Int?=null,
    enabled : Boolean = true,
    checked : Boolean,
    onChanged : (Boolean) -> Unit) {

    Column(Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.secondary,
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
                        checkedThumbColor = MaterialTheme.colors.primary,
                        checkedTrackAlpha = .5f,
                        uncheckedThumbColor = MaterialTheme.colors.background,
                        disabledCheckedThumbColor = MaterialTheme.colors.primary.copy(alpha = .5f),
                        disabledUncheckedThumbColor = MaterialTheme.colors.background.copy(alpha = .5f),
                    )
                )
            }
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