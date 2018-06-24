package com.example.lexuanchinh.newsarticlesearch.articlesearch.presenter;

import com.example.lexuanchinh.newsarticlesearch.articlesearch.model.ArticleSearchData;
import com.example.lexuanchinh.newsarticlesearch.articlesearch.model.DataListener;
import com.example.lexuanchinh.newsarticlesearch.articlesearch.view.ListArtSearchView;
import com.example.lexuanchinh.newsarticlesearch.model.Doc;
import com.example.lexuanchinh.newsarticlesearch.util.NetWorkUtil;

import java.util.List;

public class ListArtSearchPresenterImpl implements ListArtSearchPresenter,DataListener {
    ListArtSearchView mView; ArticleSearchData articleSearchData;
    public ListArtSearchPresenterImpl(ListArtSearchView mView, ArticleSearchData articleSearchData){
        this.mView=mView;
        this.articleSearchData=articleSearchData;
    }
    @Override
    public void onResponse(List<Doc> docs) {
        mView.hideLoading();
        mView.showListArtSearch(docs);
    }

    @Override
    public void getDocs(int page) {

        mView.showLoading();
        if (NetWorkUtil.isOnline()){
            articleSearchData.getDataFormNetwork(this,page);
        }

    }

    @Override
    public void getSearch(String query) {
        mView.showLoading();
        if (NetWorkUtil.isOnline()){
            articleSearchData.getDataSearchFromNetwork(this,query);
        }
    }

    @Override
    public void getSearchFilter(String beginDay, String sort, String newDesk) {
        mView.showLoading();
        articleSearchData.getDataSearchFilterFromNetwork(this,beginDay,sort,newDesk);
    }

    @Override
    public void getLoadMore(String beginDay, String sort, String q, String newDesks, String API_KEY, int page) {
        mView.showLoading();
        if (NetWorkUtil.isOnline()){
            //articleSearchData.getDataSearchFromNetwork(this,query);
            mView.showLoading();
            articleSearchData.getDataLoadMoreFromNetWork(this, beginDay,sort,  q,  newDesks,  API_KEY, page);
        }
    }
}
