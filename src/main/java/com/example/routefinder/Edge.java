package com.example.routefinder;

public class Edge {
    Station from;
    Station to;
    double distance;
    String lineName;

    public Edge(Station from, Station to, double distance, String lineName) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.lineName = lineName;
    }

    public Station getFrom() {
        return from;
    }

    public Station getTo() {
        return to;
    }

    public double getDistance() {
        return distance;
    }

    public String getLineName() {
        return lineName;
    }
}
