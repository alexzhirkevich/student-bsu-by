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
import github.alexzhirkevich.studentbsuby.data.models.HostelAdvert;
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
public final class HostelDao_Impl implements HostelDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<HostelAdvert> __insertionAdapterOfHostelAdvert;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public HostelDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHostelAdvert = new EntityInsertionAdapter<HostelAdvert>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `HostelAdvert` (`id`,`number`,`phone`,`publisher`,`address`,`conditions`,`price`,`currency`,`publish_date`,`note`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, HostelAdvert value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getNumber());
        if (value.getPhone() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPhone());
        }
        if (value.getPublisher() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPublisher());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAddress());
        }
        if (value.getConditions() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getConditions());
        }
        if (value.getPrice() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getPrice());
        }
        if (value.getCurrency() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getCurrency());
        }
        if (value.getPublishDate() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getPublishDate());
        }
        if (value.getNote() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getNote());
        }
      }
    };
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM HostelAdvert";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final HostelAdvert value, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHostelAdvert.insert(value);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object clear(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClear.acquire();
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
  public Object getAll(final Continuation<? super List<HostelAdvert>> continuation) {
    final String _sql = "SELECT * FROM HostelAdvert";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<HostelAdvert>>() {
      @Override
      public List<HostelAdvert> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfPublisher = CursorUtil.getColumnIndexOrThrow(_cursor, "publisher");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfConditions = CursorUtil.getColumnIndexOrThrow(_cursor, "conditions");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfPublishDate = CursorUtil.getColumnIndexOrThrow(_cursor, "publish_date");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final List<HostelAdvert> _result = new ArrayList<HostelAdvert>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final HostelAdvert _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpNumber;
            _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpPublisher;
            if (_cursor.isNull(_cursorIndexOfPublisher)) {
              _tmpPublisher = null;
            } else {
              _tmpPublisher = _cursor.getString(_cursorIndexOfPublisher);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpConditions;
            if (_cursor.isNull(_cursorIndexOfConditions)) {
              _tmpConditions = null;
            } else {
              _tmpConditions = _cursor.getString(_cursorIndexOfConditions);
            }
            final String _tmpPrice;
            if (_cursor.isNull(_cursorIndexOfPrice)) {
              _tmpPrice = null;
            } else {
              _tmpPrice = _cursor.getString(_cursorIndexOfPrice);
            }
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final String _tmpPublishDate;
            if (_cursor.isNull(_cursorIndexOfPublishDate)) {
              _tmpPublishDate = null;
            } else {
              _tmpPublishDate = _cursor.getString(_cursorIndexOfPublishDate);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            _item = new HostelAdvert(_tmpId,_tmpNumber,_tmpPhone,_tmpPublisher,_tmpAddress,_tmpConditions,_tmpPrice,_tmpCurrency,_tmpPublishDate,_tmpNote);
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
