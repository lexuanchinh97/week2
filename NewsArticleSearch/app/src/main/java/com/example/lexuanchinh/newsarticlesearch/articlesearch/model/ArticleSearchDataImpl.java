package com.example.lexuanchinh.newsarticlesearch.articlesearch.model;

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
    @Override
    public void getDataFormNetwork(final DataListener listener) {
        APIService.getInstance().getArticlesearch("20170112","newest","news_desk:( Sports)",APIService.API_KEY,page++).enqueue(new Callback<ListSearch>() {
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
