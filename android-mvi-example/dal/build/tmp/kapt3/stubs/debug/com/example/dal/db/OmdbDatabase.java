package com.example.dal.db;

import java.lang.System;

@androidx.room.TypeConverters(value = {com.example.dal.db.Converters.class})
@androidx.room.Database(entities = {com.example.dal.entities.Movie.class, com.example.dal.entities.MovieDetail.class}, version = 5, exportSchema = false)
@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u0007"}, d2 = {"Lcom/example/dal/db/OmdbDatabase;", "Landroidx/room/RoomDatabase;", "()V", "movieDao", "Lcom/example/dal/db/MovieDao;", "movieDetailDao", "Lcom/example/dal/db/MovieDetailDao;", "dal_debug"})
public abstract class OmdbDatabase extends androidx.room.RoomDatabase {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.dal.db.MovieDao movieDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.dal.db.MovieDetailDao movieDetailDao();
    
    public OmdbDatabase() {
        super();
    }
}