package airportnetwork;

import java.util.*;

public class MiniHeap<T> {
    static class Node<T> {
        T key;
        double priority;

        Node(T key, double priority) {
            this.key = key;
            this.priority = priority;
        }
    }

    private final List<Node<T>> heap = new ArrayList<>();
    private final Map<T, Integer> indexMap = new HashMap<>();

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void insert(T key, double priority) {
        Node<T> node = new Node<>(key, priority);
        heap.add(node);
        int index = heap.size() - 1;
        indexMap.put(key, index);
        siftUp(index);
    }

    public Node<T> extractMin() {
        if (heap.isEmpty()) return null;

        Node<T> min = heap.get(0);
        Node<T> last = heap.remove(heap.size() - 1);
        indexMap.remove(min.key);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            indexMap.put(last.key, 0);
            siftDown(0);
        }

        return min;
    }

    public void decreaseKey(T key, double newPriority) {
        Integer index = indexMap.get(key);
        if (index == null) return;

        Node<T> node = heap.get(index);
        if (newPriority < node.priority) {
            node.priority = newPriority;
            siftUp(index);
        }
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).priority < heap.get(parent).priority) {
                swap(index, parent);
                index = parent;
            } else break;
        }
    }

    private void siftDown(int index) {
        int left, right, smallest;
        int size = heap.size();

        while (true) {
            left = 2 * index + 1;
            right = 2 * index + 2;
            smallest = index;

            if (left < size && heap.get(left).priority < heap.get(smallest).priority)
                smallest = left;

            if (right < size && heap.get(right).priority < heap.get(smallest).priority)
                smallest = right;

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else break;
        }
    }

    private void swap(int i, int j) {
        Node<T> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        indexMap.put(heap.get(i).key, i);
        indexMap.put(heap.get(j).key, j);
    }
}
