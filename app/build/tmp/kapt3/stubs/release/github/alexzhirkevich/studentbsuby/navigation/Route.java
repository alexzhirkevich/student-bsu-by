package github.alexzhirkevich.studentbsuby.navigation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u000f\u0010\u0011B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0001\u0003\u0012\u0013\u0014\u00a8\u0006\u0015"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/navigation/Route;", "", "route", "", "(Ljava/lang/String;)V", "navArguments", "", "Landroidx/navigation/NamedNavArgument;", "getNavArguments", "()Ljava/util/List;", "getRoute", "()Ljava/lang/String;", "getArguments", "entry", "Landroidx/navigation/NavBackStackEntry;", "AuthScreen", "DrawerScreen", "SettingsScreen", "Lgithub/alexzhirkevich/studentbsuby/navigation/Route$AuthScreen;", "Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen;", "Lgithub/alexzhirkevich/studentbsuby/navigation/Route$SettingsScreen;", "app_release"})
public abstract class Route {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String route = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<androidx.navigation.NamedNavArgument> navArguments = null;
    
    private Route(java.lang.String route) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRoute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.util.List<androidx.navigation.NamedNavArgument> getNavArguments() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getArguments(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavBackStackEntry entry) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/navigation/Route$AuthScreen;", "Lgithub/alexzhirkevich/studentbsuby/navigation/Route;", "()V", "app_release"})
    public static final class AuthScreen extends github.alexzhirkevich.studentbsuby.navigation.Route {
        @org.jetbrains.annotations.NotNull()
        public static final github.alexzhirkevich.studentbsuby.navigation.Route.AuthScreen INSTANCE = null;
        
        private AuthScreen() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0016\u0018\u0000 \u00062\u00020\u0001:\u0007\u0005\u0006\u0007\b\t\n\u000bB\u0011\b\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\f"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen;", "Lgithub/alexzhirkevich/studentbsuby/navigation/Route;", "route", "", "(Ljava/lang/String;)V", "About", "DrawerScreen", "Hostel", "News", "PaidServices", "Subjects", "Timetable", "app_release"})
    public static class DrawerScreen extends github.alexzhirkevich.studentbsuby.navigation.Route {
        
        private DrawerScreen(java.lang.String route) {
            super(null);
        }
        
        @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen$Subjects;", "Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen;", "()V", "app_release"})
        public static final class Subjects extends github.alexzhirkevich.studentbsuby.navigation.Route.DrawerScreen {
            @org.jetbrains.annotations.NotNull()
            public static final github.alexzhirkevich.studentbsuby.navigation.Route.DrawerScreen.Subjects INSTANCE = null;
            
            private Subjects() {
                super(null);
            }
        }
        
        @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen$Timetable;", "Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen;", "()V", "app_release"})
        public static final class Timetable extends github.alexzhirkevich.studentbsuby.navigation.Route.DrawerScreen {
            @org.jetbrains.annotations.NotNull()
            public static final github.alexzhirkevich.studentbsuby.navigation.Route.DrawerScreen.Timetable INSTANCE = null;
            
            private Timetable() {
                super(null);
            }
        }
        
        @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen$About;", "Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen;", "()V", "app_release"})
        public static final class About extends github.alexzhirkevich.studentbsuby.navigation.Route.DrawerScreen {
            @org.jetbrains.annotations.NotNull()
            public static final github.alexzhirkevich.studentbsuby.navigation.Route.DrawerScreen.About INSTANCE = null;
            
            private About() {
                super(null);
            }
        }
        
        @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen$Hostel;", "Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen;", "()V", "app_release"})
        public static final class Hostel extends github.alexzhirkevich.studentbsuby.navigation.Route.DrawerScreen {
            @org.jetbrains.annotations.NotNull()
            public static final github.alexzhirkevich.studentbsuby.navigation.Route.DrawerScreen.Hostel INSTANCE = null;
            
            private Hostel() {
                super(null);
            }
        }
        
        @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen$PaidServices;", "Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen;", "()V", "app_release"})
        public static final class PaidServices extends github.alexzhirkevich.studentbsuby.navigation.Route.DrawerScreen {
            @org.jetbrains.annotations.NotNull()
            public static final github.alexzhirkevich.studentbsuby.navigation.Route.DrawerScreen.PaidServices INSTANCE = null;
            
            private PaidServices() {
                super(null);
            }
        }
        
        @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0016\u0018\u0000 \u00052\u00020\u0001:\u0003\u0005\u0006\u0007B\u0011\b\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\b"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen$News;", "Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen;", "route", "", "(Ljava/lang/String;)V", "News", "NewsDetail", "NewsList", "app_release"})
        public static class News extends github.alexzhirkevich.studentbsuby.navigation.Route.DrawerScreen {
            
            private News(java.lang.String route) {
                super(null);
            }
            
            @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen$News$NewsList;", "Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen$News;", "()V", "app_release"})
            public static final class NewsList extends github.alexzhirkevich.studentbsuby.navigation.Route.DrawerScreen.News {
                @org.jetbrains.annotations.NotNull()
                public static final github.alexzhirkevich.studentbsuby.navigation.Route.DrawerScreen.News.NewsList INSTANCE = null;
                
                private NewsList() {
                    super(null);
                }
            }
            
            @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016\u00a2\u0006\u0002\u0010\u000eR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0010"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen$News$NewsDetail;", "Lgithub/alexzhirkevich/studentbsuby/navigation/Route$DrawerScreen$News;", "id", "", "(Ljava/lang/String;)V", "navArguments", "", "Landroidx/navigation/NamedNavArgument;", "getNavArguments", "()Ljava/util/List;", "getArguments", "", "entry", "Landroidx/navigation/NavBackStackEntry;", "(Landroidx/navigation/NavBackStackEntry;)Ljava/lang/Integer;", "NewsDetail", "app_release"})
            public static class NewsDetail extends github.alexzhirkevich.studentbsuby.navigation.Route.DrawerScreen.News {
                
                public NewsDetail(@org.jetbrains.annotations.NotNull()
                java.lang.String id) {
                    super(null);
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public java.util.List<androidx.navigation.NamedNavArgument> getNavArguments() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public java.lang.Integer getArguments(@org.jetbrains.annotations.NotNull()
                androidx.navigation.NavBackStackEntry entry) {
                    return null;
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/navigation/Route$SettingsScreen;", "Lgithub/alexzhirkevich/studentbsuby/navigation/Route;", "()V", "app_release"})
    public static final class SettingsScreen extends github.alexzhirkevich.studentbsuby.navigation.Route {
        @org.jetbrains.annotations.NotNull()
        public static final github.alexzhirkevich.studentbsuby.navigation.Route.SettingsScreen INSTANCE = null;
        
        private SettingsScreen() {
            super(null);
        }
    }
}