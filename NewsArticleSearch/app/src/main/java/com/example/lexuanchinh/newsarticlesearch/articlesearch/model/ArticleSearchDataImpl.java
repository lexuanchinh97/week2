package com.example.lexuanchinh.newsarticlesearch.articlesearch.model;

import android.util.Log;

import com.example.lexuanchinh.newsarticlesearch.Adapter.AdapterListSearch;
import com.example.lexuanchinh.newsarticlesearch.model.Doc;
import com.example.lexuanchinh.newsarticlesearch.model.ListSearch;
import com.example.lexuanchinh.newsarticlesearch.util.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleSearchDataImpl implements ArticleSearchData {
    ListSearch listSearch;
    public static List<Doc> docList=null;
    @Override
    public void getDataFormNetwork(final DataListener listener,int page) {
        APIService.getInstance().getArticlesearch("","newest",APIService.API_KEY,page).enqueue(new Callback<ListSearch>() {
            @Override
            public void onResponse(Call<ListSearch> call, Response<ListSearch> response) {
                if(response!=null && response.body()!=null){
                    docList=null;
                    listSearch=response.body();
                    docList=listSearch.getResponse().getDocs();
                    listener.onResponse(docList);
                }
            }
            @Override
            public void onFailure(Call<ListSearch> call, Throwable t) {
            }
        });
    }
    @Override
    public void getDataSearchFromNetwork(final DataListener listener, String query) {
        APIService.getInstance().getArticlesearchQ(query,APIService.API_KEY).enqueue(new Callback<ListSearch>() {
            @Override
            public void onResponse(Call<ListSearch> call, Response<ListSearch> response) {
                if(response!=null && response.body()!=null){
                    docList=null;
                    listSearch=response.body();
                    docList=listSearch.getResponse().getDocs();
                    listener.onResponse(docList);
                }
            }
            @Override
            public void onFailure(Call<ListSearch> call, Throwable t) {
                Log.d("aaa","chinh");
            }
        });
    }

    @Override
    public void getDataSearchFilterFromNetwork(final DataListener listener, String beginDat, String sort, String newDesk) {
        APIService.getInstance().getSearchFilter(beginDat,sort,newDesk,APIService.API_KEY).enqueue(new Callback<ListSearch>() {
            @Override
            public void onResponse(Call<ListSearch> call, Response<ListSearch> response) {
                if(response!=null && response.body()!=null){
                    docList=null;
                    listSearch=response.body();
                    docList=listSearch.getResponse().getDocs();
                    listener.onResponse(docList);
                }
            }

            @Override
            public void onFailure(Call<ListSearch> call, Throwable t) {

            }
        });
    }

    @Override
    public void getDataLoadMoreFromNetWork(final DataListener listener, String beginDay, String sort, String q, String newDesks, String API_KEY, int page) {
        APIService.getInstance().getLoadMore(beginDay,sort,q,newDesks,APIService.API_KEY,page).enqueue(new Callback<ListSearch>() {
            @Override
            public void onResponse(Call<ListSearch> call, Response<ListSearch> response) {
                if(response!=null && response.body()!=null){
                    if(docList==null){
                        listSearch=response.body();
                        docList=listSearch.getResponse().getDocs();
                    }
                    else {
                        docList.addAll(response.body().getResponse().getDocs());
                    }
                    listener.onResponse(docList);
                }
            }
            @Override
            public void onFailure(Call<ListSearch> call, Throwable t) {

            }
        });
    }


}
