package com.example.routefinder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UbahnGraph graph = new UbahnGraph();
        CsvLoader.loadFromCsv(graph, "vienna_subway.csv");

        List<Station> path = graph.findRouteBFS("Oberlaa", "Neulaa");
        if (path != null) {
            for (Station station : path) {
                System.out.println(station.getName() + " -> ");
            }
            System.out.println("End");
        }

        List<Station> route = graph.findRoute("Oberlaa", "Stadtpark");
        if (!route.isEmpty()) {
            System.out.println("Route found: ");
            for (Station station : route) {
                System.out.println(station.getName());
            }
        }
        else {
            System.out.println("No route found.");
        }
    }
}
