package github.alexzhirkevich.studentbsuby.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/HostelState;", "", "NotProvided", "Provided", "Lgithub/alexzhirkevich/studentbsuby/repo/HostelState$Provided;", "Lgithub/alexzhirkevich/studentbsuby/repo/HostelState$NotProvided;", "app_release"})
public abstract interface HostelState {
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/HostelState$Provided;", "Lgithub/alexzhirkevich/studentbsuby/repo/HostelState;", "address", "", "(Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"})
    public static final class Provided implements github.alexzhirkevich.studentbsuby.repo.HostelState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String address = null;
        
        @org.jetbrains.annotations.NotNull()
        public final github.alexzhirkevich.studentbsuby.repo.HostelState.Provided copy(@org.jetbrains.annotations.NotNull()
        java.lang.String address) {
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
        
        public Provided(@org.jetbrains.annotations.NotNull()
        java.lang.String address) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAddress() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/HostelState$NotProvided;", "Lgithub/alexzhirkevich/studentbsuby/repo/HostelState;", "adverts", "", "Lgithub/alexzhirkevich/studentbsuby/data/models/HostelAdvert;", "(Ljava/util/List;)V", "getAdverts", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"})
    public static final class NotProvided implements github.alexzhirkevich.studentbsuby.repo.HostelState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<github.alexzhirkevich.studentbsuby.data.models.HostelAdvert> adverts = null;
        
        @org.jetbrains.annotations.NotNull()
        public final github.alexzhirkevich.studentbsuby.repo.HostelState.NotProvided copy(@org.jetbrains.annotations.NotNull()
        java.util.List<github.alexzhirkevich.studentbsuby.data.models.HostelAdvert> adverts) {
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
        
        public NotProvided(@org.jetbrains.annotations.NotNull()
        java.util.List<github.alexzhirkevich.studentbsuby.data.models.HostelAdvert> adverts) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<github.alexzhirkevich.studentbsuby.data.models.HostelAdvert> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<github.alexzhirkevich.studentbsuby.data.models.HostelAdvert> getAdverts() {
            return null;
        }
    }
}