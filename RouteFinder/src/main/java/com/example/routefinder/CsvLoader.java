package com.example.routefinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvLoader {

    public static void loadFromCsv(UbahnGraph graph, String csvFilePath) {
        try {
            InputStream is = CsvLoader.class.getClassLoader().getResourceAsStream(csvFilePath);

            if (is == null) {
                throw new IllegalArgumentException("File not Found: " +csvFilePath);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine(); //skips header line (so it reads just the data)

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                if (tokens.length >= 4) {
                    String fromStationName = tokens[0].trim();
                    String toStationName = tokens[1].trim();
                    String lineNumber = tokens[2].trim();
                    String lineColour = tokens[3].trim();

                    double distance = (tokens.length >= 5) ? Double.parseDouble(tokens[4].trim()) : 1.0;

                    Station fromStation = graph.getOrCreateStation(fromStationName);
                    Station toStation = graph.getOrCreateStation(toStationName);

                    String lineName = "U" + lineNumber;

                    graph.addEdge(fromStation, toStation, distance, lineName, lineColour); //1.0 dummy distance
                    //graph.addEdge(toStation, fromStation, distance, lineName, lineColour); //two-way
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
