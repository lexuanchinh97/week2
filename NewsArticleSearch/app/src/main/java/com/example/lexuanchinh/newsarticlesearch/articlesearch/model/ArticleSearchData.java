package com.example.lexuanchinh.newsarticlesearch.articlesearch.model;

public interface ArticleSearchData {
    void getDataFormNetwork(DataListener listener,int page);
    void getDataSearchFromNetwork(DataListener listener,String query);
    void getDataSearchFilterFromNetwork(DataListener listener,String beginDat,String sort,String newDesk);
    void getDataLoadMoreFromNetWork(DataListener listener,String beginDay,String sort,String q,String newDesks,String API_KEY,int page);
}
