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
   
   public List<Station> findRouteBFS(String startName, String endName) {
        Station start = stations.get(startName);
        Station end = stations.get(endName);

        if (start == null || end == null) {
            System.out.println("Start or End station not found");
            return null;
        }

        //Set up a queue for BFS
        Queue<List<Station>> queue = new LinkedList<>();
        Set<Station> visited = new HashSet<>();

        //Start with a path containing only the start station
        List<Station> initialPath = new ArrayList<>();
        initialPath.add(start);
        queue.add(initialPath);

        while (!queue.isEmpty()) {
            List<Station> path = queue.poll();
            Station current = path.get(path.size() - 1);

            if (current.equals(end)) {
                return path; //Found the destination
            }

            visited.add(current);

            for (Edge edge : current.getNeighbours()) {
                Station neighbour = edge.getTo();
                if (!visited.contains(neighbour)) {
                    List<Station> newPath = new ArrayList<>(path);
                    newPath.add(neighbour);
                    queue.add(newPath);
                }
            }
        }

        //If no path found
        System.out.println("No route found");
        return null;
    }
}
