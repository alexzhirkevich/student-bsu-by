package github.alexzhirkevich.studentbsuby.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 J\u001c\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 J\u001c\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\r0\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 J(\u0010#\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\r0\u00190\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 J\u001c\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\r0\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u00138F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u0018\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\r0\u00190\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/PaidServicesRepository;", "", "usernameProvider", "Lgithub/alexzhirkevich/studentbsuby/repo/UsernameProvider;", "api", "Lgithub/alexzhirkevich/studentbsuby/api/PaidServicesApi;", "dao", "Lgithub/alexzhirkevich/studentbsuby/dao/PaidServicesDao;", "resources", "Landroid/content/res/Resources;", "(Lgithub/alexzhirkevich/studentbsuby/repo/UsernameProvider;Lgithub/alexzhirkevich/studentbsuby/api/PaidServicesApi;Lgithub/alexzhirkevich/studentbsuby/dao/PaidServicesDao;Landroid/content/res/Resources;)V", "academDebtRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/Repository;", "", "Lgithub/alexzhirkevich/studentbsuby/data/models/Receipt;", "commonReceiptsRepository", "dateFormat", "Ljava/text/SimpleDateFormat;", "eripUrl", "", "getEripUrl", "()Ljava/lang/String;", "hostelBillsRepository", "Lgithub/alexzhirkevich/studentbsuby/data/models/Bill;", "infoAndBillsRepository", "Lkotlin/Pair;", "Lgithub/alexzhirkevich/studentbsuby/data/models/PaidServicesInfo;", "tuitionFeeRepository", "Lgithub/alexzhirkevich/studentbsuby/data/models/TuitionFeePayment;", "getAcademDebtFeeReceipts", "Lkotlinx/coroutines/flow/Flow;", "source", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource;", "getCommonReceipts", "getHostelBills", "getInfoAndBills", "getTuitionFeeReceipts", "app_debug"})
public final class PaidServicesRepository {
    private final java.text.SimpleDateFormat dateFormat = null;
    private final github.alexzhirkevich.studentbsuby.repo.Repository<kotlin.Pair<github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo, java.util.List<github.alexzhirkevich.studentbsuby.data.models.Bill>>> infoAndBillsRepository = null;
    private final github.alexzhirkevich.studentbsuby.repo.Repository<java.util.List<github.alexzhirkevich.studentbsuby.data.models.TuitionFeePayment>> tuitionFeeRepository = null;
    private final github.alexzhirkevich.studentbsuby.repo.Repository<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Receipt>> academDebtRepository = null;
    private final github.alexzhirkevich.studentbsuby.repo.Repository<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Bill>> hostelBillsRepository = null;
    private final github.alexzhirkevich.studentbsuby.repo.Repository<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Receipt>> commonReceiptsRepository = null;
    
    @javax.inject.Inject()
    public PaidServicesRepository(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.UsernameProvider usernameProvider, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.api.PaidServicesApi api, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.dao.PaidServicesDao dao, @org.jetbrains.annotations.NotNull()
    android.content.res.Resources resources) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEripUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Pair<github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo, java.util.List<github.alexzhirkevich.studentbsuby.data.models.Bill>>> getInfoAndBills(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.DataSource source) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<github.alexzhirkevich.studentbsuby.data.models.TuitionFeePayment>> getTuitionFeeReceipts(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.DataSource source) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Receipt>> getAcademDebtFeeReceipts(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.DataSource source) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Bill>> getHostelBills(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.DataSource source) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Receipt>> getCommonReceipts(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.DataSource source) {
        return null;
    }
}