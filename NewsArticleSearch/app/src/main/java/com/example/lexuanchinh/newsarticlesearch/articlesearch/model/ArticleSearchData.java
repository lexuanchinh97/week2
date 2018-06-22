package com.example.lexuanchinh.newsarticlesearch.articlesearch.model;

public interface ArticleSearchData {
    void getDataFormNetwork(DataListener listener);
    void getDataSearchFromNetwork(DataListener listener,String query);
    void getDataSearchFilterFromNetwork(DataListener listener,String beginDat,String sort,String newDesk);
}
