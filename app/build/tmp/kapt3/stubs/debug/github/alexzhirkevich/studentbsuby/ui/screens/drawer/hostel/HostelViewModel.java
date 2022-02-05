package github.alexzhirkevich.studentbsuby.ui.screens.drawer.hostel;

import java.lang.System;

@android.annotation.SuppressLint(value = {"StaticFieldLeak"})
@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B!\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\"\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!J\u0010\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u001fH\u0002J\b\u0010$\u001a\u00020\u001bH\u0016J\b\u0010%\u001a\u00020\u001bH\u0002R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\u00188VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0019R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/ui/screens/drawer/hostel/HostelViewModel;", "Landroidx/lifecycle/ViewModel;", "Lgithub/alexzhirkevich/studentbsuby/util/Updatable;", "hostelRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/HostelRepository;", "context", "Landroid/content/Context;", "logger", "Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;", "(Lgithub/alexzhirkevich/studentbsuby/repo/HostelRepository;Landroid/content/Context;Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;)V", "_hostelState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lgithub/alexzhirkevich/studentbsuby/util/DataState;", "Lgithub/alexzhirkevich/studentbsuby/repo/HostelState;", "_isUpdating", "Landroidx/compose/runtime/MutableState;", "", "hostelState", "Lkotlinx/coroutines/flow/StateFlow;", "getHostelState", "()Lkotlinx/coroutines/flow/StateFlow;", "hostelStateJob", "Lkotlinx/coroutines/Job;", "isUpdating", "Landroidx/compose/runtime/State;", "()Landroidx/compose/runtime/State;", "call", "", "hostelAdvert", "Lgithub/alexzhirkevich/studentbsuby/data/models/HostelAdvert;", "getHostelImage", "", "ad", "Lgithub/alexzhirkevich/studentbsuby/repo/HostelState$Provided;", "showOnMap", "address", "update", "updateHostelState", "app_debug"})
public final class HostelViewModel extends androidx.lifecycle.ViewModel implements github.alexzhirkevich.studentbsuby.util.Updatable {
    private final github.alexzhirkevich.studentbsuby.repo.HostelRepository hostelRepository = null;
    private final android.content.Context context = null;
    private final github.alexzhirkevich.studentbsuby.util.logger.Logger logger = null;
    private final androidx.compose.runtime.MutableState<java.lang.Boolean> _isUpdating = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<github.alexzhirkevich.studentbsuby.util.DataState<github.alexzhirkevich.studentbsuby.repo.HostelState>> _hostelState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<github.alexzhirkevich.studentbsuby.util.DataState<github.alexzhirkevich.studentbsuby.repo.HostelState>> hostelState = null;
    private kotlinx.coroutines.Job hostelStateJob;
    
    @javax.inject.Inject()
    public HostelViewModel(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.HostelRepository hostelRepository, @org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.util.logger.Logger logger) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.compose.runtime.State<java.lang.Boolean> isUpdating() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<github.alexzhirkevich.studentbsuby.util.DataState<github.alexzhirkevich.studentbsuby.repo.HostelState>> getHostelState() {
        return null;
    }
    
    public final void call(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.data.models.HostelAdvert hostelAdvert) {
    }
    
    public final void showOnMap(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.HostelState.Provided ad) {
    }
    
    public final void showOnMap(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.data.models.HostelAdvert hostelAdvert) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHostelImage(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.HostelState.Provided ad) {
        return null;
    }
    
    @java.lang.Override()
    public void update() {
    }
    
    private final void updateHostelState() {
    }
    
    private final void showOnMap(java.lang.String address) {
    }
}