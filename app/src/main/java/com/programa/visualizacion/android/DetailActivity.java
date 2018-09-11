package com.programa.visualizacion.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent incomingIntent = getIntent();

        Bundle bundle = incomingIntent.getExtras();
        Event event = (Event) bundle.getSerializable("event");

        TextView eventTitleDisplay = findViewById(R.id.event_title);
        TextView eventSpeakersDisplay = findViewById(R.id.event_speakers);
        TextView eventDescriptionDisplay = findViewById(R.id.event_description);

        String title = (event.getTitle()== null || "".equals(event.getTitle())) ? "No hay información disponible sobre el título del evento. Disculpe las molestias." : event.getTitle().trim();
        String speakers = (event.getSpeakers()== null || "".equals(event.getSpeakers())) ? "No hay información disponible sobre los ponentes del evento. Disculpe las molestias." : event.getSpeakers().trim();
        String description = (event.getDescription()== null || "".equals(event.getDescription())) ? "No hay información disponible sobre la descripción del evento. Disculpe las molestias." : event.getDescription().trim();

        eventTitleDisplay.setText(title);
        eventSpeakersDisplay.setText(speakers);
        eventDescriptionDisplay.setText(description);

        this.setTitle(title);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DetailActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }

}
