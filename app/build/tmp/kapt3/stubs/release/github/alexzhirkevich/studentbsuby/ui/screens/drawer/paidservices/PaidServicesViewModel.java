package github.alexzhirkevich.studentbsuby.ui.screens.drawer.paidservices;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B!\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0006\u00104\u001a\u000205J\b\u00106\u001a\u000205H\u0016J~\u00106\u001a\u00020 \"\u0004\b\u0000\u001072\u0012\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H70\r092\u0018\u0010:\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H70\r0\f0;2\u001e\u0010<\u001a\u001a\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H70\r0\f\u0012\u0004\u0012\u0002050=2\b\b\u0001\u0010>\u001a\u00020/2\u0006\u0010?\u001a\u00020@2\u000e\u0010A\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u000bH\u0002J\u0010\u0010B\u001a\u00020 2\u0006\u00108\u001a\u00020CH\u0002J\u0010\u0010D\u001a\u00020 2\u0006\u00108\u001a\u00020CH\u0002J\u0010\u0010E\u001a\u00020 2\u0006\u00108\u001a\u00020CH\u0002J\u0018\u0010F\u001a\u0002052\u0006\u00108\u001a\u00020C2\u0006\u0010G\u001a\u00020\u0013H\u0002J\u0010\u0010H\u001a\u00020 2\u0006\u00108\u001a\u00020CH\u0002J\u0010\u0010I\u001a\u00020 2\u0006\u00108\u001a\u00020CH\u0002R \u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\r0\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\r0\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\r0\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR#\u0010\u001d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0016\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010!\u001a\u00020\"\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R#\u0010%\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\r0\f0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001cR\u0016\u0010\'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00130\u001a8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b(\u0010\u001cR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\f0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001cR#\u0010+\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\r0\f0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001cR\u0010\u0010-\u001a\u0004\u0018\u00010 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082D\u00a2\u0006\u0002\n\u0000R\u0016\u00100\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R#\u00101\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\r0\f0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001cR\u000e\u00103\u001a\u00020/X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006J"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/ui/screens/drawer/paidservices/PaidServicesViewModel;", "Landroidx/lifecycle/ViewModel;", "Lgithub/alexzhirkevich/studentbsuby/util/Updatable;", "repo", "Lgithub/alexzhirkevich/studentbsuby/repo/PaidServicesRepository;", "context", "Landroid/content/Context;", "logger", "Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;", "(Lgithub/alexzhirkevich/studentbsuby/repo/PaidServicesRepository;Landroid/content/Context;Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;)V", "_academDebtReceipts", "Landroidx/compose/runtime/MutableState;", "Lgithub/alexzhirkevich/studentbsuby/util/DataState;", "", "Lgithub/alexzhirkevich/studentbsuby/data/models/Receipt;", "_commonReceipts", "_hostelBills", "Lgithub/alexzhirkevich/studentbsuby/data/models/Bill;", "_isUpdating", "", "_paidInfo", "Lgithub/alexzhirkevich/studentbsuby/data/models/PaidServicesInfo;", "_paidInfoBills", "_tuitionFeeReceipts", "Lgithub/alexzhirkevich/studentbsuby/data/models/TuitionFeePayment;", "academDebtReceipts", "Landroidx/compose/runtime/State;", "getAcademDebtReceipts", "()Landroidx/compose/runtime/State;", "commonReceipts", "getCommonReceipts", "commonReceiptsJob", "Lkotlinx/coroutines/Job;", "dateFormat", "Ljava/text/DateFormat;", "getDateFormat", "()Ljava/text/DateFormat;", "hostelBills", "getHostelBills", "hostelBillsJob", "isUpdating", "paidInfo", "getPaidInfo", "paidInfoBills", "getPaidInfoBills", "paidInfoJob", "totalComponents", "", "tuitionFeeJob", "tuitionFeeReceipts", "getTuitionFeeReceipts", "updatedComponents", "onEripHelpClicked", "", "update", "T", "dataSource", "Lkotlinx/coroutines/flow/Flow;", "getValue", "Lkotlin/Function0;", "setValue", "Lkotlin/Function1;", "error", "logMessage", "", "job", "updateAcademDebtReceipts", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource;", "updateCommonReceipts", "updateHostelBills", "updateInternal", "setState", "updatePaidInfoAndBills", "updateTuitionFeeReceipts", "app_release"})
public final class PaidServicesViewModel extends androidx.lifecycle.ViewModel implements github.alexzhirkevich.studentbsuby.util.Updatable {
    private final github.alexzhirkevich.studentbsuby.repo.PaidServicesRepository repo = null;
    private final android.content.Context context = null;
    private final github.alexzhirkevich.studentbsuby.util.logger.Logger logger = null;
    @org.jetbrains.annotations.NotNull()
    private final java.text.DateFormat dateFormat = null;
    private final androidx.compose.runtime.MutableState<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Bill>>> _paidInfoBills = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Bill>>> paidInfoBills = null;
    private final androidx.compose.runtime.MutableState<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Bill>>> _hostelBills = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Bill>>> hostelBills = null;
    private final androidx.compose.runtime.MutableState<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.TuitionFeePayment>>> _tuitionFeeReceipts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.TuitionFeePayment>>> tuitionFeeReceipts = null;
    private final androidx.compose.runtime.MutableState<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Receipt>>> _academDebtReceipts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Receipt>>> academDebtReceipts = null;
    private final androidx.compose.runtime.MutableState<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Receipt>>> _commonReceipts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Receipt>>> commonReceipts = null;
    private final androidx.compose.runtime.MutableState<github.alexzhirkevich.studentbsuby.util.DataState<github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo>> _paidInfo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo>> paidInfo = null;
    private final androidx.compose.runtime.MutableState<java.lang.Boolean> _isUpdating = null;
    @kotlin.jvm.Volatile()
    private volatile int updatedComponents = 5;
    private final int totalComponents = 5;
    private kotlinx.coroutines.Job paidInfoJob;
    private androidx.compose.runtime.MutableState<kotlinx.coroutines.Job> tuitionFeeJob;
    private androidx.compose.runtime.MutableState<kotlinx.coroutines.Job> commonReceiptsJob;
    private androidx.compose.runtime.MutableState<kotlinx.coroutines.Job> hostelBillsJob;
    
