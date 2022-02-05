package github.alexzhirkevich.studentbsuby.dao;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UsersDao _usersDao;

  private volatile SubjectsDao _subjectsDao;

  private volatile LessonsDao _lessonsDao;

  private volatile HostelDao _hostelDao;

  private volatile PaidServicesDao _paidServicesDao;

  private volatile NewsDao _newsDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `User` (`username` TEXT NOT NULL, `name` TEXT NOT NULL, `faculty` TEXT NOT NULL, `info` TEXT NOT NULL, `avgGrade` TEXT NOT NULL, PRIMARY KEY(`username`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Subject` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `owner` TEXT NOT NULL, `name` TEXT NOT NULL, `lectures` INTEGER NOT NULL, `practice` INTEGER NOT NULL, `labs` INTEGER NOT NULL, `seminars` INTEGER NOT NULL, `facults` INTEGER NOT NULL, `ksr` INTEGER NOT NULL, `has_credit` INTEGER NOT NULL, `credit_passed` INTEGER, `credit_mark` INTEGER, `credit_retakes` INTEGER NOT NULL, `has_exam` INTEGER NOT NULL, `exam_mark` INTEGER, `exam_retakes` INTEGER NOT NULL, `semester` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Lesson` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `owner` TEXT NOT NULL, `day_of_week` INTEGER NOT NULL, `number` INTEGER NOT NULL, `name` TEXT NOT NULL, `place` TEXT NOT NULL, `type` TEXT NOT NULL, `teacher` TEXT NOT NULL, `starts` TEXT NOT NULL, `ends` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `HostelAdvert` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `number` INTEGER NOT NULL, `phone` TEXT, `publisher` TEXT, `address` TEXT, `conditions` TEXT, `price` TEXT, `currency` TEXT, `publish_date` TEXT NOT NULL, `note` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `PaidServicesInfo` (`owner` TEXT NOT NULL, `contract_number` TEXT NOT NULL, `debt` REAL NOT NULL, `fine` REAL NOT NULL, `price` REAL, PRIMARY KEY(`owner`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Bill` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `owner` TEXT NOT NULL, `paymentType` TEXT NOT NULL, `deadline` INTEGER NOT NULL, `price` REAL NOT NULL, `billType` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TuitionFeePayment` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `owner` TEXT NOT NULL, `deadline` INTEGER NOT NULL, `date` INTEGER, `price` REAL, `left` REAL, `fUll_price` REAL NOT NULL, `year` TEXT NOT NULL, `fine_days` INTEGER NOT NULL, `fine_size` REAL NOT NULL, `debt` REAL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Receipt` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `owner` TEXT NOT NULL, `deadline` INTEGER NOT NULL, `date` INTEGER, `price` REAL, `left` REAL, `info` TEXT, `receiptType` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `News` (`id` INTEGER NOT NULL, `title` TEXT NOT NULL, `preview` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `NewsContent` (`id` INTEGER NOT NULL, `content` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'feb50b560e03c57bc92fb4176bd54591')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `User`");
        _db.execSQL("DROP TABLE IF EXISTS `Subject`");
        _db.execSQL("DROP TABLE IF EXISTS `Lesson`");
        _db.execSQL("DROP TABLE IF EXISTS `HostelAdvert`");
        _db.execSQL("DROP TABLE IF EXISTS `PaidServicesInfo`");
        _db.execSQL("DROP TABLE IF EXISTS `Bill`");
        _db.execSQL("DROP TABLE IF EXISTS `TuitionFeePayment`");
        _db.execSQL("DROP TABLE IF EXISTS `Receipt`");
        _db.execSQL("DROP TABLE IF EXISTS `News`");
        _db.execSQL("DROP TABLE IF EXISTS `NewsContent`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(5);
        _columnsUser.put("username", new TableInfo.Column("username", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("faculty", new TableInfo.Column("faculty", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("info", new TableInfo.Column("info", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("avgGrade", new TableInfo.Column("avgGrade", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUser = new TableInfo("User", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(_db, "User");
        if (! _infoUser.equals(_existingUser)) {
          return new RoomOpenHelper.ValidationResult(false, "User(github.alexzhirkevich.studentbsuby.data.models.User).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
        final HashMap<String, TableInfo.Column> _columnsSubject = new HashMap<String, TableInfo.Column>(17);
        _columnsSubject.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("owner", new TableInfo.Column("owner", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("lectures", new TableInfo.Column("lectures", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("practice", new TableInfo.Column("practice", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("labs", new TableInfo.Column("labs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("seminars", new TableInfo.Column("seminars", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("facults", new TableInfo.Column("facults", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("ksr", new TableInfo.Column("ksr", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("has_credit", new TableInfo.Column("has_credit", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("credit_passed", new TableInfo.Column("credit_passed", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("credit_mark", new TableInfo.Column("credit_mark", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("credit_retakes", new TableInfo.Column("credit_retakes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("has_exam", new TableInfo.Column("has_exam", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("exam_mark", new TableInfo.Column("exam_mark", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("exam_retakes", new TableInfo.Column("exam_retakes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubject.put("semester", new TableInfo.Column("semester", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSubject = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSubject = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSubject = new TableInfo("Subject", _columnsSubject, _foreignKeysSubject, _indicesSubject);
        final TableInfo _existingSubject = TableInfo.read(_db, "Subject");
        if (! _infoSubject.equals(_existingSubject)) {
          return new RoomOpenHelper.ValidationResult(false, "Subject(github.alexzhirkevich.studentbsuby.data.models.Subject).\n"
                  + " Expected:\n" + _infoSubject + "\n"
                  + " Found:\n" + _existingSubject);
        }
        final HashMap<String, TableInfo.Column> _columnsLesson = new HashMap<String, TableInfo.Column>(10);
        _columnsLesson.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLesson.put("owner", new TableInfo.Column("owner", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLesson.put("day_of_week", new TableInfo.Column("day_of_week", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLesson.put("number", new TableInfo.Column("number", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLesson.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLesson.put("place", new TableInfo.Column("place", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLesson.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLesson.put("teacher", new TableInfo.Column("teacher", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLesson.put("starts", new TableInfo.Column("starts", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLesson.put("ends", new TableInfo.Column("ends", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLesson = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLesson = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLesson = new TableInfo("Lesson", _columnsLesson, _foreignKeysLesson, _indicesLesson);
        final TableInfo _existingLesson = TableInfo.read(_db, "Lesson");
        if (! _infoLesson.equals(_existingLesson)) {
          return new RoomOpenHelper.ValidationResult(false, "Lesson(github.alexzhirkevich.studentbsuby.data.models.Lesson).\n"
                  + " Expected:\n" + _infoLesson + "\n"
                  + " Found:\n" + _existingLesson);
        }
        final HashMap<String, TableInfo.Column> _columnsHostelAdvert = new HashMap<String, TableInfo.Column>(10);
        _columnsHostelAdvert.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostelAdvert.put("number", new TableInfo.Column("number", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostelAdvert.put("phone", new TableInfo.Column("phone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostelAdvert.put("publisher", new TableInfo.Column("publisher", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostelAdvert.put("address", new TableInfo.Column("address", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostelAdvert.put("conditions", new TableInfo.Column("conditions", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostelAdvert.put("price", new TableInfo.Column("price", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostelAdvert.put("currency", new TableInfo.Column("currency", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostelAdvert.put("publish_date", new TableInfo.Column("publish_date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostelAdvert.put("note", new TableInfo.Column("note", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHostelAdvert = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHostelAdvert = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHostelAdvert = new TableInfo("HostelAdvert", _columnsHostelAdvert, _foreignKeysHostelAdvert, _indicesHostelAdvert);
        final TableInfo _existingHostelAdvert = TableInfo.read(_db, "HostelAdvert");
        if (! _infoHostelAdvert.equals(_existingHostelAdvert)) {
          return new RoomOpenHelper.ValidationResult(false, "HostelAdvert(github.alexzhirkevich.studentbsuby.data.models.HostelAdvert).\n"
                  + " Expected:\n" + _infoHostelAdvert + "\n"
                  + " Found:\n" + _existingHostelAdvert);
        }
        final HashMap<String, TableInfo.Column> _columnsPaidServicesInfo = new HashMap<String, TableInfo.Column>(5);
        _columnsPaidServicesInfo.put("owner", new TableInfo.Column("owner", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaidServicesInfo.put("contract_number", new TableInfo.Column("contract_number", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaidServicesInfo.put("debt", new TableInfo.Column("debt", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaidServicesInfo.put("fine", new TableInfo.Column("fine", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaidServicesInfo.put("price", new TableInfo.Column("price", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPaidServicesInfo = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPaidServicesInfo = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPaidServicesInfo = new TableInfo("PaidServicesInfo", _columnsPaidServicesInfo, _foreignKeysPaidServicesInfo, _indicesPaidServicesInfo);
        final TableInfo _existingPaidServicesInfo = TableInfo.read(_db, "PaidServicesInfo");
        if (! _infoPaidServicesInfo.equals(_existingPaidServicesInfo)) {
          return new RoomOpenHelper.ValidationResult(false, "PaidServicesInfo(github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo).\n"
                  + " Expected:\n" + _infoPaidServicesInfo + "\n"
                  + " Found:\n" + _existingPaidServicesInfo);
        }
        final HashMap<String, TableInfo.Column> _columnsBill = new HashMap<String, TableInfo.Column>(6);
        _columnsBill.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBill.put("owner", new TableInfo.Column("owner", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBill.put("paymentType", new TableInfo.Column("paymentType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBill.put("deadline", new TableInfo.Column("deadline", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBill.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBill.put("billType", new TableInfo.Column("billType", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBill = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBill = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBill = new TableInfo("Bill", _columnsBill, _foreignKeysBill, _indicesBill);
        final TableInfo _existingBill = TableInfo.read(_db, "Bill");
        if (! _infoBill.equals(_existingBill)) {
          return new RoomOpenHelper.ValidationResult(false, "Bill(github.alexzhirkevich.studentbsuby.data.models.Bill).\n"
                  + " Expected:\n" + _infoBill + "\n"
                  + " Found:\n" + _existingBill);
        }
        final HashMap<String, TableInfo.Column> _columnsTuitionFeePayment = new HashMap<String, TableInfo.Column>(11);
        _columnsTuitionFeePayment.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTuitionFeePayment.put("owner", new TableInfo.Column("owner", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTuitionFeePayment.put("deadline", new TableInfo.Column("deadline", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTuitionFeePayment.put("date", new TableInfo.Column("date", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTuitionFeePayment.put("price", new TableInfo.Column("price", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTuitionFeePayment.put("left", new TableInfo.Column("left", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTuitionFeePayment.put("fUll_price", new TableInfo.Column("fUll_price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTuitionFeePayment.put("year", new TableInfo.Column("year", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTuitionFeePayment.put("fine_days", new TableInfo.Column("fine_days", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTuitionFeePayment.put("fine_size", new TableInfo.Column("fine_size", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTuitionFeePayment.put("debt", new TableInfo.Column("debt", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTuitionFeePayment = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTuitionFeePayment = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTuitionFeePayment = new TableInfo("TuitionFeePayment", _columnsTuitionFeePayment, _foreignKeysTuitionFeePayment, _indicesTuitionFeePayment);
        final TableInfo _existingTuitionFeePayment = TableInfo.read(_db, "TuitionFeePayment");
        if (! _infoTuitionFeePayment.equals(_existingTuitionFeePayment)) {
          return new RoomOpenHelper.ValidationResult(false, "TuitionFeePayment(github.alexzhirkevich.studentbsuby.data.models.TuitionFeePayment).\n"
                  + " Expected:\n" + _infoTuitionFeePayment + "\n"
                  + " Found:\n" + _existingTuitionFeePayment);
        }
        final HashMap<String, TableInfo.Column> _columnsReceipt = new HashMap<String, TableInfo.Column>(8);
        _columnsReceipt.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceipt.put("owner", new TableInfo.Column("owner", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceipt.put("deadline", new TableInfo.Column("deadline", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceipt.put("date", new TableInfo.Column("date", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceipt.put("price", new TableInfo.Column("price", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceipt.put("left", new TableInfo.Column("left", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceipt.put("info", new TableInfo.Column("info", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceipt.put("receiptType", new TableInfo.Column("receiptType", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReceipt = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReceipt = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReceipt = new TableInfo("Receipt", _columnsReceipt, _foreignKeysReceipt, _indicesReceipt);
        final TableInfo _existingReceipt = TableInfo.read(_db, "Receipt");
        if (! _infoReceipt.equals(_existingReceipt)) {
          return new RoomOpenHelper.ValidationResult(false, "Receipt(github.alexzhirkevich.studentbsuby.data.models.Receipt).\n"
                  + " Expected:\n" + _infoReceipt + "\n"
                  + " Found:\n" + _existingReceipt);
        }
        final HashMap<String, TableInfo.Column> _columnsNews = new HashMap<String, TableInfo.Column>(3);
        _columnsNews.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNews.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNews.put("preview", new TableInfo.Column("preview", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNews = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNews = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNews = new TableInfo("News", _columnsNews, _foreignKeysNews, _indicesNews);
        final TableInfo _existingNews = TableInfo.read(_db, "News");
        if (! _infoNews.equals(_existingNews)) {
          return new RoomOpenHelper.ValidationResult(false, "News(github.alexzhirkevich.studentbsuby.data.models.News).\n"
                  + " Expected:\n" + _infoNews + "\n"
                  + " Found:\n" + _existingNews);
        }
        final HashMap<String, TableInfo.Column> _columnsNewsContent = new HashMap<String, TableInfo.Column>(2);
        _columnsNewsContent.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNewsContent.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNewsContent = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNewsContent = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNewsContent = new TableInfo("NewsContent", _columnsNewsContent, _foreignKeysNewsContent, _indicesNewsContent);
        final TableInfo _existingNewsContent = TableInfo.read(_db, "NewsContent");
        if (! _infoNewsContent.equals(_existingNewsContent)) {
          return new RoomOpenHelper.ValidationResult(false, "NewsContent(github.alexzhirkevich.studentbsuby.data.models.NewsContent).\n"
                  + " Expected:\n" + _infoNewsContent + "\n"
                  + " Found:\n" + _existingNewsContent);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "feb50b560e03c57bc92fb4176bd54591", "dad0ae9b159258abf01ec694c6880a5c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "User","Subject","Lesson","HostelAdvert","PaidServicesInfo","Bill","TuitionFeePayment","Receipt","News","NewsContent");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `User`");
      _db.execSQL("DELETE FROM `Subject`");
      _db.execSQL("DELETE FROM `Lesson`");
      _db.execSQL("DELETE FROM `HostelAdvert`");
      _db.execSQL("DELETE FROM `PaidServicesInfo`");
      _db.execSQL("DELETE FROM `Bill`");
      _db.execSQL("DELETE FROM `TuitionFeePayment`");
      _db.execSQL("DELETE FROM `Receipt`");
      _db.execSQL("DELETE FROM `News`");
      _db.execSQL("DELETE FROM `NewsContent`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UsersDao.class, UsersDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SubjectsDao.class, SubjectsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LessonsDao.class, LessonsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(HostelDao.class, HostelDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PaidServicesDao.class, PaidServicesDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(NewsDao.class, NewsDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public UsersDao userDao() {
    if (_usersDao != null) {
      return _usersDao;
    } else {
      synchronized(this) {
        if(_usersDao == null) {
          _usersDao = new UsersDao_Impl(this);
        }
        return _usersDao;
      }
    }
  }

  @Override
  public SubjectsDao subjectsDao() {
    if (_subjectsDao != null) {
      return _subjectsDao;
    } else {
      synchronized(this) {
        if(_subjectsDao == null) {
          _subjectsDao = new SubjectsDao_Impl(this);
        }
        return _subjectsDao;
      }
    }
  }

  @Override
  public LessonsDao lessonsDao() {
    if (_lessonsDao != null) {
      return _lessonsDao;
    } else {
      synchronized(this) {
        if(_lessonsDao == null) {
          _lessonsDao = new LessonsDao_Impl(this);
        }
        return _lessonsDao;
      }
    }
  }

  @Override
  public HostelDao hostelDao() {
    if (_hostelDao != null) {
      return _hostelDao;
    } else {
      synchronized(this) {
        if(_hostelDao == null) {
          _hostelDao = new HostelDao_Impl(this);
        }
        return _hostelDao;
      }
    }
  }

  @Override
  public PaidServicesDao paidServicesDao() {
    if (_paidServicesDao != null) {
      return _paidServicesDao;
    } else {
      synchronized(this) {
        if(_paidServicesDao == null) {
          _paidServicesDao = new PaidServicesDao_Impl(this);
        }
        return _paidServicesDao;
      }
    }
  }

  @Override
  public NewsDao newsDao() {
    if (_newsDao != null) {
      return _newsDao;
    } else {
      synchronized(this) {
        if(_newsDao == null) {
          _newsDao = new NewsDao_Impl(this);
        }
        return _newsDao;
      }
    }
  }
}
