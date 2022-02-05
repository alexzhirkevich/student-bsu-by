package github.alexzhirkevich.studentbsuby.ui.theme;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\n"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/ui/theme/ThemeSelector;", "", "currentTheme", "Landroidx/compose/runtime/State;", "Lgithub/alexzhirkevich/studentbsuby/ui/theme/Theme;", "getCurrentTheme", "()Landroidx/compose/runtime/State;", "setTheme", "", "theme", "app_debug"})
public abstract interface ThemeSelector {
    
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.ui.theme.Theme> getCurrentTheme();
    
    public abstract void setTheme(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.ui.theme.Theme theme);
}