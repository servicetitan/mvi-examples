package com.example.dal.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class OmdbDatabase_Impl extends OmdbDatabase {
  private volatile MovieDao _movieDao;

  private volatile MovieDetailDao _movieDetailDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(5) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Movie` (`title` TEXT NOT NULL, `year` TEXT NOT NULL, `imdbId` TEXT NOT NULL, `type` TEXT NOT NULL, `posterUrl` TEXT NOT NULL, PRIMARY KEY(`imdbId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MovieDetail` (`actors` TEXT NOT NULL, `awards` TEXT NOT NULL, `boxOffice` TEXT NOT NULL, `country` TEXT NOT NULL, `dVD` TEXT NOT NULL, `director` TEXT NOT NULL, `genre` TEXT NOT NULL, `imdbID` TEXT NOT NULL, `imdbRating` TEXT NOT NULL, `imdbVotes` TEXT NOT NULL, `language` TEXT NOT NULL, `metascore` TEXT NOT NULL, `plot` TEXT NOT NULL, `poster` TEXT NOT NULL, `production` TEXT NOT NULL, `rated` TEXT NOT NULL, `ratings` TEXT NOT NULL, `released` TEXT NOT NULL, `response` TEXT NOT NULL, `runtime` TEXT NOT NULL, `title` TEXT NOT NULL, `type` TEXT NOT NULL, `website` TEXT NOT NULL, `writer` TEXT NOT NULL, `year` TEXT NOT NULL, PRIMARY KEY(`imdbID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '46f190e260d22fd5dc118e66e9d93c99')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Movie`");
        _db.execSQL("DROP TABLE IF EXISTS `MovieDetail`");
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
        final HashMap<String, TableInfo.Column> _columnsMovie = new HashMap<String, TableInfo.Column>(5);
        _columnsMovie.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovie.put("year", new TableInfo.Column("year", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovie.put("imdbId", new TableInfo.Column("imdbId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovie.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovie.put("posterUrl", new TableInfo.Column("posterUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMovie = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMovie = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMovie = new TableInfo("Movie", _columnsMovie, _foreignKeysMovie, _indicesMovie);
        final TableInfo _existingMovie = TableInfo.read(_db, "Movie");
        if (! _infoMovie.equals(_existingMovie)) {
          return new RoomOpenHelper.ValidationResult(false, "Movie(com.example.dal.entities.Movie).\n"
                  + " Expected:\n" + _infoMovie + "\n"
                  + " Found:\n" + _existingMovie);
        }
        final HashMap<String, TableInfo.Column> _columnsMovieDetail = new HashMap<String, TableInfo.Column>(25);
        _columnsMovieDetail.put("actors", new TableInfo.Column("actors", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("awards", new TableInfo.Column("awards", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("boxOffice", new TableInfo.Column("boxOffice", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("country", new TableInfo.Column("country", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("dVD", new TableInfo.Column("dVD", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("director", new TableInfo.Column("director", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("genre", new TableInfo.Column("genre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("imdbID", new TableInfo.Column("imdbID", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("imdbRating", new TableInfo.Column("imdbRating", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("imdbVotes", new TableInfo.Column("imdbVotes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("language", new TableInfo.Column("language", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("metascore", new TableInfo.Column("metascore", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("plot", new TableInfo.Column("plot", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("poster", new TableInfo.Column("poster", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("production", new TableInfo.Column("production", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("rated", new TableInfo.Column("rated", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("ratings", new TableInfo.Column("ratings", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("released", new TableInfo.Column("released", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("response", new TableInfo.Column("response", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("runtime", new TableInfo.Column("runtime", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("website", new TableInfo.Column("website", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("writer", new TableInfo.Column("writer", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetail.put("year", new TableInfo.Column("year", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMovieDetail = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMovieDetail = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMovieDetail = new TableInfo("MovieDetail", _columnsMovieDetail, _foreignKeysMovieDetail, _indicesMovieDetail);
        final TableInfo _existingMovieDetail = TableInfo.read(_db, "MovieDetail");
        if (! _infoMovieDetail.equals(_existingMovieDetail)) {
          return new RoomOpenHelper.ValidationResult(false, "MovieDetail(com.example.dal.entities.MovieDetail).\n"
                  + " Expected:\n" + _infoMovieDetail + "\n"
                  + " Found:\n" + _existingMovieDetail);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "46f190e260d22fd5dc118e66e9d93c99", "4280cfa3d135c553e0671e403125caf7");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Movie","MovieDetail");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Movie`");
      _db.execSQL("DELETE FROM `MovieDetail`");
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
  public MovieDao movieDao() {
    if (_movieDao != null) {
      return _movieDao;
    } else {
      synchronized(this) {
        if(_movieDao == null) {
          _movieDao = new MovieDao_Impl(this);
        }
        return _movieDao;
      }
    }
  }

  @Override
  public MovieDetailDao movieDetailDao() {
    if (_movieDetailDao != null) {
      return _movieDetailDao;
    } else {
      synchronized(this) {
        if(_movieDetailDao == null) {
          _movieDetailDao = new MovieDetailDao_Impl(this);
        }
        return _movieDetailDao;
      }
    }
  }
}
