package airportnetwork;

import java.util.*;
/**
 * A generic MiniHeap implementation.
 * A MiniHeap is a binary tree where the value of each node is less than or equal
 * to the value of its children. It's often used to implement priority queues.
 */

public class MiniHeap<T> {
    /**
     * Represents a node in the MiniHeap.
     * Each node contains a key (of type T) and a priority (a double).
     * The priority is used to determine the order of elements in the heap.
     */
    static class Node<T> {
        T key;
        double priority;
/**
         * Constructs a new Node.
         */
        Node(T key, double priority) {
            this.key = key;
            this.priority = priority;
        }
    }

    private final List<Node<T>> heap = new ArrayList<>(); // The list that stores the heap nodes.
    private final Map<T, Integer> indexMap = new HashMap<>();

    public boolean isEmpty() {
        return heap.isEmpty();
    }
/**
     * Checks if the heap is empty.
     */
    public boolean contains(T key) {
        return indexMap.containsKey(key);
    }

    /**
     * Inserts a new node with the given key and priority into the heap.
     */
    public void insert(T key, double priority) {
        Node<T> node = new Node<>(key, priority);
        heap.add(node);
        int index = heap.size() - 1;
        indexMap.put(key, index);
        siftUp(index);
    }
/**
     * Extracts and removes the node with the minimum priority from the heap.
     */
    public Node<T> extractMin() {
        if (heap.isEmpty())
            return null;

        Node<T> min = heap.get(0);// Get the node with the minimum priority (root of the heap).
        Node<T> last = heap.remove(heap.size() - 1);
        indexMap.remove(min.key);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            indexMap.put(last.key, 0);
            siftDown(0);
        }

        return min; // Return the extracted minimum node.
    
    }
/**
     * Decreases the priority of the node with the given key.
     */
    public void decreaseKey(T key, double newPriority) {
        Integer index = indexMap.get(key);
        if (index == null)
            return;

        Node<T> node = heap.get(index);
        if (newPriority < node.priority) {
            node.priority = newPriority;
            siftUp(index);
        }
    }
/**
     * Restores the heap property by sifting up the node at the given index.
     */
    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).priority < heap.get(parent).priority) {
                swap(index, parent);
                index = parent;
            } else
                break;
        }
    }
    /**
     * Restores the heap property by sifting down the node at the given index.
     */

    private void siftDown(int index) {
        int size = heap.size();
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && heap.get(left).priority < heap.get(smallest).priority)
                smallest = left;
            if (right < size && heap.get(right).priority < heap.get(smallest).priority)
                smallest = right;

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else
                break;
        }
    }
/**
     * Swaps the nodes at the given indices.
     */
    private void swap(int i, int j) {
        Node<T> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        indexMap.put(heap.get(i).key, i);
        indexMap.put(heap.get(j).key, j);
    }
}
