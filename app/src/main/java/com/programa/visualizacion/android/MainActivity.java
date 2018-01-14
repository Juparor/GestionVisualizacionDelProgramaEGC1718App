package com.programa.visualizacion.android;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import static com.programa.visualizacion.android.ExcelImporter.importExcelDataFileOneSchedule;
import static com.programa.visualizacion.android.ExcelImporter.importExcelDataFileTwoSchedule;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView fecha;
    private Button botonCalendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager assetManager = getAssets();
        try{
            InputStream programme1 = assetManager.open("programme1.xlsx");
            List<String> list1 = importExcelDataFileOneSchedule(programme1);

            InputStream programme2 = assetManager.open("programme2.xlsx");
            List<String> list2 = importExcelDataFileTwoSchedule(programme2);

            DBController dbController = new DBController(this);
            SQLiteDatabase database = dbController.getReadableDatabase();

            DBController.insertRows(list1, list2, database);

        } catch(Exception e){
            Log.e("MainActivity: ", e.getMessage());
        }

        fecha = (TextView) findViewById(R.id.fecha);
        botonCalendario = (Button) findViewById(R.id.botonCalendario);


        Intent incoming = getIntent();
        String date = incoming.getStringExtra("fecha");
        fecha.setText(date);

        botonCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }

}
