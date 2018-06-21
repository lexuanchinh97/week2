package com.example.lexuanchinh.newsarticlesearch.articlesearch.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.lexuanchinh.newsarticlesearch.Adapter.AdapterListSearch;
import com.example.lexuanchinh.newsarticlesearch.R;
import com.example.lexuanchinh.newsarticlesearch.articlesearch.model.ArticleSearchData;
import com.example.lexuanchinh.newsarticlesearch.articlesearch.model.ArticleSearchDataImpl;
import com.example.lexuanchinh.newsarticlesearch.articlesearch.presenter.ListArtSearchPresenter;
import com.example.lexuanchinh.newsarticlesearch.articlesearch.presenter.ListArtSearchPresenterImpl;
import com.example.lexuanchinh.newsarticlesearch.model.Doc;
import com.example.lexuanchinh.newsarticlesearch.util.APIService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.lexuanchinh.newsarticlesearch.articlesearch.model.ArticleSearchDataImpl.beginDay;
import static com.example.lexuanchinh.newsarticlesearch.articlesearch.model.ArticleSearchDataImpl.newsDesk;
import static com.example.lexuanchinh.newsarticlesearch.articlesearch.model.ArticleSearchDataImpl.page;
import static com.example.lexuanchinh.newsarticlesearch.articlesearch.model.ArticleSearchDataImpl.sort;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,ListArtSearchView  {
    List<Doc> docList;
    RecyclerView recyclerView;
    AdapterListSearch adapter;
    ListArtSearchPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);

//           if(docList==null)
//           {
//               setUpListView();
//              // callAPI();
//           }else {
//               adapter.setData(docList);
//           }
        setUpListView();
        ArticleSearchData searchData=new ArticleSearchDataImpl();
           presenter=new ListArtSearchPresenterImpl(this,searchData);
           presenter.getDocs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem searchItem=menu.findItem(R.id.action_search);
        final SearchView searchView= (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                presenter.getSearch(s);
                return  true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:

            break;
            case R.id.filter:
                Intent intent=new Intent(MainActivity.this,FilterActivity.class);
                startActivity(intent);
                break;
            case R.id.profile:
                Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
//    private void callAPI() {
//        //    progressBar.setVisibility(View.VISIBLE);
//        APIService.getInstance().getArticlesearch("20170112","newest","news_desk:( Sports)",APIService.API_KEY,page++).enqueue(new Callback<ListSearch>() {
//            @Override
//            public void onResponse(Call<ListSearch> call, Response<ListSearch> response) {
//                if(response.body()!=null){
//                    listSearch=response.body();
//                    docList=listSearch.getResponse().getDocs();
//                    adapter.setData(docList);
//                    //  Toast.makeText(MainActivity.this, response.body().getCopyright(), Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<ListSearch> call, Throwable t) {
//
//            }
//        });
//
//
//    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // store the values selected into a Calendar instance
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    }
    private void setUpListView() {
        docList = new ArrayList<>();
        // recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        adapter = new AdapterListSearch(MainActivity.this);
        adapter.setData(docList);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //this.recyclerView.addOnScrollListener(n);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void showListArtSearch(List<Doc> docs) {
            adapter.setData(docs);
    }
}
