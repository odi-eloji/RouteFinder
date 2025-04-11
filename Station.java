package com.example.routefinder;

import java.util.List;

public class Station {
    String name;
    List<String> lines;
    double latitude;
    double longitude;

    public Station(String name, List<String> lines, double latitude, double longitude) {
        this.name = name;
        this.lines = lines;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String addLine(List<String> lines) {
        return null;
    }




}
