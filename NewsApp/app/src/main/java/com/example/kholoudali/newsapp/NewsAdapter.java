package com.example.kholoudali.newsapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kholoudali on 1/12/18.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, ArrayList<News> NewsList) {
        super(context, 0, NewsList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        News news = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_layout, parent, false);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.article_name);
        TextView auther = (TextView) convertView.findViewById(R.id.auther);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView section = (TextView) convertView.findViewById(R.id.section_name);


        // Populate the data into the template view using the data object
        name.setText(news.getArticle_name());
        auther.setText(news.getAuther());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd" );
        date.setText(format.format(news.getDate()));
        section.setText(news.getSection_name());

        // Return the completed view to render on screen
        return convertView;
    }
}