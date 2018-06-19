package com.example.lexuanchinh.newsarticlesearch;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.lexuanchinh.newsarticlesearch.API.APIInterface;
import com.example.lexuanchinh.newsarticlesearch.model.ListSearch;
import com.example.lexuanchinh.newsarticlesearch.util.APIService;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {
    List list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
           callAPI();
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
        APIService.getInstance().getArticlesearch(APIService.API_KEY).enqueue(new Callback<ListSearch>() {
            @Override
            public void onResponse(Call<ListSearch> call, Response<ListSearch> response) {
                if(response.body()!=null){

                    //  Toast.makeText(MainActivity.this, response.body().getCopyright(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ListSearch> call, Throwable t) {

            }
        });


    }


}
