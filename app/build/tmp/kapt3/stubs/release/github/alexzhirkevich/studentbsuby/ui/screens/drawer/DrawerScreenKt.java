package github.alexzhirkevich.studentbsuby.ui.screens.drawer;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000h\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aP\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u001e\b\u0002\u0010\u0010\u001a\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00070\u0011\u00a2\u0006\u0002\b\u0013\u00a2\u0006\u0002\b\u0014H\u0003\u001aF\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u000e\b\u0002\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070\rH\u0003\u001a\u001a\u0010 \u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0007\u001a.\u0010!\u001a\u00020\u00072\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020&0#2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0003\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0003\u001a\u00020\u0001X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0004\u001a\u00020\u0001X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0005\u001a\u00020\u0001X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\'"}, d2 = {"AvatarHeight", "Landroidx/compose/ui/unit/Dp;", "F", "AvatarWidth", "CardPaddings", "CardTopPadding", "DrawerButton", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "text", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "DrawerContent", "navController", "Landroidx/navigation/NavController;", "childNavController", "routes", "", "Lgithub/alexzhirkevich/studentbsuby/ui/screens/drawer/DrawerRoute;", "initial", "profileViewModel", "Lgithub/alexzhirkevich/studentbsuby/ui/screens/drawer/ProfileViewModel;", "onRouteSelected", "DrawerScreen", "ProfileCard", "photo", "Lgithub/alexzhirkevich/studentbsuby/util/DataState;", "Landroidx/compose/ui/graphics/ImageBitmap;", "user", "Lgithub/alexzhirkevich/studentbsuby/data/models/User;", "app_release"})
public final class DrawerScreenKt {
    private static final float AvatarWidth = 0.0F;
    private static final float AvatarHeight = 0.0F;
    private static final float CardTopPadding = 0.0F;
    private static final float CardPaddings = 0.0F;
    
    @androidx.compose.runtime.Composable()
    @com.google.accompanist.pager.ExperimentalPagerApi()
    @kotlinx.coroutines.ExperimentalCoroutinesApi()
    @me.onebone.toolbar.ExperimentalToolbarApi()
    @androidx.compose.foundation.ExperimentalFoundationApi()
    @androidx.compose.material.ExperimentalMaterialApi()
    @androidx.compose.animation.ExperimentalAnimationApi()
    @androidx.compose.ui.ExperimentalComposeUiApi()
    public static final void DrawerScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.ui.screens.drawer.ProfileViewModel profileViewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    @kotlinx.coroutines.ExperimentalCoroutinesApi()
    @com.google.accompanist.pager.ExperimentalPagerApi()
    @androidx.compose.material.ExperimentalMaterialApi()
    @androidx.compose.ui.ExperimentalComposeUiApi()
    @androidx.compose.animation.ExperimentalAnimationApi()
    @androidx.compose.foundation.ExperimentalFoundationApi()
    private static final void DrawerContent(androidx.navigation.NavController navController, androidx.navigation.NavController childNavController, java.util.List<? extends github.alexzhirkevich.studentbsuby.ui.screens.drawer.DrawerRoute> routes, github.alexzhirkevich.studentbsuby.ui.screens.drawer.DrawerRoute initial, github.alexzhirkevich.studentbsuby.ui.screens.drawer.ProfileViewModel profileViewModel, kotlin.jvm.functions.Function0<kotlin.Unit> onRouteSelected) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DrawerButton(androidx.compose.ui.graphics.vector.ImageVector icon, java.lang.String text, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, androidx.compose.ui.Modifier modifier, kotlin.jvm.functions.Function1<? super androidx.compose.foundation.layout.RowScope, kotlin.Unit> content) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ProfileCard(github.alexzhirkevich.studentbsuby.util.DataState<? extends androidx.compose.ui.graphics.ImageBitmap> photo, github.alexzhirkevich.studentbsuby.util.DataState<github.alexzhirkevich.studentbsuby.data.models.User> user, androidx.compose.ui.Modifier modifier) {
    }
}