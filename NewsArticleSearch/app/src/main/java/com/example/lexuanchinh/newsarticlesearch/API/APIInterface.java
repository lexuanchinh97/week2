package com.example.lexuanchinh.newsarticlesearch.API;

import com.example.lexuanchinh.newsarticlesearch.model.ListSearch;
import com.example.lexuanchinh.newsarticlesearch.util.APIService;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("svc/search/v2/articlesearch.json")
    Call<ListSearch>getArticlesearch(
                                     @Query("begin_day") String BEGIN_DAY,
                                     @Query("sort") String SORT ,
                                     @Query("api-key") String API_KEY,
                                     @Query("page") int PAGE);
    @GET("svc/search/v2/articlesearch.json")
    Call<ListSearch>getArticlesearchQ(
            @Query("q") String Q,
          //  @Query("begin_day") String BEGIN_DAY,
         //   @Query("fq") String NEWS_DESK,
            @Query("api-key") String API_KEY);
    @GET("svc/search/v2/articlesearch.json")
    Call<ListSearch>getSearchFilter(
              @Query("begin_day") String BEGIN_DAY,
              @Query("sort") String SORT,
              @Query("fq") String NEWS_DESK,
            @Query("api-key") String API_KEY);
}
