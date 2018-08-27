package com.programa.visualizacion.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;
    private int backButtonCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        this.setTitle("Calendario del evento");

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                String date =  dayOfMonth + "/" + (month + 1) + "/" + year ;
                Log.d(TAG, "onSelectedDayChange: dd/MM/yyyy" + date);
                Intent intent = new Intent(CalendarActivity.this, EventsActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(backButtonCount >= 1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            //int pid = android.os.Process.myPid();
            //android.os.Process.killProcess(pid);
        } else {
            Toast.makeText(this, "Pulse de nuevo para cerrar la aplicación.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
}
