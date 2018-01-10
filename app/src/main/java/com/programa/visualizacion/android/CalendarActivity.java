package com.programa.visualizacion.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int anyo, int mes, int diaMes) {
                String fecha =  diaMes + "/" + (mes + 1) + "/" + anyo ;
                Log.d(TAG, "onSelectedDayChange: dd/MM/yyyy" + fecha);
                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                intent.putExtra("fecha", fecha);
                startActivity(intent);

            }
        });
    }
}
