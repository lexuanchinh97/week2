package com.example.lexuanchinh.newsarticlesearch.articlesearch.presenter;

public interface ListArtSearchPresenter {
    void getDocs();
    void getSearch(String query);
    void getSearchFilter(String beginDay,String sort,String newDesk );
}
