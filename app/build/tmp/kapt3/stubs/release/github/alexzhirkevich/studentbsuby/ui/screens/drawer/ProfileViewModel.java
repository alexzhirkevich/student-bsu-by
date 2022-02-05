package github.alexzhirkevich.studentbsuby.ui.screens.drawer;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@com.google.accompanist.pager.ExperimentalPagerApi()
@kotlinx.coroutines.ExperimentalCoroutinesApi()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B;\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\u0006\u0010\u001b\u001a\u00020\u001cJ\b\u0010\u001d\u001a\u00020\u001cH\u0014J\b\u0010\u001e\u001a\u00020\u001cH\u0002J\b\u0010\u001f\u001a\u00020\u001cH\u0002R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00100\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/ui/screens/drawer/ProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "photoRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/Repository;", "Landroid/graphics/Bitmap;", "userRepository", "Lgithub/alexzhirkevich/studentbsuby/data/models/User;", "loginRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/LoginRepository;", "logger", "Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;", "synchronizationWorkerManager", "Lgithub/alexzhirkevich/studentbsuby/workers/SynchronizationWorkerManager;", "(Lgithub/alexzhirkevich/studentbsuby/repo/Repository;Lgithub/alexzhirkevich/studentbsuby/repo/Repository;Lgithub/alexzhirkevich/studentbsuby/repo/LoginRepository;Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;Lgithub/alexzhirkevich/studentbsuby/workers/SynchronizationWorkerManager;)V", "_photo", "Landroidx/compose/runtime/MutableState;", "Lgithub/alexzhirkevich/studentbsuby/util/DataState;", "Landroidx/compose/ui/graphics/ImageBitmap;", "_user", "photo", "Landroidx/compose/runtime/State;", "getPhoto", "()Landroidx/compose/runtime/State;", "photoJob", "Lkotlinx/coroutines/Job;", "user", "getUser", "logout", "", "onCleared", "updatePhoto", "updateUser", "app_release"})
@androidx.compose.foundation.ExperimentalFoundationApi()
@androidx.compose.animation.ExperimentalAnimationApi()
@androidx.compose.ui.ExperimentalComposeUiApi()
@androidx.compose.material.ExperimentalMaterialApi()
public final class ProfileViewModel extends androidx.lifecycle.ViewModel {
    private final github.alexzhirkevich.studentbsuby.repo.Repository<android.graphics.Bitmap> photoRepository = null;
    private final github.alexzhirkevich.studentbsuby.repo.Repository<github.alexzhirkevich.studentbsuby.data.models.User> userRepository = null;
    private final github.alexzhirkevich.studentbsuby.repo.LoginRepository loginRepository = null;
    private final github.alexzhirkevich.studentbsuby.util.logger.Logger logger = null;
    private final github.alexzhirkevich.studentbsuby.workers.SynchronizationWorkerManager synchronizationWorkerManager = null;
    private final androidx.compose.runtime.MutableState<github.alexzhirkevich.studentbsuby.util.DataState<androidx.compose.ui.graphics.ImageBitmap>> _photo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<androidx.compose.ui.graphics.ImageBitmap>> photo = null;
    private final androidx.compose.runtime.MutableState<github.alexzhirkevich.studentbsuby.util.DataState<github.alexzhirkevich.studentbsuby.data.models.User>> _user = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<github.alexzhirkevich.studentbsuby.data.models.User>> user = null;
    private final androidx.compose.runtime.MutableState<kotlinx.coroutines.Job> photoJob = null;
    
    @javax.inject.Inject()
    public ProfileViewModel(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.Repository<android.graphics.Bitmap> photoRepository, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.Repository<github.alexzhirkevich.studentbsuby.data.models.User> userRepository, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.LoginRepository loginRepository, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.util.logger.Logger logger, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.workers.SynchronizationWorkerManager synchronizationWorkerManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<androidx.compose.ui.graphics.ImageBitmap>> getPhoto() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<github.alexzhirkevich.studentbsuby.data.models.User>> getUser() {
        return null;
    }
    
    private final void updatePhoto() {
    }
    
    private final void updateUser() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void logout() {
    }
}