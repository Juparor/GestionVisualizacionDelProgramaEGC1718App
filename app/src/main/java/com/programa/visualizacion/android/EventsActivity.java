package com.programa.visualizacion.android;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity {

    ListView eventsList;
    List<Event> events = new ArrayList<>();
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        eventsList = findViewById(R.id.events_list);

        this.setTitle("Lista de los eventos del día");

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");

        if (date.contains("25")) {
            events = MainActivity.mondayEvents;
        } else if (date.contains("26")) {
            events = MainActivity.tuesdayEvents;
        } else if (date.contains("27")) {
            events = MainActivity.wednesdayEvents;
        } else if (date.contains("28")) {
            events = MainActivity.thursdayEvents;
        } else if (date.contains("29")) {
            events = MainActivity.fridayEvents;
        }
        EventsListAdapter eventsListAdapter = new EventsListAdapter(this, events);
        eventsList.setAdapter(eventsListAdapter);

        eventsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent detailsIntent = new Intent(view.getContext(), DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("event", events.get(position));
                detailsIntent.putExtras(bundle);
                startActivity(detailsIntent);
            }
        });
    }

    class EventsListAdapter extends ArrayAdapter<Event> {
        Context context;
        List<Event> events;

        EventsListAdapter(Context c, List<Event> events) {
            super(c, R.layout.row, R.id.title, events);
            this.context = c;
            this.events = events;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);

            TextView rowTitle = row.findViewById(R.id.title);
            TextView rowTime = row.findViewById(R.id.time);
            TextView rowSpeaker = row.findViewById(R.id.speaker);

            rowTitle.setText(events.get(position).getTitle().trim());
            rowTime.setText((formatter.format(events.get(position).getBeginningHour()) + " - " + formatter.format(events.get(position).getEndingHour())).trim());
            rowSpeaker.setText(events.get(position).getSpeakers());

            return row;
        }
    }
}
