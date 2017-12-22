package com.example.kholoudali.musicalappbodcastapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class StationsActivity extends AppCompatActivity {

    private ListView stationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);
        // Data sourse
        ArrayList<Stations> stations = new ArrayList<Stations>();

        stations.add(new Stations("Android Developer", "Tech bodcast"));
        stations.add(new Stations("Google", "Tech bodcast"));
        stations.add(new Stations("Geeks inc.", "Networking bodcast"));
        stations.add(new Stations("Apple developer", "Tech bodcast"));
        stations.add(new Stations("QA Team", "Quality bodcast"));
        stations.add(new Stations("Funny bunny", "Social bodcast"));

        // set data adapter
        StationAdapter adapter = new StationAdapter(this, stations);

        stationList = (ListView) findViewById(R.id.station_list);
        stationList.setAdapter(adapter);

    }


    public class StationAdapter extends ArrayAdapter<Stations> {
        public StationAdapter(Context context, ArrayList<Stations> stations) {
            super(context, 0, stations);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Stations station = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.podcast_layout, parent, false);
            }
            // Lookup view for data population
            TextView Name = (TextView) convertView.findViewById(R.id.name);
            TextView category = (TextView) convertView.findViewById(R.id.auther);
            ImageView podcastIcon = (ImageView) convertView.findViewById(R.id.ic_img);
            ImageView playicon = (ImageView) convertView.findViewById(R.id.playbtn);
            // Populate the data into the template view using the data object
            Name.setText(station.getStationName());
            category.setText(station.getStationCatagroy());
            podcastIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_stations));
            playicon.setImageDrawable(ContextCompat.getDrawable(getContext(), android.R.drawable.ic_menu_share));

            // Return the completed view to render on screen
            return convertView;
        }
    }
}