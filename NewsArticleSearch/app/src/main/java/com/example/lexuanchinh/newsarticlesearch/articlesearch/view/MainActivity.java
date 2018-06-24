package com.example.lexuanchinh.newsarticlesearch.articlesearch.view;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lexuanchinh.newsarticlesearch.Adapter.AdapterListSearch;
import com.example.lexuanchinh.newsarticlesearch.R;
import com.example.lexuanchinh.newsarticlesearch.articlesearch.model.ArticleSearchData;
import com.example.lexuanchinh.newsarticlesearch.articlesearch.model.ArticleSearchDataImpl;
import com.example.lexuanchinh.newsarticlesearch.articlesearch.presenter.ListArtSearchPresenter;
import com.example.lexuanchinh.newsarticlesearch.articlesearch.presenter.ListArtSearchPresenterImpl;
import com.example.lexuanchinh.newsarticlesearch.databinding.ActivityMainBinding;
import com.example.lexuanchinh.newsarticlesearch.model.Doc;
import com.example.lexuanchinh.newsarticlesearch.util.APIService;
import com.example.lexuanchinh.newsarticlesearch.util.NetWorkUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,ListArtSearchView  {
    List<Doc> docList;
    AdapterListSearch adapter;
    ListArtSearchPresenter presenter;
    int requestCode = 100;
    String beginDay="20160112",sort="newest",newDesks="",q="";
    int page=1;
    boolean isLoading=true;
    int pastVisibleItem,visibleItemCount,totalItemCount,previous_total=0;
    int viewThreshold=10;
    GridLayoutManager layoutManager;
    ProgressBar progressBar;
    RecyclerView recyclerView;
//    @BindView(R.id.recyclerview) RecyclerView recyclerView;
//    @BindView(R.id.progressBar) ProgressBar progressBar;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetWorkUtil.setContext(this);
      //  recyclerView=findViewById(R.id.recyclerview);
     //   progressBar=findViewById(R.id.progressBar);
       // ButterKnife.bind(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        recyclerView=binding.recyclerview;
        progressBar=binding.progressBar;
        setUpListView();
        ArticleSearchData searchData=new ArticleSearchDataImpl();
        presenter=new ListArtSearchPresenterImpl(this,searchData);
        presenter.getDocs(page);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){

            beginDay=bundle.getString("beginDay");
            sort=bundle.getString("sort");
            newDesks=bundle.getString("newDesks");
            page=1;
            presenter.getSearchFilter(beginDay,sort,newDesks);
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount=layoutManager.getChildCount();
                totalItemCount=layoutManager.getItemCount();
                pastVisibleItem=layoutManager.findFirstVisibleItemPosition();
                if(dy>0){
                    if(isLoading){
                            isLoading=false;
                            previous_total=totalItemCount;
                    }
                    if(!isLoading && (totalItemCount-visibleItemCount)<=(pastVisibleItem+viewThreshold)){
                        page++;
                        Toast.makeText(MainActivity.this, "page="+page, Toast.LENGTH_SHORT).show();
                        isLoading=true;
                        presenter.getLoadMore(beginDay,sort,q,newDesks, APIService.API_KEY,page);
                    }
                }
            }
        });
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
                q=s;
                page=1;
                presenter.getSearch(q);
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
    //    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdapterListSearch(MainActivity.this);
        adapter.setData(docList);
        this.recyclerView.setAdapter(adapter);
        adapter.setListener(new AdapterListSearch.IClickListener() {
            @Override
            public void onItemClick(Doc docs) {
              //  Toast.makeText(MainActivity.this, docs.getHeadline().getMain(), Toast.LENGTH_SHORT).show();
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                // set toolbar color and/or setting custom actions before invoking build()
                // Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent
                builder.setToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, docs.getWebUrl());
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                        requestCode,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.share);
                builder.setActionButton(bitmap, "Share Link", pendingIntent, true);
                CustomTabsIntent customTabsIntent = builder.build();
                // and launch the desired Url with CustomTabsIntent.launchUrl()
                customTabsIntent.launchUrl(MainActivity.this, Uri.parse(docs.getWebUrl()));
                    // add share action to menu list
               // builder.addMenuItem()

            }
        });
//        this.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListArtSearch(List<Doc> docs) {
            adapter.setData(docs);
    }

    @Override
    public void hideLoading() {
        if (progressBar.isShown()) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
