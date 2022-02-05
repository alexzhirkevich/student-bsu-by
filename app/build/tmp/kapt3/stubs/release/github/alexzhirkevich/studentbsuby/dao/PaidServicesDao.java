package github.alexzhirkevich.studentbsuby.dao;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\bg\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ!\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\'\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u001b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\'\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u001f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\r2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0010H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u0019\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0012H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u0019\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0014H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 \u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/dao/PaidServicesDao;", "", "clearBills", "", "username", "", "type", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearReceipts", "clearTuitionFeeReceipts", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBills", "", "Lgithub/alexzhirkevich/studentbsuby/data/models/Bill;", "getInfo", "Lgithub/alexzhirkevich/studentbsuby/data/models/PaidServicesInfo;", "getReceipts", "Lgithub/alexzhirkevich/studentbsuby/data/models/Receipt;", "getTuitionFeeReceipts", "Lgithub/alexzhirkevich/studentbsuby/data/models/TuitionFeePayment;", "insertBill", "bill", "(Lgithub/alexzhirkevich/studentbsuby/data/models/Bill;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertInfo", "info", "(Lgithub/alexzhirkevich/studentbsuby/data/models/PaidServicesInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertReceipt", "receipt", "(Lgithub/alexzhirkevich/studentbsuby/data/models/Receipt;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertTuitionFeeReceipt", "payment", "(Lgithub/alexzhirkevich/studentbsuby/data/models/TuitionFeePayment;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface PaidServicesDao {
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object insertInfo(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo info, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object insertTuitionFeeReceipt(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.data.models.TuitionFeePayment payment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object insertReceipt(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.data.models.Receipt receipt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object insertBill(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.data.models.Bill bill, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "SELECT * FROM PaidServicesInfo WHERE owner = :username LIMIT 1")
    public abstract java.lang.Object getInfo(@org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "SELECT * FROM Bill WHERE owner = :username AND billType = :type")
    public abstract java.lang.Object getBills(@org.jetbrains.annotations.NotNull()
    java.lang.String username, int type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<github.alexzhirkevich.studentbsuby.data.models.Bill>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "SELECT * FROM TuitionFeePayment WHERE owner = :username ORDER BY deadline DESC")
    public abstract java.lang.Object getTuitionFeeReceipts(@org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<github.alexzhirkevich.studentbsuby.data.models.TuitionFeePayment>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "SELECT * FROM Receipt WHERE owner = :username AND receiptType = :type ORDER BY deadline DESC")
    public abstract java.lang.Object getReceipts(@org.jetbrains.annotations.NotNull()
    java.lang.String username, int type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends github.alexzhirkevich.studentbsuby.data.models.Receipt>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "DELETE FROM TuitionFeePayment WHERE owner = :username")
    public abstract java.lang.Object clearTuitionFeeReceipts(@org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "DELETE FROM Receipt WHERE owner = :username AND receiptType = :type")
    public abstract java.lang.Object clearReceipts(@org.jetbrains.annotations.NotNull()
    java.lang.String username, int type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "DELETE FROM Bill WHERE owner = :username AND billType = :type")
    public abstract java.lang.Object clearBills(@org.jetbrains.annotations.NotNull()
    java.lang.String username, int type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}