package com.example.lexuanchinh.newsarticlesearch.articlesearch.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lexuanchinh.newsarticlesearch.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity {
    @BindView(R.id.spinner) Spinner spinner;
    @BindView(R.id.txtBeginDay) TextView txtBeginDate;
    @BindView(R.id.btnSave) Button btnSave;
    @BindView(R.id.btnExit) Button btnExit;
    @BindView(R.id.chkArt) CheckBox chkArt;
    @BindView(R.id.chkFashion) CheckBox chkFashion;
    @BindView(R.id.chkSports) CheckBox chkSport;
    int day,month,year;
    String beginDay="01012018",sort="";
    String newDesk="news_desk:(";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
         ButterKnife.bind(this);
        SortOderSpiner();
        datePickerDialog();
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FilterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkArt.isChecked()){
                    chkArt.setChecked(true);
                    Log.d("abc","a");
                    newDesk+="Arst ";
                }
                if(chkFashion.isChecked())
                {
                    chkFashion.setChecked(true);
                    Log.d("abc","b");
                    newDesk+="Fashion & Style ";
                }
                if(chkSport.isChecked()){
                    chkSport.setChecked(true);
                    Log.d("abc","c");
                    newDesk+="Sports";
                }
                newDesk+=")";
                Intent intent=new Intent(FilterActivity.this,MainActivity.class);
                Log.d("abc",beginDay+sort+newDesk);
                intent.putExtra("beginDay",beginDay);
                intent.putExtra("sort",sort);
                intent.putExtra("newDesks",newDesk);
                startActivity(intent);
            }
        });

    }
    private void SortOderSpiner() {
        List<String> list=new ArrayList<>();
        list.add("Newest");
        list.add("Oldest");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        sort="newest";
                        break;
                    case 1:
                        sort="oldest";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    void datePickerDialog(){
         Calendar c=Calendar.getInstance();
         year=c.get(Calendar.YEAR);
         month=c.get(Calendar.MONTH);
         day=c.get(Calendar.DAY_OF_MONTH);
        month+=1;
        txtBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(FilterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dateOfMonth) {
                        beginDay="";
                        monthOfYear+=1;
                        String a="01",b="01",c="2018";
                        txtBeginDate.setText(dateOfMonth+"/"+monthOfYear+"/"+year);
                        a=String.valueOf(dateOfMonth);
                        b=String.valueOf(monthOfYear);
                        c=String.valueOf(year);
                        if(dateOfMonth<10){
                         a="0"+String.valueOf(dateOfMonth);
                        }
                        if(monthOfYear<10){
                            b="0"+String.valueOf(monthOfYear);
                        }

                        beginDay= a+b+c;
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

    }
}
