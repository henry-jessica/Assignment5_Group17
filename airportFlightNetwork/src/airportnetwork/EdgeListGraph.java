package airportnetwork;

import java.util.ArrayList;
import java.util.List;

public class EdgeListGraph<V, E> implements Graph<V, E> {

    private final List<Vertex<V>> vertices = new ArrayList<>();
    private final List<Edge<E>> edges = new ArrayList<>();

    private class VertexImpl implements Vertex<V> {
        private final V element;

        public VertexImpl(V element) {
            this.element = element;
        }

        @Override
        public V getElement() {
            return element;
        }
    }

    private class EdgeImpl implements Edge<E> {
        private final E element;
        private final Vertex<V> u;
        private final Vertex<V> v;

        public EdgeImpl(Vertex<V> u, Vertex<V> v, E element) {
            this.u = u;
            this.v = v;
            this.element = element;
        }

        @Override
        public E getElement() {
            return element;
        }
    }

    // Accessors
    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertices;
    }

    @Override
    public int numEdges() {
        return edges.size();
    }

    @Override
    public Iterable<Edge<E>> edges() {
        return edges;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
        for (Edge<E> e : edges) {
            EdgeImpl edge = (EdgeImpl) e;
            if ((edge.u == u && edge.v == v) || (edge.u == v && edge.v == u)) {
                return e;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Vertex<V>[] endVertices(Edge<E> e) {
        EdgeImpl edge = (EdgeImpl) e;
        return (Vertex<V>[]) new Vertex[] { edge.u, edge.v };
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        EdgeImpl edge = (EdgeImpl) e;
        if (edge.u == v)
            return edge.v;
        if (edge.v == v)
            return edge.u;
        throw new IllegalArgumentException("Vertex is not incident to this edge.");
    }

    @Override
    public int outDegree(Vertex<V> v) {
        int count = 0;
        for (Edge<E> e : edges) {
            EdgeImpl edge = (EdgeImpl) e;
            if (edge.u == v || edge.v == v)
                count++;
        }
        return count;
    }

    @Override
    public int inDegree(Vertex<V> v) {
        return outDegree(v); // Same for undirected graph
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) {
        List<Edge<E>> result = new ArrayList<>();
        for (Edge<E> e : edges) {
            EdgeImpl edge = (EdgeImpl) e;
            if (edge.u == v || edge.v == v)
                result.add(e);
        }
        return result;
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) {
        return outgoingEdges(v); // Same for undirected graph
    }

    // Updates
    @Override
    public Vertex<V> insertVertex(V element) {
        Vertex<V> v = new VertexImpl(element);
        vertices.add(v);
        return v;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) {
        if (getEdge(u, v) != null)
            throw new IllegalArgumentException("Edge already exists.");
        Edge<E> e = new EdgeImpl(u, v, element);
        edges.add(e);
        return e;
    }

    @Override
    public void removeVertex(Vertex<V> v) {
        edges.removeIf(e -> ((EdgeImpl) e).u == v || ((EdgeImpl) e).v == v);
        vertices.remove(v);
    }

    @Override
    public void removeEdge(Edge<E> e) {
        edges.remove(e);
    }
}