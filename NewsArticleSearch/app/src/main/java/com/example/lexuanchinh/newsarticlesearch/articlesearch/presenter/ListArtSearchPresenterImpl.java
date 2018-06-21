package com.example.lexuanchinh.newsarticlesearch.articlesearch.presenter;

import com.example.lexuanchinh.newsarticlesearch.articlesearch.model.ArticleSearchData;
import com.example.lexuanchinh.newsarticlesearch.articlesearch.model.DataListener;
import com.example.lexuanchinh.newsarticlesearch.articlesearch.view.ListArtSearchView;
import com.example.lexuanchinh.newsarticlesearch.model.Doc;

import java.util.List;

public class ListArtSearchPresenterImpl implements ListArtSearchPresenter,DataListener {
    ListArtSearchView mView; ArticleSearchData articleSearchData;
    public ListArtSearchPresenterImpl(ListArtSearchView mView, ArticleSearchData articleSearchData){
        this.mView=mView;
        this.articleSearchData=articleSearchData;
    }
    @Override
    public void onResponse(List<Doc> docs) {
        mView.showListArtSearch(docs);
    }

    @Override
    public void getDocs() {
        mView.showLoading();
        articleSearchData.getDataFormNetwork(this);
    }

    @Override
    public void getSearch(String query) {
        mView.showLoading();
        articleSearchData.getDataSearchFromNetwork(this,query);
    }
}
