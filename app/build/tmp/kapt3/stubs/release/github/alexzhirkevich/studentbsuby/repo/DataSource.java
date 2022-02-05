package github.alexzhirkevich.studentbsuby.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007\u00a8\u0006\b"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/DataSource;", "", "All", "Local", "Remote", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource$Local;", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource$Remote;", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource$All;", "app_release"})
public abstract interface DataSource {
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/DataSource$Local;", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource;", "()V", "app_release"})
    public static final class Local implements github.alexzhirkevich.studentbsuby.repo.DataSource {
        @org.jetbrains.annotations.NotNull()
        public static final github.alexzhirkevich.studentbsuby.repo.DataSource.Local INSTANCE = null;
        
        private Local() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/DataSource$Remote;", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource;", "()V", "app_release"})
    public static final class Remote implements github.alexzhirkevich.studentbsuby.repo.DataSource {
        @org.jetbrains.annotations.NotNull()
        public static final github.alexzhirkevich.studentbsuby.repo.DataSource.Remote INSTANCE = null;
        
        private Remote() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/DataSource$All;", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource;", "()V", "app_release"})
    public static final class All implements github.alexzhirkevich.studentbsuby.repo.DataSource {
        @org.jetbrains.annotations.NotNull()
        public static final github.alexzhirkevich.studentbsuby.repo.DataSource.All INSTANCE = null;
        
        private All() {
            super();
        }
    }
}