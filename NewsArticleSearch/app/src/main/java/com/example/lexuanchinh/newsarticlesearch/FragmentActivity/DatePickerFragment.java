package com.example.lexuanchinh.newsarticlesearch.FragmentActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment  {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog.OnDateSetListener listener=(DatePickerDialog.OnDateSetListener)getActivity();
        return new DatePickerDialog(getActivity(),listener,year,month,day);
    }

}
