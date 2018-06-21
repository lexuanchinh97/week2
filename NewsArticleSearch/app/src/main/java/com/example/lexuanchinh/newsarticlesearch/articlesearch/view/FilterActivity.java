package com.example.lexuanchinh.newsarticlesearch.articlesearch.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class FilterActivity extends AppCompatActivity {
    Spinner spinner;
    TextView txtBeginDate;
    int day,month,year;
    Button btnSave;
    CheckBox chkArt,chkFashion,chkSport;
    Boolean checkArt,checkFashion,checkSports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        AddControls();
        SortOderSpiner();
        datePickerDialog();
        checkValues();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(chkFashion.isChecked())
//                {
//                    Toast.makeText(FilterActivity.this, chkFashion.getText().toString(), Toast.LENGTH_SHORT).show();
//                }
                Intent intent=new Intent(FilterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void checkValues() {
        if(chkFashion.isChecked()){
            chkFashion.setChecked(true);
            Toast.makeText(this, "abc", Toast.LENGTH_SHORT).show();
        }
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
                        Toast.makeText(FilterActivity.this, "newer", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(FilterActivity.this, "older", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void AddControls() {
        spinner=findViewById(R.id.spinner);
        txtBeginDate=findViewById(R.id.txtBeginDay);
        btnSave=findViewById(R.id.btnSave);
        chkArt=findViewById(R.id.chkArt);
        chkFashion=findViewById(R.id.chkFashion);
        chkSport=findViewById(R.id.chkSports);
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
                        monthOfYear+=1;
                        txtBeginDate.setText(dateOfMonth+"/"+monthOfYear+"/"+year);
                        Toast.makeText(FilterActivity.this, dateOfMonth+"/"+monthOfYear+"/"+year, Toast.LENGTH_SHORT).show();
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

    }
}
