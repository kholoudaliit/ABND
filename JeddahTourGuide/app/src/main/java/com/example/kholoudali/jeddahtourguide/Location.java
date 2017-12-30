package com.example.kholoudali.jeddahtourguide;

import android.graphics.drawable.Drawable;

public class Location  {

    private String locationName;
    private Drawable locationImg;
    private String locationDescription;

    public Location(String name, Drawable img , String description){
        locationName=name;
        locationImg= img;
        locationDescription= description;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Drawable getLocationImg() {
        return locationImg;
    }

    public void setLocationImg(Drawable locationImg) {
        this.locationImg = locationImg;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

}
