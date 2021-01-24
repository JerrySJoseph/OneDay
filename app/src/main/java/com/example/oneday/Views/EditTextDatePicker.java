package com.example.oneday.Views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class EditTextDatePicker  implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    EditText _editText;
    private int _day;
    private int _month;
    private int _birthYear;
    private Context _context;
    private long selectedDate;

    public EditTextDatePicker(Context context, int editTextViewID)
    {
        Activity act = (Activity)context;
        this._editText = (EditText)act.findViewById(editTextViewID);
        this._editText.setOnClickListener(this);
        this._context = context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        _birthYear = year;
        _month = monthOfYear;
        _day = dayOfMonth;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        selectedDate = calendar.getTimeInMillis();
        updateDisplay();
    }
    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(_context, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();

    }

    // updates the date in the birth date EditText
    private void updateDisplay() {

        _editText.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(_day).append("/").append(_month + 1).append("/").append(_birthYear).append(" "));
    }

    public long getSelectedDate() {
        return selectedDate;
    }
}