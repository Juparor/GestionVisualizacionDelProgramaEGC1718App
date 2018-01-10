package com.programa.visualizacion.android;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView fecha;
    private Button botonCalendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBController dbController = new DBController(this);

        SQLiteDatabase database = dbController.getReadableDatabase();

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
