package com.programa.visualizacion.android;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.programa.visualizacion.android.ExcelImporter.importExcelDataFileOne;
import static com.programa.visualizacion.android.ExcelImporter.importExcelDataFileOneSchedule;
import static com.programa.visualizacion.android.ExcelImporter.importExcelDataFileTwo;
import static com.programa.visualizacion.android.ExcelImporter.importExcelDataFileTwoSchedule;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView fecha;
    private Button botonCalendario;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager assetManager = getAssets();

        List<Event> mondayEvents = new ArrayList<>();
        List<Event> tuesdayEvents = new ArrayList<>();
        List<Event> wednesdayEvents = new ArrayList<>();
        List<Event> thursdayEvents = new ArrayList<>();
        List<Event> fridayEvents = new ArrayList<>();

        try {
            InputStream programme1 = assetManager.open("programme1.xlsx");
            List<String> list1 = importExcelDataFileOne(programme1);

            InputStream programme2 = assetManager.open("programme2.xlsx");
            List<String> list2 = importExcelDataFileTwo(programme2);

            InputStream programme3 = assetManager.open("programme1.xlsx");
            List<String> list3 = importExcelDataFileOneSchedule(programme3);

            InputStream programme4 = assetManager.open("programme2.xlsx");
            List<String> list4 = importExcelDataFileTwoSchedule(programme4);

            DBController dbController = new DBController(this);
            SQLiteDatabase database = dbController.getReadableDatabase();

            DBController.insertRows(list1, list2, list3, list4, database);

            Cursor c = database.rawQuery("SELECT FECHA, HORA_INICIO, HORA_FIN, CHARLAS.EVENTO, CHARLAS.TITULO, CHARLAS.PONENTES, CHARLAS.RESUMEN FROM PROGRAMA INNER JOIN CHARLAS ON CHARLAS.SESSIONCODE = PROGRAMA.SESSIONCODE", null);

            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    String date = c.getString(0);
                    String beginning = c.getString(1);
                    String ending = c.getString(2);
                    String title = c.getString(4);

                    SimpleDateFormat format = new SimpleDateFormat("H:m");
                    Date beginningHour = format.parse(beginning);
                    Date endingHour = format.parse(ending);

                    Event event = new Event();
                    event.setBeginningHour(beginningHour);
                    event.setEndingHour(endingHour);
                    event.setTitle(title);

                    if (date.contains("25")) {
                        mondayEvents.add(event);
                    } else if (date.contains("26")) {
                        tuesdayEvents.add(event);
                    } else if (date.contains("27")) {
                        wednesdayEvents.add(event);
                    } else if (date.contains("28")) {
                        thursdayEvents.add(event);
                    } else {
                        fridayEvents.add(event);
                    }
                } while (c.moveToNext());
            }

            c = database.rawQuery("SELECT FECHA, HORA_INICIO, HORA_FIN, TITULO  FROM PROGRAMA WHERE TITULO !=''", null);

            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    String date = c.getString(0);
                    String beginning = c.getString(1);
                    String ending = c.getString(2);
                    String title = c.getString(3);

                    SimpleDateFormat format = new SimpleDateFormat("H:m");
                    Date beginningHour = format.parse(beginning);
                    Date endingHour = format.parse(ending);

                    Event event = new Event();
                    event.setBeginningHour(beginningHour);
                    event.setEndingHour(endingHour);
                    event.setTitle(title);

                    if (date.contains("25")) {
                        mondayEvents.add(event);
                    } else if (date.contains("26")) {
                        tuesdayEvents.add(event);
                    } else if (date.contains("27")) {
                        wednesdayEvents.add(event);
                    } else if (date.contains("28")) {
                        thursdayEvents.add(event);
                    } else {
                        fridayEvents.add(event);
                    }
                } while (c.moveToNext());
            }

            Collections.sort(mondayEvents);
            Collections.sort(tuesdayEvents);
            Collections.sort(wednesdayEvents);
            Collections.sort(thursdayEvents);
            Collections.sort(fridayEvents);

        } catch (Exception e) {
            Log.e("MainActivity: ", e.getMessage());
        }

        fecha = (TextView) findViewById(R.id.fecha);
        botonCalendario = (Button) findViewById(R.id.botonCalendario);
        listView = (ListView) findViewById(R.id.list);

        Intent incoming = getIntent();

        String date = incoming.getStringExtra("fecha");
        fecha.setText("");

        ArrayList<String> list = new ArrayList<String>();
        ArrayList<Event> eventsToShow;

        if (date != null){
            if (date.contains("25")) {
                eventsToShow = new ArrayList<>(mondayEvents);
            } else if (date.contains("26")) {
                eventsToShow = new ArrayList<>(tuesdayEvents);
            } else if (date.contains("27")) {
                eventsToShow = new ArrayList<>(wednesdayEvents);
            } else if (date.contains("28")) {
                eventsToShow = new ArrayList<>(thursdayEvents);
            } else {
                eventsToShow = new ArrayList<>(fridayEvents);
            }

            for (Event event : eventsToShow) {
                list.add(event.getBeginningHour().getHours() + ":" + event.getBeginningHour().getMinutes() + " - " + event.getEndingHour().getHours() + ":" + event.getEndingHour().getMinutes() + " | " + event.getTitle());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1,
                    list.toArray(new String[list.size()]));
            listView.setAdapter(adapter);
        }




        botonCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }

}
