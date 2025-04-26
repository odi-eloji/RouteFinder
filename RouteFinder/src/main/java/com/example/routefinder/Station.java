package com.example.routefinder;

import java.util.List;

public class Station {
    String name;
     private List<Edge> neighbours; //list of outgoing connections

    public Station(String name) {
        this.name = name;
        this.neighbours = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Edge> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Edge edge) {
        neighbours.add(edge);
    }

    @Override
    public String toString() {
        return name;
    }
}
