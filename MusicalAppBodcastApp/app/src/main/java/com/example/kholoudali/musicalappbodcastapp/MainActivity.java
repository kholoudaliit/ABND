package com.example.kholoudali.musicalappbodcastapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout myPodcats;
    private LinearLayout station;
    private LinearLayout categories;
    private LinearLayout search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define the layout and set click listener
        myPodcats = (LinearLayout) findViewById(R.id.mypodcast_layout);
        myPodcats.setOnClickListener(this);
        station = (LinearLayout) findViewById(R.id.station_layout);
        station.setOnClickListener(this);
        categories = (LinearLayout) findViewById(R.id.categroies_layout);
        categories.setOnClickListener(this);
        search = (LinearLayout) findViewById(R.id.search_layout);
        search.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        // implement the onClick code based on clicked layout id
        switch (view.getId()) {
            case R.id.mypodcast_layout:
                Intent intent_podcast = new Intent(this, MyPodcastsActivity.class);
                startActivity(intent_podcast);
                break;

            case R.id.station_layout:
                Intent intent_station = new Intent(this, StationsActivity.class);
                startActivity(intent_station);
                break;

            case R.id.categroies_layout:
                Intent intent_categroies = new Intent(this, CategroiesActivity.class);
                startActivity(intent_categroies);
                break;

            case R.id.search_layout:
                Intent intent_search = new Intent(Intent.ACTION_VIEW);
                intent_search.setData(Uri.parse("http://google.com"));
                startActivity(intent_search);
                break;

        }

    }
}
