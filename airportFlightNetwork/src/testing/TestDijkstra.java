package testing;

import airportnetwork.*;
import java.util.*;

public class TestDijkstra {
    public static void main(String[] args) {
        // 1. Define airports
        Airport dub = new Airport("DUB", "Dublin");
        Airport lon = new Airport("LON", "London");
        Airport par = new Airport("PAR", "Paris");
        Airport ber = new Airport("BER", "Berlin");

        // 2. Create and populate the graph
        Graph<Airport> graph = new Graph<>();
        graph.addNode(dub);
        graph.addNode(lon);
        graph.addNode(par);
        graph.addNode(ber);

        graph.addEdge(dub, lon, 100); // DUB -> LON
        graph.addEdge(lon, par, 200); // LON -> PAR
        graph.addEdge(par, ber, 300); // PAR -> BER
        graph.addEdge(dub, ber, 700); // DUB -> BER (direct but longer)

        // 4. Run A* pathfinding
        Map<Airport, Double> path = Dijkstra.computeShortestPath(graph, par);

        // 5. Print the path
        if (!path.isEmpty()) {
            System.out.println("Shortest path from Dublin to other airports:");
            for (var a : path.keySet()) {
                System.out.println(a + ", " + path.get(a));
            }
        } else {
            System.out.println("No path found.");
        }
    }
}
