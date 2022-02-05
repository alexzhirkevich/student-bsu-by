package github.alexzhirkevich.studentbsuby.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import github.alexzhirkevich.studentbsuby.data.models.Subject;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SubjectsDao_Impl implements SubjectsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Subject> __insertionAdapterOfSubject;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public SubjectsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSubject = new EntityInsertionAdapter<Subject>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Subject` (`id`,`owner`,`name`,`lectures`,`practice`,`labs`,`seminars`,`facults`,`ksr`,`has_credit`,`credit_passed`,`credit_mark`,`credit_retakes`,`has_exam`,`exam_mark`,`exam_retakes`,`semester`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Subject value) {
        stmt.bindLong(1, value.getId());
        if (value.getOwner() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getOwner());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        stmt.bindLong(4, value.getLectures());
        stmt.bindLong(5, value.getPractice());
        stmt.bindLong(6, value.getLabs());
        stmt.bindLong(7, value.getSeminars());
        stmt.bindLong(8, value.getFacults());
        stmt.bindLong(9, value.getKsr());
        final int _tmp = value.getHasCredit() ? 1 : 0;
        stmt.bindLong(10, _tmp);
        final Integer _tmp_1 = value.getCreditPassed() == null ? null : (value.getCreditPassed() ? 1 : 0);
        if (_tmp_1 == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindLong(11, _tmp_1);
        }
        if (value.getCreditMark() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindLong(12, value.getCreditMark());
        }
        stmt.bindLong(13, value.getCreditRetakes());
        final int _tmp_2 = value.getHasExam() ? 1 : 0;
        stmt.bindLong(14, _tmp_2);
        if (value.getExamMark() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindLong(15, value.getExamMark());
        }
        stmt.bindLong(16, value.getExamRetakes());
        stmt.bindLong(17, value.getSemester());
      }
    };
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Subject WHERE owner = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final Subject subject, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSubject.insert(subject);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object clear(final String username, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClear.acquire();
        int _argIndex = 1;
        if (username == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, username);
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfClear.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getAll(final String username,
      final Continuation<? super List<Subject>> continuation) {
    final String _sql = "SELECT *  FROM Subject WHERE owner = ? ORDER BY semester";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Subject>>() {
      @Override
      public List<Subject> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "owner");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfLectures = CursorUtil.getColumnIndexOrThrow(_cursor, "lectures");
          final int _cursorIndexOfPractice = CursorUtil.getColumnIndexOrThrow(_cursor, "practice");
          final int _cursorIndexOfLabs = CursorUtil.getColumnIndexOrThrow(_cursor, "labs");
          final int _cursorIndexOfSeminars = CursorUtil.getColumnIndexOrThrow(_cursor, "seminars");
          final int _cursorIndexOfFacults = CursorUtil.getColumnIndexOrThrow(_cursor, "facults");
          final int _cursorIndexOfKsr = CursorUtil.getColumnIndexOrThrow(_cursor, "ksr");
          final int _cursorIndexOfHasCredit = CursorUtil.getColumnIndexOrThrow(_cursor, "has_credit");
          final int _cursorIndexOfCreditPassed = CursorUtil.getColumnIndexOrThrow(_cursor, "credit_passed");
          final int _cursorIndexOfCreditMark = CursorUtil.getColumnIndexOrThrow(_cursor, "credit_mark");
          final int _cursorIndexOfCreditRetakes = CursorUtil.getColumnIndexOrThrow(_cursor, "credit_retakes");
          final int _cursorIndexOfHasExam = CursorUtil.getColumnIndexOrThrow(_cursor, "has_exam");
          final int _cursorIndexOfExamMark = CursorUtil.getColumnIndexOrThrow(_cursor, "exam_mark");
          final int _cursorIndexOfExamRetakes = CursorUtil.getColumnIndexOrThrow(_cursor, "exam_retakes");
          final int _cursorIndexOfSemester = CursorUtil.getColumnIndexOrThrow(_cursor, "semester");
          final List<Subject> _result = new ArrayList<Subject>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Subject _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOwner;
            if (_cursor.isNull(_cursorIndexOfOwner)) {
              _tmpOwner = null;
            } else {
              _tmpOwner = _cursor.getString(_cursorIndexOfOwner);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpLectures;
            _tmpLectures = _cursor.getInt(_cursorIndexOfLectures);
            final int _tmpPractice;
            _tmpPractice = _cursor.getInt(_cursorIndexOfPractice);
            final int _tmpLabs;
            _tmpLabs = _cursor.getInt(_cursorIndexOfLabs);
            final int _tmpSeminars;
            _tmpSeminars = _cursor.getInt(_cursorIndexOfSeminars);
            final int _tmpFacults;
            _tmpFacults = _cursor.getInt(_cursorIndexOfFacults);
            final int _tmpKsr;
            _tmpKsr = _cursor.getInt(_cursorIndexOfKsr);
            final boolean _tmpHasCredit;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCredit);
            _tmpHasCredit = _tmp != 0;
            final Boolean _tmpCreditPassed;
            final Integer _tmp_1;
            if (_cursor.isNull(_cursorIndexOfCreditPassed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getInt(_cursorIndexOfCreditPassed);
            }
            _tmpCreditPassed = _tmp_1 == null ? null : _tmp_1 != 0;
            final Integer _tmpCreditMark;
            if (_cursor.isNull(_cursorIndexOfCreditMark)) {
              _tmpCreditMark = null;
            } else {
              _tmpCreditMark = _cursor.getInt(_cursorIndexOfCreditMark);
            }
            final int _tmpCreditRetakes;
            _tmpCreditRetakes = _cursor.getInt(_cursorIndexOfCreditRetakes);
            final boolean _tmpHasExam;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfHasExam);
            _tmpHasExam = _tmp_2 != 0;
            final Integer _tmpExamMark;
            if (_cursor.isNull(_cursorIndexOfExamMark)) {
              _tmpExamMark = null;
            } else {
              _tmpExamMark = _cursor.getInt(_cursorIndexOfExamMark);
            }
            final int _tmpExamRetakes;
            _tmpExamRetakes = _cursor.getInt(_cursorIndexOfExamRetakes);
            final int _tmpSemester;
            _tmpSemester = _cursor.getInt(_cursorIndexOfSemester);
            _item = new Subject(_tmpId,_tmpOwner,_tmpName,_tmpLectures,_tmpPractice,_tmpLabs,_tmpSeminars,_tmpFacults,_tmpKsr,_tmpHasCredit,_tmpCreditPassed,_tmpCreditMark,_tmpCreditRetakes,_tmpHasExam,_tmpExamMark,_tmpExamRetakes,_tmpSemester);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
