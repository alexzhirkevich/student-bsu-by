package github.alexzhirkevich.studentbsuby.data.models;

import java.lang.System;

@androidx.room.Entity()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001Bg\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\n\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u0011J\u0013\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010(\u001a\u00020\u0003H\u0016J\b\u0010)\u001a\u00020\u0005H\u0016R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\n\u00a2\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u000f\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\f\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b \u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0015\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b#\u0010\u0018R\u0011\u0010\r\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\"\u00a8\u0006*"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/data/models/TuitionFeePayment;", "", "id", "", "owner", "", "deadline", "", "date", "price", "", "left", "fullPrice", "year", "fineDays", "fineSize", "debt", "(ILjava/lang/String;JLjava/lang/Long;Ljava/lang/Float;Ljava/lang/Float;FLjava/lang/String;IFLjava/lang/Float;)V", "getDate", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getDeadline", "()J", "getDebt", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getFineDays", "()I", "getFineSize", "()F", "getFullPrice", "getId", "getLeft", "getOwner", "()Ljava/lang/String;", "getPrice", "getYear", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class TuitionFeePayment {
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
    @androidx.room.ColumnInfo(name = "fUll_price")
    private final float fullPrice = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String year = null;
    @androidx.room.ColumnInfo(name = "fine_days")
    private final int fineDays = 0;
    @androidx.room.ColumnInfo(name = "fine_size")
    private final float fineSize = 0.0F;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Float debt = null;
    
    public TuitionFeePayment(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String owner, long deadline, @org.jetbrains.annotations.Nullable()
    java.lang.Long date, @org.jetbrains.annotations.Nullable()
    java.lang.Float price, @org.jetbrains.annotations.Nullable()
    java.lang.Float left, float fullPrice, @org.jetbrains.annotations.NotNull()
    java.lang.String year, int fineDays, float fineSize, @org.jetbrains.annotations.Nullable()
    java.lang.Float debt) {
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
    
    public final float getFullPrice() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getYear() {
        return null;
    }
    
    public final int getFineDays() {
        return 0;
    }
    
    public final float getFineSize() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float getDebt() {
        return null;
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
}