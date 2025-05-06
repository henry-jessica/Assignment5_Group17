package testing;

import airportnetwork.*;
import java.util.*;
import java.util.function.Function;

public class GraphPerformanceTest {
    public static void main(String[] args) {
        System.out.println("Graph Performance Test Starting...");

        int NUM_AIRPORTS = 100_000_00;
        int EDGES_PER_NODE = 3;

        Graph<Airport> graph = new Graph<>();

        // 1. Generate nodes
        long start = System.nanoTime();
        List<Airport> nodes = new ArrayList<>();
        for (int i = 0; i < NUM_AIRPORTS; i++) {
            Airport a = new Airport("CODE" + i, "City" + i);
            nodes.add(a);
            graph.addNode(a);
        }
        long end = System.nanoTime();
        System.out.printf("Added %,d nodes in %.2f ms%n", NUM_AIRPORTS, (end - start) / 1e6);

        // 2. Generate edges (sparse random graph)
        Random rand = new Random(42);
        start = System.nanoTime();
        for (int i = 0; i < NUM_AIRPORTS; i++) {
            for (int j = 0; j < EDGES_PER_NODE; j++) {
                int targetIdx = rand.nextInt(NUM_AIRPORTS);
                if (targetIdx != i) {
                    graph.addEdge(nodes.get(i), nodes.get(targetIdx), 1 + rand.nextDouble() * 100);
                }
            }
        }
        end = System.nanoTime();
        int edgeCount = NUM_AIRPORTS * EDGES_PER_NODE;
        System.out.printf("Added approx %,d edges in %.2f ms%n", edgeCount, (end - start) / 1e6);

        // 3. Test core graph operations
        Airport test = nodes.get(42);
        start = System.nanoTime();
        int out = graph.outDegree(test);
        int in = graph.inDegree(test);
        end = System.nanoTime();
        System.out.printf("inDegree/outDegree for node in %.2f µs [in=%d, out=%d]%n", (end - start) / 1e3, in, out);

        // 4. Pathfinding performance
        Airport src = nodes.get(123);
        Airport dst = nodes.get(NUM_AIRPORTS - 1);

        Function<Airport, Double> heuristic = a -> 0.0;

        // A* Test
        start = System.nanoTime();
        List<Airport> path = AStar.findPath(graph, src, dst, heuristic);
        end = System.nanoTime();
        System.out.printf("A* from node[%d] to node[%d] took %.2f ms (%s)%n",
                123, NUM_AIRPORTS - 1, (end - start) / 1e6,
                path.isEmpty() ? "no path" : "path length = " + path.size());

        // Dijkstra Test
        start = System.nanoTime();
        List<Airport> dijkstraPath = Dijkstra.getPath(graph, src, dst);
        end = System.nanoTime();
        System.out.printf("Dijkstra from node[%d] to node[%d] took %.2f ms (%s)%n",
                123, NUM_AIRPORTS - 1, (end - start) / 1e6,
                dijkstraPath.isEmpty() ? "no path" : "path length = " + dijkstraPath.size());

        System.out.println("Performance test complete.");
    }
}