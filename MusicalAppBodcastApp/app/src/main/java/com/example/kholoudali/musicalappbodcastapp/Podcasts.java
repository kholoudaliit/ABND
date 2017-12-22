package com.example.kholoudali.musicalappbodcastapp;

import android.graphics.drawable.Drawable;

/**
 * Created by kholoudali on 12/22/17.
 */

public class Podcasts {
    // Define podcast feild
    private String name;
    private String auther;

    Podcasts(String name, String auther) {
        this.name = name;
        this.auther = auther;
    }

    // Feild seter and getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }


}
