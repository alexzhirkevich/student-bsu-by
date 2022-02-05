package github.alexzhirkevich.studentbsuby.data.models;

import java.lang.System;

@androidx.room.Entity()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0017\u0018\u0000 #2\u00020\u0001:\u0001#BO\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\r\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000eJ\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010!\u001a\u00020\u0003H\u0016J\b\u0010\"\u001a\u00020\u0005H\u0016R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0015\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015\u00a8\u0006$"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/data/models/Receipt;", "", "id", "", "owner", "", "deadline", "", "date", "price", "", "left", "info", "receiptType", "(ILjava/lang/String;JLjava/lang/Long;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;I)V", "getDate", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getDeadline", "()J", "getId", "()I", "getInfo", "()Ljava/lang/String;", "getLeft", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getOwner", "getPrice", "getReceiptType", "equals", "", "other", "hashCode", "toString", "Companion", "app_release"})
public class Receipt {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final int id = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String owner = null;
    private final long deadline = 0L;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long date = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Float price = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Float left = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String info = null;
    private final int receiptType = 0;
    @org.jetbrains.annotations.NotNull()
    public static final github.alexzhirkevich.studentbsuby.data.models.Receipt.Companion Companion = null;
    public static final int TYPE_COMMON = 0;
    public static final int TYPE_ACADEM_DEBT = 1;
    
    public Receipt(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String owner, long deadline, @org.jetbrains.annotations.Nullable()
    java.lang.Long date, @org.jetbrains.annotations.Nullable()
    java.lang.Float price, @org.jetbrains.annotations.Nullable()
    java.lang.Float left, @org.jetbrains.annotations.Nullable()
    java.lang.String info, int receiptType) {
        super();
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOwner() {
        return null;
    }
    
    public final long getDeadline() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getDate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float getPrice() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float getLeft() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getInfo() {
        return null;
    }
    
    public final int getReceiptType() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/data/models/Receipt$Companion;", "", "()V", "TYPE_ACADEM_DEBT", "", "TYPE_COMMON", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}