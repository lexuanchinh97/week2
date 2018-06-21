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
    List<Doc> docList;
    AdapterListSearch adapter;
    public static int page=1;
    public static String beginDay="20170112";
    public static String sort="newest";
    public static String newsDesk="";
    @Override
    public void getDataFormNetwork(final DataListener listener) {
        APIService.getInstance().getArticlesearch(beginDay,sort,APIService.API_KEY,page).enqueue(new Callback<ListSearch>() {
            @Override
            public void onResponse(Call<ListSearch> call, Response<ListSearch> response) {
                if(response.body()!=null){
                    listSearch=response.body();
                    docList=listSearch.getResponse().getDocs();
                    listener.onResponse(docList);
                   // adapter.setData(docList);
                    //  Toast.makeText(MainActivity.this, response.body().getCopyright(), Toast.LENGTH_SHORT).show();
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
                if(response.body()!=null){
                    listSearch=response.body();
                    docList=listSearch.getResponse().getDocs();
                    listener.onResponse(docList);
                    // adapter.setData(docList);
                    //  Toast.makeText(MainActivity.this, response.body().getCopyright(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListSearch> call, Throwable t) {

            }
        });
    }


}
