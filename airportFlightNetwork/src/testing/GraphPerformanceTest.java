package testing;

import airportnetwork.*;
import java.util.*;
import java.util.function.Function;

public class GraphPerformanceTest {
    public static void main(String[] args) {
        System.out.println("input_size,node_insert_ms,edge_insert_ms,degree_lookup_us,astar_ms,dijkstra_ms");

        int[] inputSizes = {
                10_000,
                20_000,
                40_000,
                80_000,
                160_000,
                320_000,
                640_000,
                1_280_000,
                2_560_000,
                5_120_000
        };

        for (int size : inputSizes) {
            int EDGES_PER_NODE = 3;
            Graph<Airport> graph = new Graph<>();
            List<Airport> nodes = new ArrayList<>(size);
            Random rand = new Random(42);

            // 1. Insert nodes
            long start = System.nanoTime();
            for (int i = 0; i < size; i++) {
                Airport a = new Airport("CODE" + i, "City" + i);
                nodes.add(a);
                graph.addNode(a);
            }
            long end = System.nanoTime();
            double nodeInsertMs = (end - start) / 1e6;

            // 2. Insert edges
            start = System.nanoTime();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < EDGES_PER_NODE; j++) {
                    int targetIdx = rand.nextInt(size);
                    if (targetIdx != i) {
                        graph.addEdge(nodes.get(i), nodes.get(targetIdx), 1 + rand.nextDouble() * 100);
                    }
                }
            }
            end = System.nanoTime();
            double edgeInsertMs = (end - start) / 1e6;

            // 3. Degree lookup
            Airport probe = nodes.get(size / 10); // sample node
            start = System.nanoTime();
            int inDeg = graph.inDegree(probe);
            int outDeg = graph.outDegree(probe);
            end = System.nanoTime();
            double degreeLookupUs = (end - start) / 1e3;

            // 4. A* test (dummy heuristic)
            Airport src = nodes.get(size / 1000);
            Airport dst = nodes.get(size - 1);
            Function<Airport, Double> heuristic = a -> 0.0;

            start = System.nanoTime();
            List<Airport> aStarPath = AStar.findPath(graph, src, dst, heuristic);
            end = System.nanoTime();
            double aStarMs = (end - start) / 1e6;

            // 5. Dijkstra test
            start = System.nanoTime();
            List<Airport> dijkstraPath = Dijkstra.getPath(graph, src, dst);
            end = System.nanoTime();
            double dijkstraMs = (end - start) / 1e6;

            // 6. Output CSV row
            System.out.printf(Locale.US,
                    "%d,%.2f,%.2f,%.2f,%.2f,%.2f%n",
                    size, nodeInsertMs, edgeInsertMs, degreeLookupUs, aStarMs, dijkstraMs);
        }
    }
}