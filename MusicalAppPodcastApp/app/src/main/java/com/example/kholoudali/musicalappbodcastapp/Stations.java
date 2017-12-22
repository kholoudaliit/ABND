package com.example.kholoudali.musicalappbodcastapp;

/**
 * Created by kholoudali on 12/22/17.
 */

public class Stations {
    private String stationName;
    private String stationCatagroy;

    public Stations(String name, String category) {
        stationName = name;
        stationCatagroy = category;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationCatagroy() {
        return stationCatagroy;
    }

    public void setStationCatagroy(String stationCatagroy) {
        this.stationCatagroy = stationCatagroy;
    }


}
