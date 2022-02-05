package github.alexzhirkevich.studentbsuby.di;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.android.components.ViewModelComponent.class})
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\t\u001a\u00020\nH\'J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\u0006\u0010\r\u001a\u00020\u000eH\'\u00a8\u0006\u000f"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/di/RepoModule;", "", "bindCurrentSemesterRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/Repository;", "", "repository", "Lgithub/alexzhirkevich/studentbsuby/repo/CurrentSemesterRepository;", "bindPhotoRepository", "Landroid/graphics/Bitmap;", "photoRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/PhotoRepository;", "bindUserRepository", "Lgithub/alexzhirkevich/studentbsuby/data/models/User;", "userRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/UserRepository;", "app_release"})
@dagger.Module()
public abstract interface RepoModule {
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract github.alexzhirkevich.studentbsuby.repo.Repository<android.graphics.Bitmap> bindPhotoRepository(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.PhotoRepository photoRepository);
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract github.alexzhirkevich.studentbsuby.repo.Repository<github.alexzhirkevich.studentbsuby.data.models.User> bindUserRepository(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.UserRepository userRepository);
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract github.alexzhirkevich.studentbsuby.repo.Repository<java.lang.Integer> bindCurrentSemesterRepository(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.CurrentSemesterRepository repository);
}