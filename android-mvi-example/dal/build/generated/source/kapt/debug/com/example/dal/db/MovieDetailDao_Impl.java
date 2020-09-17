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
import com.example.dal.entities.MovieDetail;
import com.example.dal.entities.Rating;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MovieDetailDao_Impl implements MovieDetailDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MovieDetail> __insertionAdapterOfMovieDetail;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<MovieDetail> __deletionAdapterOfMovieDetail;

  public MovieDetailDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMovieDetail = new EntityInsertionAdapter<MovieDetail>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `MovieDetail` (`actors`,`awards`,`boxOffice`,`country`,`dVD`,`director`,`genre`,`imdbID`,`imdbRating`,`imdbVotes`,`language`,`metascore`,`plot`,`poster`,`production`,`rated`,`ratings`,`released`,`response`,`runtime`,`title`,`type`,`website`,`writer`,`year`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MovieDetail value) {
        if (value.getActors() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getActors());
        }
        if (value.getAwards() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getAwards());
        }
        if (value.getBoxOffice() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getBoxOffice());
        }
        if (value.getCountry() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCountry());
        }
        if (value.getDVD() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDVD());
        }
        if (value.getDirector() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDirector());
        }
        if (value.getGenre() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getGenre());
        }
        if (value.getImdbID() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getImdbID());
        }
        if (value.getImdbRating() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getImdbRating());
        }
        if (value.getImdbVotes() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getImdbVotes());
        }
        if (value.getLanguage() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getLanguage());
        }
        if (value.getMetascore() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getMetascore());
        }
        if (value.getPlot() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getPlot());
        }
        if (value.getPoster() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getPoster());
        }
        if (value.getProduction() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getProduction());
        }
        if (value.getRated() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getRated());
        }
        final String _tmp;
        _tmp = __converters.ratingToString(value.getRatings());
        if (_tmp == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, _tmp);
        }
        if (value.getReleased() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getReleased());
        }
        if (value.getResponse() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getResponse());
        }
        if (value.getRuntime() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.getRuntime());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(21);
        } else {
          stmt.bindString(21, value.getTitle());
        }
        if (value.getType() == null) {
          stmt.bindNull(22);
        } else {
          stmt.bindString(22, value.getType());
        }
        if (value.getWebsite() == null) {
          stmt.bindNull(23);
        } else {
          stmt.bindString(23, value.getWebsite());
        }
        if (value.getWriter() == null) {
          stmt.bindNull(24);
        } else {
          stmt.bindString(24, value.getWriter());
        }
        if (value.getYear() == null) {
          stmt.bindNull(25);
        } else {
          stmt.bindString(25, value.getYear());
        }
      }
    };
    this.__deletionAdapterOfMovieDetail = new EntityDeletionOrUpdateAdapter<MovieDetail>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `MovieDetail` WHERE `imdbID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MovieDetail value) {
        if (value.getImdbID() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getImdbID());
        }
      }
    };
  }

  @Override
  public Object insert(final MovieDetail item, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMovieDetail.insert(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object delete(final MovieDetail item, final Continuation<? super Integer> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__deletionAdapterOfMovieDetail.handle(item);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object findById(final String id, final Continuation<? super MovieDetail> p1) {
    final String _sql = "SELECT * FROM MovieDetail WHERE imdbId LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return CoroutinesRoom.execute(__db, false, new Callable<MovieDetail>() {
      @Override
      public MovieDetail call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfActors = CursorUtil.getColumnIndexOrThrow(_cursor, "actors");
          final int _cursorIndexOfAwards = CursorUtil.getColumnIndexOrThrow(_cursor, "awards");
          final int _cursorIndexOfBoxOffice = CursorUtil.getColumnIndexOrThrow(_cursor, "boxOffice");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfDVD = CursorUtil.getColumnIndexOrThrow(_cursor, "dVD");
          final int _cursorIndexOfDirector = CursorUtil.getColumnIndexOrThrow(_cursor, "director");
          final int _cursorIndexOfGenre = CursorUtil.getColumnIndexOrThrow(_cursor, "genre");
          final int _cursorIndexOfImdbID = CursorUtil.getColumnIndexOrThrow(_cursor, "imdbID");
          final int _cursorIndexOfImdbRating = CursorUtil.getColumnIndexOrThrow(_cursor, "imdbRating");
          final int _cursorIndexOfImdbVotes = CursorUtil.getColumnIndexOrThrow(_cursor, "imdbVotes");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfMetascore = CursorUtil.getColumnIndexOrThrow(_cursor, "metascore");
          final int _cursorIndexOfPlot = CursorUtil.getColumnIndexOrThrow(_cursor, "plot");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfProduction = CursorUtil.getColumnIndexOrThrow(_cursor, "production");
          final int _cursorIndexOfRated = CursorUtil.getColumnIndexOrThrow(_cursor, "rated");
          final int _cursorIndexOfRatings = CursorUtil.getColumnIndexOrThrow(_cursor, "ratings");
          final int _cursorIndexOfReleased = CursorUtil.getColumnIndexOrThrow(_cursor, "released");
          final int _cursorIndexOfResponse = CursorUtil.getColumnIndexOrThrow(_cursor, "response");
          final int _cursorIndexOfRuntime = CursorUtil.getColumnIndexOrThrow(_cursor, "runtime");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfWebsite = CursorUtil.getColumnIndexOrThrow(_cursor, "website");
          final int _cursorIndexOfWriter = CursorUtil.getColumnIndexOrThrow(_cursor, "writer");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final MovieDetail _result;
          if(_cursor.moveToFirst()) {
            final String _tmpActors;
            _tmpActors = _cursor.getString(_cursorIndexOfActors);
            final String _tmpAwards;
            _tmpAwards = _cursor.getString(_cursorIndexOfAwards);
            final String _tmpBoxOffice;
            _tmpBoxOffice = _cursor.getString(_cursorIndexOfBoxOffice);
            final String _tmpCountry;
            _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            final String _tmpDVD;
            _tmpDVD = _cursor.getString(_cursorIndexOfDVD);
            final String _tmpDirector;
            _tmpDirector = _cursor.getString(_cursorIndexOfDirector);
            final String _tmpGenre;
            _tmpGenre = _cursor.getString(_cursorIndexOfGenre);
            final String _tmpImdbID;
            _tmpImdbID = _cursor.getString(_cursorIndexOfImdbID);
            final String _tmpImdbRating;
            _tmpImdbRating = _cursor.getString(_cursorIndexOfImdbRating);
            final String _tmpImdbVotes;
            _tmpImdbVotes = _cursor.getString(_cursorIndexOfImdbVotes);
            final String _tmpLanguage;
            _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            final String _tmpMetascore;
            _tmpMetascore = _cursor.getString(_cursorIndexOfMetascore);
            final String _tmpPlot;
            _tmpPlot = _cursor.getString(_cursorIndexOfPlot);
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final String _tmpProduction;
            _tmpProduction = _cursor.getString(_cursorIndexOfProduction);
            final String _tmpRated;
            _tmpRated = _cursor.getString(_cursorIndexOfRated);
            final List<Rating> _tmpRatings;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfRatings);
            _tmpRatings = __converters.stringToRatings(_tmp);
            final String _tmpReleased;
            _tmpReleased = _cursor.getString(_cursorIndexOfReleased);
            final String _tmpResponse;
            _tmpResponse = _cursor.getString(_cursorIndexOfResponse);
            final String _tmpRuntime;
            _tmpRuntime = _cursor.getString(_cursorIndexOfRuntime);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final String _tmpWebsite;
            _tmpWebsite = _cursor.getString(_cursorIndexOfWebsite);
            final String _tmpWriter;
            _tmpWriter = _cursor.getString(_cursorIndexOfWriter);
            final String _tmpYear;
            _tmpYear = _cursor.getString(_cursorIndexOfYear);
            _result = new MovieDetail(_tmpActors,_tmpAwards,_tmpBoxOffice,_tmpCountry,_tmpDVD,_tmpDirector,_tmpGenre,_tmpImdbID,_tmpImdbRating,_tmpImdbVotes,_tmpLanguage,_tmpMetascore,_tmpPlot,_tmpPoster,_tmpProduction,_tmpRated,_tmpRatings,_tmpReleased,_tmpResponse,_tmpRuntime,_tmpTitle,_tmpType,_tmpWebsite,_tmpWriter,_tmpYear);
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
}
