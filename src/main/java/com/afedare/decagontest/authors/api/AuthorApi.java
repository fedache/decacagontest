package com.afedare.decagontest.authors.api;

import com.afedare.decagontest.authors.data.AuthorsPage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AuthorApi {
    @GET("article_users/search")
    Call<AuthorsPage> getAuthors(@Query("page") int page);
}