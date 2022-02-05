package github.alexzhirkevich.studentbsuby.ui.screens.settings;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003\u001a;\u0010\u0004\u001a\u00020\u00012\b\b\u0001\u0010\u0005\u001a\u00020\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0007\u00a2\u0006\u0002\u0010\f\u001a\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u0012\u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001aI\u0010\u0011\u001a\u00020\u00012\b\b\u0001\u0010\u0005\u001a\u00020\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\t2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u0014H\u0007\u00a2\u0006\u0002\u0010\u0015\u001a\b\u0010\u0016\u001a\u00020\u0001H\u0003\u00a8\u0006\u0017"}, d2 = {"Body", "", "viewModel", "Lgithub/alexzhirkevich/studentbsuby/ui/screens/settings/SettingsViewModel;", "ButtonPreference", "title", "", "helper", "enabled", "", "onClick", "Lkotlin/Function0;", "(ILjava/lang/Integer;ZLkotlin/jvm/functions/Function0;)V", "GroupName", "name", "", "SettingsScreen", "TogglePreference", "checked", "onChanged", "Lkotlin/Function1;", "(ILjava/lang/Integer;ZZLkotlin/jvm/functions/Function1;)V", "Toolbar", "app_release"})
public final class SettingsScreenKt {
    
    @androidx.compose.runtime.Composable()
    @com.google.accompanist.pager.ExperimentalPagerApi()
    @kotlinx.coroutines.ExperimentalCoroutinesApi()
    @androidx.compose.material.ExperimentalMaterialApi()
    @androidx.compose.ui.ExperimentalComposeUiApi()
    @androidx.compose.animation.ExperimentalAnimationApi()
    @androidx.compose.foundation.ExperimentalFoundationApi()
    public static final void SettingsScreen(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.ui.screens.settings.SettingsViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void Toolbar() {
    }
    
    @androidx.compose.runtime.Composable()
    @com.google.accompanist.pager.ExperimentalPagerApi()
    @kotlinx.coroutines.ExperimentalCoroutinesApi()
    @androidx.compose.material.ExperimentalMaterialApi()
    @androidx.compose.ui.ExperimentalComposeUiApi()
    @androidx.compose.animation.ExperimentalAnimationApi()
    @androidx.compose.foundation.ExperimentalFoundationApi()
    private static final void Body(github.alexzhirkevich.studentbsuby.ui.screens.settings.SettingsViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void GroupName(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    @androidx.compose.runtime.Composable()
    @androidx.compose.material.ExperimentalMaterialApi()
    public static final void ButtonPreference(@androidx.annotation.StringRes()
    int title, @org.jetbrains.annotations.Nullable()
    @androidx.annotation.StringRes()
    java.lang.Integer helper, boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    @androidx.compose.material.ExperimentalMaterialApi()
    public static final void TogglePreference(@androidx.annotation.StringRes()
    int title, @org.jetbrains.annotations.Nullable()
    @androidx.annotation.StringRes()
    java.lang.Integer helper, boolean enabled, boolean checked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onChanged) {
    }
}