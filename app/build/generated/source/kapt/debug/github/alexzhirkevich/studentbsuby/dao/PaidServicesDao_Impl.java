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
import github.alexzhirkevich.studentbsuby.data.models.Bill;
import github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo;
import github.alexzhirkevich.studentbsuby.data.models.Receipt;
import github.alexzhirkevich.studentbsuby.data.models.TuitionFeePayment;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Long;
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
public final class PaidServicesDao_Impl implements PaidServicesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PaidServicesInfo> __insertionAdapterOfPaidServicesInfo;

  private final EntityInsertionAdapter<TuitionFeePayment> __insertionAdapterOfTuitionFeePayment;

  private final EntityInsertionAdapter<Receipt> __insertionAdapterOfReceipt;

  private final EntityInsertionAdapter<Bill> __insertionAdapterOfBill;

  private final SharedSQLiteStatement __preparedStmtOfClearTuitionFeeReceipts;

  private final SharedSQLiteStatement __preparedStmtOfClearReceipts;

  private final SharedSQLiteStatement __preparedStmtOfClearBills;

  public PaidServicesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPaidServicesInfo = new EntityInsertionAdapter<PaidServicesInfo>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `PaidServicesInfo` (`owner`,`contract_number`,`debt`,`fine`,`price`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PaidServicesInfo value) {
        if (value.getOwner() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getOwner());
        }
        if (value.getContractNumber() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getContractNumber());
        }
        stmt.bindDouble(3, value.getDebt());
        stmt.bindDouble(4, value.getFine());
        if (value.getPrice() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindDouble(5, value.getPrice());
        }
      }
    };
    this.__insertionAdapterOfTuitionFeePayment = new EntityInsertionAdapter<TuitionFeePayment>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `TuitionFeePayment` (`id`,`owner`,`deadline`,`date`,`price`,`left`,`fUll_price`,`year`,`fine_days`,`fine_size`,`debt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TuitionFeePayment value) {
        stmt.bindLong(1, value.getId());
        if (value.getOwner() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getOwner());
        }
        stmt.bindLong(3, value.getDeadline());
        if (value.getDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getDate());
        }
        if (value.getPrice() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindDouble(5, value.getPrice());
        }
        if (value.getLeft() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindDouble(6, value.getLeft());
        }
        stmt.bindDouble(7, value.getFullPrice());
        if (value.getYear() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getYear());
        }
        stmt.bindLong(9, value.getFineDays());
        stmt.bindDouble(10, value.getFineSize());
        if (value.getDebt() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindDouble(11, value.getDebt());
        }
      }
    };
    this.__insertionAdapterOfReceipt = new EntityInsertionAdapter<Receipt>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Receipt` (`id`,`owner`,`deadline`,`date`,`price`,`left`,`info`,`receiptType`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Receipt value) {
        stmt.bindLong(1, value.getId());
        if (value.getOwner() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getOwner());
        }
        stmt.bindLong(3, value.getDeadline());
        if (value.getDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getDate());
        }
        if (value.getPrice() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindDouble(5, value.getPrice());
        }
        if (value.getLeft() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindDouble(6, value.getLeft());
        }
        if (value.getInfo() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getInfo());
        }
        stmt.bindLong(8, value.getReceiptType());
      }
    };
    this.__insertionAdapterOfBill = new EntityInsertionAdapter<Bill>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Bill` (`id`,`owner`,`paymentType`,`deadline`,`price`,`billType`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Bill value) {
        stmt.bindLong(1, value.getId());
        if (value.getOwner() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getOwner());
        }
        if (value.getPaymentType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPaymentType());
        }
        stmt.bindLong(4, value.getDeadline());
        stmt.bindDouble(5, value.getPrice());
        stmt.bindLong(6, value.getBillType());
      }
    };
    this.__preparedStmtOfClearTuitionFeeReceipts = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM TuitionFeePayment WHERE owner = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearReceipts = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Receipt WHERE owner = ? AND receiptType = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearBills = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Bill WHERE owner = ? AND billType = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertInfo(final PaidServicesInfo info,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPaidServicesInfo.insert(info);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object insertTuitionFeeReceipt(final TuitionFeePayment payment,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTuitionFeePayment.insert(payment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object insertReceipt(final Receipt receipt,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfReceipt.insert(receipt);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object insertBill(final Bill bill, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBill.insert(bill);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object clearTuitionFeeReceipts(final String username,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearTuitionFeeReceipts.acquire();
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
          __preparedStmtOfClearTuitionFeeReceipts.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object clearReceipts(final String username, final int type,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearReceipts.acquire();
        int _argIndex = 1;
        if (username == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, username);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, type);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfClearReceipts.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object clearBills(final String username, final int type,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearBills.acquire();
        int _argIndex = 1;
        if (username == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, username);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, type);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfClearBills.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getInfo(final String username,
      final Continuation<? super PaidServicesInfo> continuation) {
    final String _sql = "SELECT * FROM PaidServicesInfo WHERE owner = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PaidServicesInfo>() {
      @Override
      public PaidServicesInfo call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "owner");
          final int _cursorIndexOfContractNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "contract_number");
          final int _cursorIndexOfDebt = CursorUtil.getColumnIndexOrThrow(_cursor, "debt");
          final int _cursorIndexOfFine = CursorUtil.getColumnIndexOrThrow(_cursor, "fine");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final PaidServicesInfo _result;
          if(_cursor.moveToFirst()) {
            final String _tmpOwner;
            if (_cursor.isNull(_cursorIndexOfOwner)) {
              _tmpOwner = null;
            } else {
              _tmpOwner = _cursor.getString(_cursorIndexOfOwner);
            }
            final String _tmpContractNumber;
            if (_cursor.isNull(_cursorIndexOfContractNumber)) {
              _tmpContractNumber = null;
            } else {
              _tmpContractNumber = _cursor.getString(_cursorIndexOfContractNumber);
            }
            final float _tmpDebt;
            _tmpDebt = _cursor.getFloat(_cursorIndexOfDebt);
            final float _tmpFine;
            _tmpFine = _cursor.getFloat(_cursorIndexOfFine);
            final Float _tmpPrice;
            if (_cursor.isNull(_cursorIndexOfPrice)) {
              _tmpPrice = null;
            } else {
              _tmpPrice = _cursor.getFloat(_cursorIndexOfPrice);
            }
            _result = new PaidServicesInfo(_tmpOwner,_tmpContractNumber,_tmpDebt,_tmpFine,_tmpPrice);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object getBills(final String username, final int type,
      final Continuation<? super List<Bill>> continuation) {
    final String _sql = "SELECT * FROM Bill WHERE owner = ? AND billType = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, type);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Bill>>() {
      @Override
      public List<Bill> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "owner");
          final int _cursorIndexOfPaymentType = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentType");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfBillType = CursorUtil.getColumnIndexOrThrow(_cursor, "billType");
          final List<Bill> _result = new ArrayList<Bill>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Bill _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOwner;
            if (_cursor.isNull(_cursorIndexOfOwner)) {
              _tmpOwner = null;
            } else {
              _tmpOwner = _cursor.getString(_cursorIndexOfOwner);
            }
            final String _tmpPaymentType;
            if (_cursor.isNull(_cursorIndexOfPaymentType)) {
              _tmpPaymentType = null;
            } else {
              _tmpPaymentType = _cursor.getString(_cursorIndexOfPaymentType);
            }
            final long _tmpDeadline;
            _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            final float _tmpPrice;
            _tmpPrice = _cursor.getFloat(_cursorIndexOfPrice);
            final int _tmpBillType;
            _tmpBillType = _cursor.getInt(_cursorIndexOfBillType);
            _item = new Bill(_tmpId,_tmpOwner,_tmpPaymentType,_tmpDeadline,_tmpPrice,_tmpBillType);
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

  @Override
  public Object getTuitionFeeReceipts(final String username,
      final Continuation<? super List<TuitionFeePayment>> continuation) {
    final String _sql = "SELECT * FROM TuitionFeePayment WHERE owner = ? ORDER BY deadline DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TuitionFeePayment>>() {
      @Override
      public List<TuitionFeePayment> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "owner");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfLeft = CursorUtil.getColumnIndexOrThrow(_cursor, "left");
          final int _cursorIndexOfFullPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "fUll_price");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfFineDays = CursorUtil.getColumnIndexOrThrow(_cursor, "fine_days");
          final int _cursorIndexOfFineSize = CursorUtil.getColumnIndexOrThrow(_cursor, "fine_size");
          final int _cursorIndexOfDebt = CursorUtil.getColumnIndexOrThrow(_cursor, "debt");
          final List<TuitionFeePayment> _result = new ArrayList<TuitionFeePayment>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TuitionFeePayment _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOwner;
            if (_cursor.isNull(_cursorIndexOfOwner)) {
              _tmpOwner = null;
            } else {
              _tmpOwner = _cursor.getString(_cursorIndexOfOwner);
            }
            final long _tmpDeadline;
            _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            final Long _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            }
            final Float _tmpPrice;
            if (_cursor.isNull(_cursorIndexOfPrice)) {
              _tmpPrice = null;
            } else {
              _tmpPrice = _cursor.getFloat(_cursorIndexOfPrice);
            }
            final Float _tmpLeft;
            if (_cursor.isNull(_cursorIndexOfLeft)) {
              _tmpLeft = null;
            } else {
              _tmpLeft = _cursor.getFloat(_cursorIndexOfLeft);
            }
            final float _tmpFullPrice;
            _tmpFullPrice = _cursor.getFloat(_cursorIndexOfFullPrice);
            final String _tmpYear;
            if (_cursor.isNull(_cursorIndexOfYear)) {
              _tmpYear = null;
            } else {
              _tmpYear = _cursor.getString(_cursorIndexOfYear);
            }
            final int _tmpFineDays;
            _tmpFineDays = _cursor.getInt(_cursorIndexOfFineDays);
            final float _tmpFineSize;
            _tmpFineSize = _cursor.getFloat(_cursorIndexOfFineSize);
            final Float _tmpDebt;
            if (_cursor.isNull(_cursorIndexOfDebt)) {
              _tmpDebt = null;
            } else {
              _tmpDebt = _cursor.getFloat(_cursorIndexOfDebt);
            }
            _item = new TuitionFeePayment(_tmpId,_tmpOwner,_tmpDeadline,_tmpDate,_tmpPrice,_tmpLeft,_tmpFullPrice,_tmpYear,_tmpFineDays,_tmpFineSize,_tmpDebt);
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

  @Override
  public Object getReceipts(final String username, final int type,
      final Continuation<? super List<? extends Receipt>> continuation) {
    final String _sql = "SELECT * FROM Receipt WHERE owner = ? AND receiptType = ? ORDER BY deadline DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, type);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<? extends Receipt>>() {
      @Override
      public List<? extends Receipt> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "owner");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfLeft = CursorUtil.getColumnIndexOrThrow(_cursor, "left");
          final int _cursorIndexOfInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "info");
          final int _cursorIndexOfReceiptType = CursorUtil.getColumnIndexOrThrow(_cursor, "receiptType");
          final List<Receipt> _result = new ArrayList<Receipt>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Receipt _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOwner;
            if (_cursor.isNull(_cursorIndexOfOwner)) {
              _tmpOwner = null;
            } else {
              _tmpOwner = _cursor.getString(_cursorIndexOfOwner);
            }
            final long _tmpDeadline;
            _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            final Long _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            }
            final Float _tmpPrice;
            if (_cursor.isNull(_cursorIndexOfPrice)) {
              _tmpPrice = null;
            } else {
              _tmpPrice = _cursor.getFloat(_cursorIndexOfPrice);
            }
            final Float _tmpLeft;
            if (_cursor.isNull(_cursorIndexOfLeft)) {
              _tmpLeft = null;
            } else {
              _tmpLeft = _cursor.getFloat(_cursorIndexOfLeft);
            }
            final String _tmpInfo;
            if (_cursor.isNull(_cursorIndexOfInfo)) {
              _tmpInfo = null;
            } else {
              _tmpInfo = _cursor.getString(_cursorIndexOfInfo);
            }
            final int _tmpReceiptType;
            _tmpReceiptType = _cursor.getInt(_cursorIndexOfReceiptType);
            _item = new Receipt(_tmpId,_tmpOwner,_tmpDeadline,_tmpDate,_tmpPrice,_tmpLeft,_tmpInfo,_tmpReceiptType);
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
