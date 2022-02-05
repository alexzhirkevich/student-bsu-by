package github.alexzhirkevich.studentbsuby.di;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0004H\u0007\u00a8\u0006\u0014"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/di/RoomModule;", "", "()V", "provideDatabase", "Lgithub/alexzhirkevich/studentbsuby/dao/AppDatabase;", "context", "Landroid/content/Context;", "provideHostelDao", "Lgithub/alexzhirkevich/studentbsuby/dao/HostelDao;", "appDatabase", "provideLessonsDao", "Lgithub/alexzhirkevich/studentbsuby/dao/LessonsDao;", "provideNewsDao", "Lgithub/alexzhirkevich/studentbsuby/dao/NewsDao;", "providePaidServicesDao", "Lgithub/alexzhirkevich/studentbsuby/dao/PaidServicesDao;", "provideSubjectsDao", "Lgithub/alexzhirkevich/studentbsuby/dao/SubjectsDao;", "provideUsersDao", "Lgithub/alexzhirkevich/studentbsuby/dao/UsersDao;", "app_debug"})
@dagger.Module()
public final class RoomModule {
    
    public RoomModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final github.alexzhirkevich.studentbsuby.dao.AppDatabase provideDatabase(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final github.alexzhirkevich.studentbsuby.dao.UsersDao provideUsersDao(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.dao.AppDatabase appDatabase) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final github.alexzhirkevich.studentbsuby.dao.SubjectsDao provideSubjectsDao(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.dao.AppDatabase appDatabase) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final github.alexzhirkevich.studentbsuby.dao.LessonsDao provideLessonsDao(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.dao.AppDatabase appDatabase) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final github.alexzhirkevich.studentbsuby.dao.HostelDao provideHostelDao(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.dao.AppDatabase appDatabase) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final github.alexzhirkevich.studentbsuby.dao.PaidServicesDao providePaidServicesDao(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.dao.AppDatabase appDatabase) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final github.alexzhirkevich.studentbsuby.dao.NewsDao provideNewsDao(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.dao.AppDatabase appDatabase) {
        return null;
    }
}