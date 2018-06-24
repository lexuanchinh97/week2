package com.example.lexuanchinh.newsarticlesearch.articlesearch.presenter;

import retrofit2.http.Query;

public interface ListArtSearchPresenter {
    void getDocs(int page);
    void getSearch(String query);
    void getSearchFilter(String beginDay,String sort,String newDesk );
    void getLoadMore(String beginDay,String sort,String q,String newDesks,String API_KEY,int page);
//    @Query("begin_day") String BEGIN_DAY,
//    @Query("sort") String SORT,
//    @Query("q") String Q,
//    @Query("fq") String NEWS_DESK,
//    @Query("api-key") String API_KEY,
//    @Query("page") int PAGE);
}
