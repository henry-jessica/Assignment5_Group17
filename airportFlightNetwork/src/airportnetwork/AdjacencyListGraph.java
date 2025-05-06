package airportnetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyListGraph<V, E> implements Graph<V, E> {

    private class VertexImpl implements Vertex<V> {
        private final V element;

        public VertexImpl(V element) {
            this.element = element;
        }

        @Override
        public V getElement() {
            return element;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    private class EdgeImpl implements Edge<E> {
        private final E element;
        private final Vertex<V> u, v;

        public EdgeImpl(Vertex<V> u, Vertex<V> v, E element) {
            this.u = u;
            this.v = v;
            this.element = element;
        }

        @Override
        public E getElement() {
            return element;
        }

        public Vertex<V> getU() {
            return u;
        }

        public Vertex<V> getV() {
            return v;
        }

        @Override
        public String toString() {
            return "(" + u.getElement() + ", " + v.getElement() + "): " + element;
        }
    }

    private final Map<Vertex<V>, List<Edge<E>>> adjacencyMap = new HashMap<>();
    private final List<Vertex<V>> vertexList = new ArrayList<>();
    private final List<Edge<E>> edgeList = new ArrayList<>();

    // Accessors
    @Override
    public int numVertices() {
        return vertexList.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertexList;
    }

    @Override
    public int numEdges() {
        return edgeList.size();
    }

    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
        for (Edge<E> e : adjacencyMap.getOrDefault(u, List.of())) {
            EdgeImpl edge = (EdgeImpl) e;
            if ((edge.getU() == u && edge.getV() == v) || (edge.getU() == v && edge.getV() == u))
                return e;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Vertex<V>[] endVertices(Edge<E> e) {
        EdgeImpl edge = (EdgeImpl) e;
        return (Vertex<V>[]) new Vertex[] { edge.getU(), edge.getV() };
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        EdgeImpl edge = (EdgeImpl) e;
        if (edge.getU() == v)
            return edge.getV();
        if (edge.getV() == v)
            return edge.getU();
        throw new IllegalArgumentException("Vertex not incident to edge.");
    }

    @Override
    public int outDegree(Vertex<V> v) {
        return adjacencyMap.getOrDefault(v, List.of()).size();
    }

    @Override
    public int inDegree(Vertex<V> v) {
        return outDegree(v); // same for undirected graph
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) {
        return adjacencyMap.getOrDefault(v, List.of());
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) {
        return outgoingEdges(v); // same for undirected graph
    }

    // Mutators
    @Override
    public Vertex<V> insertVertex(V element) {
        Vertex<V> v = new VertexImpl(element);
        adjacencyMap.put(v, new ArrayList<>());
        vertexList.add(v);
        return v;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) {
        if (getEdge(u, v) != null)
            throw new IllegalArgumentException("Edge already exists.");
        EdgeImpl edge = new EdgeImpl(u, v, element);
        adjacencyMap.get(u).add(edge);
        adjacencyMap.get(v).add(edge);
        edgeList.add(edge);
        return edge;
    }

    @Override
    public void removeVertex(Vertex<V> v) {
        for (Edge<E> e : new ArrayList<>(adjacencyMap.get(v))) {
            removeEdge(e);
        }
        adjacencyMap.remove(v);
        vertexList.remove(v);
    }

    @Override
    public void removeEdge(Edge<E> e) {
        EdgeImpl edge = (EdgeImpl) e;
        adjacencyMap.get(edge.getU()).remove(e);
        adjacencyMap.get(edge.getV()).remove(e);
        edgeList.remove(e);
    }
}