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

   //Find any route from start to end (DFS)
    public List<Station> findRoute(String startName, String endName) {
        Station start = getStation(startName);
        Station end = getStation(endName);

        if (start == null || end == null) {
            System.out.println("Start or End station not found");
            return Collections.emptyList();
        }

        List<Station> path = new ArrayList<>();
        Set<Station> visited = new HashSet<>();

        if (dfs(start, end, path, visited)) {
            return path;
        }
        else {
            System.out.println("No route found");
            return Collections.emptyList();
        }
    }

    //Helper DFS method
    private boolean dfs(Station current, Station end, List<Station> path, Set<Station> visited) {
        path.add(current);
        visited.add(current);

        if (current.equals(end)) {
            return true;
        }

        for (Edge edge : current.getNeighbours()) {
            Station neighbour = edge.getTo();
            if (!visited.contains(neighbour)) {
                if (dfs(neighbour, end, path, visited)) {
                    return true;
                }
            }
        }

        path.remove(path.size() - 1); //backtrack
        return false;
    }
}
