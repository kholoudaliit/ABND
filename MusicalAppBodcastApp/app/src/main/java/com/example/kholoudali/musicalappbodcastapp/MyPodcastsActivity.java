package com.example.kholoudali.musicalappbodcastapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

public class MyPodcastsActivity extends AppCompatActivity {

    private ListView podcastsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_podcasts);

        // Data sourse
        ArrayList<Podcasts> podcasts = new ArrayList<Podcasts>();

        podcasts.add(new Podcasts("Hello World ", "Android Developer"));
        podcasts.add(new Podcasts("Design Your App ", "Google"));
        podcasts.add(new Podcasts("Web servieces", "Geeks inc."));
        podcasts.add(new Podcasts("Publish your app ", "Apple developer"));
        podcasts.add(new Podcasts("Unit Testing ", "QA Team"));
        podcasts.add(new Podcasts("Hello World ", "Android Developer"));
        podcasts.add(new Podcasts("Design Your App ", "Google"));
        podcasts.add(new Podcasts("Web servieces", "Geeks inc."));
        podcasts.add(new Podcasts("Publish your app ", "Apple developer"));
        podcasts.add(new Podcasts("Unit Testing ", "QA Team"));

        // set the adapter
        PodcastsAdapter adapter = new PodcastsAdapter(this, podcasts);
        podcastsListView = (ListView) findViewById(R.id.podcasts_list);
        podcastsListView.setAdapter(adapter);
    }

    public class PodcastsAdapter extends ArrayAdapter<Podcasts> {
        public PodcastsAdapter(Context context, ArrayList<Podcasts> podcast) {
            super(context, 0, podcast);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Podcasts podcastRow = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.podcast_layout, parent, false);
            }
            // Lookup view for data population
            TextView Name = (TextView) convertView.findViewById(R.id.name);
            TextView auther = (TextView) convertView.findViewById(R.id.auther);
            ImageView podcastIcon = (ImageView) convertView.findViewById(R.id.ic_img);
            ImageView playicon = (ImageView) convertView.findViewById(R.id.playbtn);
            // Populate the data into the template view using the data object
            Name.setText(podcastRow.getName());
            auther.setText(podcastRow.getAuther());
            podcastIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_podcaster));
            playicon.setImageDrawable(ContextCompat.getDrawable(getContext(), android.R.drawable.ic_media_play));

            // Return the completed view to render on screen
            return convertView;
        }
    }
}


