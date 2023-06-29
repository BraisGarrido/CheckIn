package com.proyecto.checkin.datepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    private DatePickerDialog.OnDateSetListener listener;

    public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener){
        DatePickerFragment fragment=new DatePickerFragment();
        fragment.setListener(listener);
        return fragment;
    }

    private void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener=listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Creamos los datos del picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        //Creamos nueva instancia de DatePickerDialog y lo devolvemos
        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }
}
