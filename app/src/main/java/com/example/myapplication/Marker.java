package com.example.myapplication;

import com.google.android.gms.maps.model.LatLng;

public class Marker {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String title;
    public LatLng latLng;

    public Marker(String title, LatLng latLng) {
        this.title = title;
        this.latLng = latLng;
    }
}
