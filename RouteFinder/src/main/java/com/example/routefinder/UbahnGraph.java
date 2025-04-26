package com.example.routefinder;

import java.util.List;
import java.util.Map;

public class UbahnGraph {
   private Map<String, Station> stations;

    public UbahnGraph() {
        this.stations = new HashMap<>();
    }

    public Station getOrCreateStation(String name) {
        Station station = stations.get(name);
        if (station == null) {
            station = new Station(name);
            stations.put(name, station);
        }
        return station;
    }

    public void addEdge(Station from, Station to, double distance, String lineName, String lineColour) {
        Edge edge = new Edge(from, to, distance, lineName);
        from.addNeighbour(edge);

        Edge reverseEdge = new Edge(to, from, distance, lineName);
        to.addNeighbour(reverseEdge);
    }

    public Station getStation(String name) {
        return stations.get(name);
    }

    public Collection<Station> getAllStations() {
        return stations.values();
    }
}
