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
import github.alexzhirkevich.studentbsuby.data.models.Lesson;
import java.lang.Class;
import java.lang.Exception;
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
public final class LessonsDao_Impl implements LessonsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Lesson> __insertionAdapterOfLesson;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public LessonsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLesson = new EntityInsertionAdapter<Lesson>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Lesson` (`id`,`owner`,`day_of_week`,`number`,`name`,`place`,`type`,`teacher`,`starts`,`ends`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Lesson value) {
        stmt.bindLong(1, value.getId());
        if (value.getOwner() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getOwner());
        }
        stmt.bindLong(3, value.getDayOfWeek());
        stmt.bindLong(4, value.getNumber());
        if (value.getName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getName());
        }
        if (value.getPlace() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getPlace());
        }
        if (value.getType() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getType());
        }
        if (value.getTeacher() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getTeacher());
        }
        if (value.getStarts() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getStarts());
        }
        if (value.getEnds() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getEnds());
        }
      }
    };
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Lesson WHERE owner = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final Lesson value, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLesson.insert(value);
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
      final Continuation<? super List<Lesson>> continuation) {
    final String _sql = "SELECT * FROM Lesson WHERE owner = ? ORDER BY day_of_week AND number";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Lesson>>() {
      @Override
      public List<Lesson> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "owner");
          final int _cursorIndexOfDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "day_of_week");
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPlace = CursorUtil.getColumnIndexOrThrow(_cursor, "place");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTeacher = CursorUtil.getColumnIndexOrThrow(_cursor, "teacher");
          final int _cursorIndexOfStarts = CursorUtil.getColumnIndexOrThrow(_cursor, "starts");
          final int _cursorIndexOfEnds = CursorUtil.getColumnIndexOrThrow(_cursor, "ends");
          final List<Lesson> _result = new ArrayList<Lesson>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Lesson _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOwner;
            if (_cursor.isNull(_cursorIndexOfOwner)) {
              _tmpOwner = null;
            } else {
              _tmpOwner = _cursor.getString(_cursorIndexOfOwner);
            }
            final int _tmpDayOfWeek;
            _tmpDayOfWeek = _cursor.getInt(_cursorIndexOfDayOfWeek);
            final int _tmpNumber;
            _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpPlace;
            if (_cursor.isNull(_cursorIndexOfPlace)) {
              _tmpPlace = null;
            } else {
              _tmpPlace = _cursor.getString(_cursorIndexOfPlace);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpTeacher;
            if (_cursor.isNull(_cursorIndexOfTeacher)) {
              _tmpTeacher = null;
            } else {
              _tmpTeacher = _cursor.getString(_cursorIndexOfTeacher);
            }
            final String _tmpStarts;
            if (_cursor.isNull(_cursorIndexOfStarts)) {
              _tmpStarts = null;
            } else {
              _tmpStarts = _cursor.getString(_cursorIndexOfStarts);
            }
            final String _tmpEnds;
            if (_cursor.isNull(_cursorIndexOfEnds)) {
              _tmpEnds = null;
            } else {
              _tmpEnds = _cursor.getString(_cursorIndexOfEnds);
            }
            _item = new Lesson(_tmpId,_tmpOwner,_tmpDayOfWeek,_tmpNumber,_tmpName,_tmpPlace,_tmpType,_tmpTeacher,_tmpStarts,_tmpEnds);
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
