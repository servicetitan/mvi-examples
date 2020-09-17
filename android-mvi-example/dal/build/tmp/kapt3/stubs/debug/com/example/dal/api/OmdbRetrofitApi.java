package com.example.dal.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u001f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rR\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lcom/example/dal/api/OmdbRetrofitApi;", "", "()V", "api", "Lcom/example/dal/api/OmdbApi;", "getApi", "()Lcom/example/dal/api/OmdbApi;", "api$delegate", "Lkotlin/Lazy;", "movieDetail", "Lcom/example/dal/entities/MovieDetail;", "movieId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "", "Lcom/example/dal/entities/Movie;", "searchQuery", "dal_debug"})
public final class OmdbRetrofitApi {
    private final kotlin.Lazy api$delegate = null;
    
    private final com.example.dal.api.OmdbApi getApi() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object search(@org.jetbrains.annotations.NotNull()
    java.lang.String searchQuery, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.dal.entities.Movie>> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object movieDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String movieId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.dal.entities.MovieDetail> p1) {
        return null;
    }
    
    public OmdbRetrofitApi() {
        super();
    }
}