package testing;

import airportnetwork.*;

import java.util.*;
import java.util.function.Function;

public class TestAStar {
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

        graph.addEdge(dub, lon, 100);  // DUB -> LON
        graph.addEdge(lon, par, 200);  // LON -> PAR
        graph.addEdge(par, ber, 300);  // PAR -> BER
        graph.addEdge(dub, ber, 700);  // DUB -> BER (direct but longer)

        // 3. Dummy heuristic (acts like Dijkstra)
        Function<Airport, Double> heuristic = airport -> 0.0;

        // 4. Run A* pathfinding
        List<Airport> path = AStar.findPath(graph, dub, ber, heuristic);

        // 5. Print the path
        if (!path.isEmpty()) {
            System.out.println("Shortest path from Dublin to Berlin:");
            for (Airport a : path) {
                System.out.println(a);
            }
        } else {
            System.out.println("No path found.");
        }
    }
}
