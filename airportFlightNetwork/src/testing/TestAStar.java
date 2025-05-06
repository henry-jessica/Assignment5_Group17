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
        Graph<Airport, Double> graph = new AdjacencyListGraph<>();
        Graph.Vertex<Airport> dubVert = graph.insertVertex(dub);
        Graph.Vertex<Airport> lonVert = graph.insertVertex(lon);
        Graph.Vertex<Airport> parVert = graph.insertVertex(par);
        Graph.Vertex<Airport> berVert = graph.insertVertex(ber);

        graph.insertEdge(dubVert, lonVert, 100.0); // DUB -> LON
        graph.insertEdge(lonVert, parVert, 200.0); // LON -> PAR
        graph.insertEdge(parVert, berVert, 300.0); // PAR -> BER
        graph.insertEdge(dubVert, berVert, 700.0); // DUB -> BER (direct but longer)

        // 3. Dummy heuristic (acts like Dijkstra)
        Function<Airport, Double> heuristic = airport -> 0.0;

        // 4. Run A* pathfinding
        List<Airport> path = AStar.findPath(graph, dubVert, berVert, heuristic);

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
