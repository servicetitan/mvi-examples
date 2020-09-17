package com.example.dal.manager;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000b"}, d2 = {"Lcom/example/dal/manager/STDalManager;", "", "movieDetail", "Lcom/example/dal/entities/MovieDetail;", "movieId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchMovies", "", "Lcom/example/dal/entities/Movie;", "query", "dal_debug"})
public abstract interface STDalManager {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchMovies(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.dal.entities.Movie>> p1);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object movieDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String movieId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.dal.entities.MovieDetail> p1);
}