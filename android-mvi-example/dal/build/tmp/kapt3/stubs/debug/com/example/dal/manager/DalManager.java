package com.example.dal.manager;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u001f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u0016H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001c"}, d2 = {"Lcom/example/dal/manager/DalManager;", "Lcom/example/dal/manager/STDalManager;", "()V", "omdbApi", "Lcom/example/dal/api/OmdbApi;", "getOmdbApi", "()Lcom/example/dal/api/OmdbApi;", "setOmdbApi", "(Lcom/example/dal/api/OmdbApi;)V", "omdbDatabase", "Lcom/example/dal/db/OmdbDatabase;", "getOmdbDatabase", "()Lcom/example/dal/db/OmdbDatabase;", "setOmdbDatabase", "(Lcom/example/dal/db/OmdbDatabase;)V", "init", "", "context", "Landroid/content/Context;", "movieDetail", "Lcom/example/dal/entities/MovieDetail;", "movieId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchMovies", "", "Lcom/example/dal/entities/Movie;", "query", "dal_debug"})
public final class DalManager implements com.example.dal.manager.STDalManager {
    @org.jetbrains.annotations.NotNull()
    public com.example.dal.api.OmdbApi omdbApi;
    @org.jetbrains.annotations.NotNull()
    public com.example.dal.db.OmdbDatabase omdbDatabase;
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.dal.api.OmdbApi getOmdbApi() {
        return null;
    }
    
    public final void setOmdbApi(@org.jetbrains.annotations.NotNull()
    com.example.dal.api.OmdbApi p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.dal.db.OmdbDatabase getOmdbDatabase() {
        return null;
    }
    
    public final void setOmdbDatabase(@org.jetbrains.annotations.NotNull()
    com.example.dal.db.OmdbDatabase p0) {
    }
    
    public final void init(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object searchMovies(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.dal.entities.Movie>> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object movieDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String movieId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.dal.entities.MovieDetail> p1) {
        return null;
    }
    
    public DalManager() {
        super();
    }
}