package github.alexzhirkevich.studentbsuby.ui.theme;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H\u0016R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/ui/theme/ThemeSelectorImpl;", "Lgithub/alexzhirkevich/studentbsuby/ui/theme/ThemeSelector;", "preferences", "Landroid/content/SharedPreferences;", "initialTheme", "Lgithub/alexzhirkevich/studentbsuby/ui/theme/Theme;", "(Landroid/content/SharedPreferences;Lgithub/alexzhirkevich/studentbsuby/ui/theme/Theme;)V", "currentTheme", "Landroidx/compose/runtime/MutableState;", "getCurrentTheme", "()Landroidx/compose/runtime/MutableState;", "setTheme", "", "theme", "Companion", "app_release"})
public final class ThemeSelectorImpl implements github.alexzhirkevich.studentbsuby.ui.theme.ThemeSelector {
    private final android.content.SharedPreferences preferences = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState<github.alexzhirkevich.studentbsuby.ui.theme.Theme> currentTheme = null;
    @org.jetbrains.annotations.NotNull()
    public static final github.alexzhirkevich.studentbsuby.ui.theme.ThemeSelectorImpl.Companion Companion = null;
    
    private ThemeSelectorImpl(android.content.SharedPreferences preferences, github.alexzhirkevich.studentbsuby.ui.theme.Theme initialTheme) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.compose.runtime.MutableState<github.alexzhirkevich.studentbsuby.ui.theme.Theme> getCurrentTheme() {
        return null;
    }
    
    @java.lang.Override()
    public void setTheme(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.ui.theme.Theme theme) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\bH\u0002J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\bH\u0002\u00a8\u0006\u0011"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/ui/theme/ThemeSelectorImpl$Companion;", "", "()V", "create", "Lgithub/alexzhirkevich/studentbsuby/ui/theme/ThemeSelectorImpl;", "context", "Landroid/content/Context;", "isInSystemTheme", "", "prefs", "Landroid/content/SharedPreferences;", "setInDarkTheme", "", "preferences", "isInDarkTheme", "setInSystemTheme", "inSystemTheme", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final github.alexzhirkevich.studentbsuby.ui.theme.ThemeSelectorImpl create(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private final boolean isInSystemTheme(android.content.SharedPreferences prefs) {
            return false;
        }
        
        private final void setInSystemTheme(android.content.SharedPreferences preferences, boolean inSystemTheme) {
        }
        
        private final void setInDarkTheme(android.content.SharedPreferences preferences, boolean isInDarkTheme) {
        }
    }
}