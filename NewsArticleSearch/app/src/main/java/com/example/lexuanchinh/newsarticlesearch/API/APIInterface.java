package com.example.lexuanchinh.newsarticlesearch.API;

import com.example.lexuanchinh.newsarticlesearch.model.ListSearch;
import com.example.lexuanchinh.newsarticlesearch.util.APIService;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/svc/search/v2/articlesearch.json")
    Call<ListSearch>getArticlesearch(@Query("api-key") String API_KEY);
}
