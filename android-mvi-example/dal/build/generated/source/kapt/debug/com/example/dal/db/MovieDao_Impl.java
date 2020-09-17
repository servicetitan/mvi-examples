package com.example.dal.db;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.dal.entities.Movie;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MovieDao_Impl implements MovieDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Movie> __insertionAdapterOfMovie;

  private final EntityDeletionOrUpdateAdapter<Movie> __deletionAdapterOfMovie;

  public MovieDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMovie = new EntityInsertionAdapter<Movie>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Movie` (`title`,`year`,`imdbId`,`type`,`posterUrl`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Movie value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        if (value.getYear() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getYear());
        }
        if (value.getImdbId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getImdbId());
        }
        if (value.getType() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getType());
        }
        if (value.getPosterUrl() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPosterUrl());
        }
      }
    };
    this.__deletionAdapterOfMovie = new EntityDeletionOrUpdateAdapter<Movie>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Movie` WHERE `imdbId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Movie value) {
        if (value.getImdbId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getImdbId());
        }
      }
    };
  }

  @Override
  public Object insert(final List<Movie> items, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMovie.insert(items);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object delete(final Movie item, final Continuation<? super Integer> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__deletionAdapterOfMovie.handle(item);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object allMovies(final Continuation<? super List<Movie>> p0) {
    final String _sql = "SELECT * FROM Movie";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.execute(__db, false, new Callable<List<Movie>>() {
      @Override
      public List<Movie> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfImdbId = CursorUtil.getColumnIndexOrThrow(_cursor, "imdbId");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfPosterUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "posterUrl");
          final List<Movie> _result = new ArrayList<Movie>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Movie _item;
            _item = new Movie();
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item.setTitle(_tmpTitle);
            final String _tmpYear;
            _tmpYear = _cursor.getString(_cursorIndexOfYear);
            _item.setYear(_tmpYear);
            final String _tmpImdbId;
            _tmpImdbId = _cursor.getString(_cursorIndexOfImdbId);
            _item.setImdbId(_tmpImdbId);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _item.setType(_tmpType);
            final String _tmpPosterUrl;
            _tmpPosterUrl = _cursor.getString(_cursorIndexOfPosterUrl);
            _item.setPosterUrl(_tmpPosterUrl);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p0);
  }

  @Override
  public Object findById(final String id, final Continuation<? super Movie> p1) {
    final String _sql = "SELECT * FROM Movie WHERE imdbId LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return CoroutinesRoom.execute(__db, false, new Callable<Movie>() {
      @Override
      public Movie call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfImdbId = CursorUtil.getColumnIndexOrThrow(_cursor, "imdbId");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfPosterUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "posterUrl");
          final Movie _result;
          if(_cursor.moveToFirst()) {
            _result = new Movie();
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _result.setTitle(_tmpTitle);
            final String _tmpYear;
            _tmpYear = _cursor.getString(_cursorIndexOfYear);
            _result.setYear(_tmpYear);
            final String _tmpImdbId;
            _tmpImdbId = _cursor.getString(_cursorIndexOfImdbId);
            _result.setImdbId(_tmpImdbId);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _result.setType(_tmpType);
            final String _tmpPosterUrl;
            _tmpPosterUrl = _cursor.getString(_cursorIndexOfPosterUrl);
            _result.setPosterUrl(_tmpPosterUrl);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p1);
  }

  @Override
  public Object findByQuery(final String query, final Continuation<? super List<Movie>> p1) {
    final String _sql = "SELECT * FROM Movie WHERE title LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    return CoroutinesRoom.execute(__db, false, new Callable<List<Movie>>() {
      @Override
      public List<Movie> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfImdbId = CursorUtil.getColumnIndexOrThrow(_cursor, "imdbId");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfPosterUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "posterUrl");
          final List<Movie> _result = new ArrayList<Movie>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Movie _item;
            _item = new Movie();
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item.setTitle(_tmpTitle);
            final String _tmpYear;
            _tmpYear = _cursor.getString(_cursorIndexOfYear);
            _item.setYear(_tmpYear);
            final String _tmpImdbId;
            _tmpImdbId = _cursor.getString(_cursorIndexOfImdbId);
            _item.setImdbId(_tmpImdbId);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _item.setType(_tmpType);
            final String _tmpPosterUrl;
            _tmpPosterUrl = _cursor.getString(_cursorIndexOfPosterUrl);
            _item.setPosterUrl(_tmpPosterUrl);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p1);
  }
}
