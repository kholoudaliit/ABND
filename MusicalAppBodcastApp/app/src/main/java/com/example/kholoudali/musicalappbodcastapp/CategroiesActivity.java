package com.example.kholoudali.musicalappbodcastapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CategroiesActivity extends AppCompatActivity {

    private ListView categroiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categroies);

        //set Data source
        ArrayList<String> categroies = new ArrayList<String>();
        categroies.add("Tech");
        categroies.add("Soical");
        categroies.add("Scince");
        categroies.add("Cooking");
        categroies.add("Fashion");
        categroies.add("Business");
        categroies.add("Kids");
        categroies.add("News");

        //set simple Adapter
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categroies);
        categroiesList = (ListView) findViewById(R.id.categroies_list);
        categroiesList.setAdapter(itemsAdapter);


    }
}
