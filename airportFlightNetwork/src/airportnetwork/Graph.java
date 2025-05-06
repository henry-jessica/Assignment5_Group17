package airportnetwork;

public interface Graph<V, E> {
    int numVertices(); // number of vertices

    Iterable<Vertex<V>> vertices(); // iterable collection of vertices

    int numEdges(); // number of edges

    Iterable<Edge<E>> edges(); // iterable collection of edges

    Edge<E> getEdge(Vertex<V> u, Vertex<V> v); // edge between two vertices (if any)

    Vertex<V>[] endVertices(Edge<E> e); // endpoints of edge

    Vertex<V> opposite(Vertex<V> v, Edge<E> e); // opposite vertex on edge

    int outDegree(Vertex<V> v); // number of outgoing edges

    int inDegree(Vertex<V> v); // number of incoming edges

    Iterable<Edge<E>> outgoingEdges(Vertex<V> v); // outgoing edges

    Iterable<Edge<E>> incomingEdges(Vertex<V> v); // incoming edges

    // Update methods
    Vertex<V> insertVertex(V element); // insert new vertex

    Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element); // insert new edge

    void removeVertex(Vertex<V> v); // remove a vertex and incident edges

    void removeEdge(Edge<E> e); // remove an edge

    // Nested interfaces for Vertex and Edge
    interface Vertex<V> {
        V getElement();
    }

    interface Edge<E> {
        E getElement();
    }
}