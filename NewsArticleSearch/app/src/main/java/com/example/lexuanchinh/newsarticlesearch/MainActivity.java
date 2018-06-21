package com.example.lexuanchinh.newsarticlesearch;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.lexuanchinh.newsarticlesearch.Adapter.AdapterListSearch;
import com.example.lexuanchinh.newsarticlesearch.model.Doc;
import com.example.lexuanchinh.newsarticlesearch.model.ListSearch;
import com.example.lexuanchinh.newsarticlesearch.util.APIService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ListSearch listSearch;
    List<Doc> docList;
    RecyclerView recyclerView;
    AdapterListSearch adapter;
    public static int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);

           if(docList==null)
           {
               setUpListView();
               callAPI();
           }else {
               adapter.setData(docList);
           }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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
    private void callAPI() {
        //    progressBar.setVisibility(View.VISIBLE);
        APIService.getInstance().getArticlesearch("20170112","newest","news_desk:( Sports)",APIService.API_KEY,page++).enqueue(new Callback<ListSearch>() {
            @Override
            public void onResponse(Call<ListSearch> call, Response<ListSearch> response) {
                if(response.body()!=null){
                    listSearch=response.body();
                    docList=listSearch.getResponse().getDocs();
                    adapter.setData(docList);
                    //  Toast.makeText(MainActivity.this, response.body().getCopyright(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ListSearch> call, Throwable t) {

            }
        });


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
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        adapter = new AdapterListSearch(MainActivity.this);
        adapter.setData(docList);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        //this.recyclerView.addOnScrollListener(n);
    }
}