    @javax.inject.Inject()
    public PaidServicesViewModel(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.PaidServicesRepository repo, @org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.util.logger.Logger logger) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.text.DateFormat getDateFormat() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Bill>>> getPaidInfoBills() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Bill>>> getHostelBills() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.TuitionFeePayment>>> getTuitionFeeReceipts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Receipt>>> getAcademDebtReceipts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Receipt>>> getCommonReceipts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo>> getPaidInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.compose.runtime.State<java.lang.Boolean> isUpdating() {
        return null;
    }
    
    public final void onEripHelpClicked() {
    }
    
    @java.lang.Override()
    public void update() {
    }
    
    private final void updateInternal(github.alexzhirkevich.studentbsuby.repo.DataSource dataSource, boolean setState) {
    }
    
    private final kotlinx.coroutines.Job updatePaidInfoAndBills(github.alexzhirkevich.studentbsuby.repo.DataSource dataSource) {
        return null;
    }
    
    private final kotlinx.coroutines.Job updateHostelBills(github.alexzhirkevich.studentbsuby.repo.DataSource dataSource) {
        return null;
    }
    
    private final kotlinx.coroutines.Job updateAcademDebtReceipts(github.alexzhirkevich.studentbsuby.repo.DataSource dataSource) {
        return null;
    }
    
    private final kotlinx.coroutines.Job updateCommonReceipts(github.alexzhirkevich.studentbsuby.repo.DataSource dataSource) {
        return null;
    }
    
    private final kotlinx.coroutines.Job updateTuitionFeeReceipts(github.alexzhirkevich.studentbsuby.repo.DataSource dataSource) {
        return null;
    }
    
    private final <T extends java.lang.Object>kotlinx.coroutines.Job update(kotlinx.coroutines.flow.Flow<? extends java.util.List<? extends T>> dataSource, kotlin.jvm.functions.Function0<? extends github.alexzhirkevich.studentbsuby.util.DataState<? extends java.util.List<? extends T>>> getValue, kotlin.jvm.functions.Function1<? super github.alexzhirkevich.studentbsuby.util.DataState<? extends java.util.List<? extends T>>, kotlin.Unit> setValue, @androidx.annotation.StringRes()
    int error, java.lang.String logMessage, androidx.compose.runtime.MutableState<kotlinx.coroutines.Job> job) {
        return null;
    }
}