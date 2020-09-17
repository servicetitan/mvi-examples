package com.example.dal.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J/\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ/\u0010\t\u001a\u00020\n2\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u000b\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lcom/example/dal/api/OmdbApi;", "", "movieDetail", "Lcom/example/dal/entities/MovieDetail;", "url", "", "movieId", "apiKey", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchMovies", "Lcom/example/dal/entities/SearchMovie;", "search", "dal_debug"})
public abstract interface OmdbApi {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET()
    public abstract java.lang.Object searchMovies(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Url()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "s")
    java.lang.String search, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "apikey")
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.dal.entities.SearchMovie> p3);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET()
    public abstract java.lang.Object movieDetail(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Url()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "i")
    java.lang.String movieId, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "apikey")
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.dal.entities.MovieDetail> p3);
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 3)
    public final class DefaultImpls {
    }
}