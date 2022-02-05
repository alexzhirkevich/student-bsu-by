package github.alexzhirkevich.studentbsuby.dao;

import java.lang.System;

@androidx.room.Database(entities = {github.alexzhirkevich.studentbsuby.data.models.User.class, github.alexzhirkevich.studentbsuby.data.models.Subject.class, github.alexzhirkevich.studentbsuby.data.models.Lesson.class, github.alexzhirkevich.studentbsuby.data.models.HostelAdvert.class, github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo.class, github.alexzhirkevich.studentbsuby.data.models.Bill.class, github.alexzhirkevich.studentbsuby.data.models.TuitionFeePayment.class, github.alexzhirkevich.studentbsuby.data.models.Receipt.class, github.alexzhirkevich.studentbsuby.data.models.News.class, github.alexzhirkevich.studentbsuby.data.models.NewsContent.class}, exportSchema = false, version = 1)
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&\u00a8\u0006\u000f"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/dao/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "hostelDao", "Lgithub/alexzhirkevich/studentbsuby/dao/HostelDao;", "lessonsDao", "Lgithub/alexzhirkevich/studentbsuby/dao/LessonsDao;", "newsDao", "Lgithub/alexzhirkevich/studentbsuby/dao/NewsDao;", "paidServicesDao", "Lgithub/alexzhirkevich/studentbsuby/dao/PaidServicesDao;", "subjectsDao", "Lgithub/alexzhirkevich/studentbsuby/dao/SubjectsDao;", "userDao", "Lgithub/alexzhirkevich/studentbsuby/dao/UsersDao;", "app_debug"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract github.alexzhirkevich.studentbsuby.dao.UsersDao userDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract github.alexzhirkevich.studentbsuby.dao.SubjectsDao subjectsDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract github.alexzhirkevich.studentbsuby.dao.LessonsDao lessonsDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract github.alexzhirkevich.studentbsuby.dao.HostelDao hostelDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract github.alexzhirkevich.studentbsuby.dao.PaidServicesDao paidServicesDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract github.alexzhirkevich.studentbsuby.dao.NewsDao newsDao();
}