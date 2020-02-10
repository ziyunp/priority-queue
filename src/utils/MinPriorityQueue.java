package utils;

import java.util.ArrayList;

public class MinPriorityQueue<T extends Comparable<T>> {


    private ArrayList<T> queue;
    private int rootIndex = 1;
    /**
     * Creates an empty queue.
     */
    public MinPriorityQueue() {
        queue = new ArrayList<>();
        queue.add(null);
    }

    /**
     *
     * Returns the number of elements currently in the queue.
     */
    public int size() {
        return queue.size() - 1;
    }
    
    /**
     * Adds elem to the queue.
     */
    public void add(T elem) {
        // add to next available space
        queue.add(elem);
        // get parent
        int elemIndex = size();
        int parentIndex = elemIndex / 2;
        T parent = queue.get(parentIndex);
        // while elem < parent
        while (parentIndex >= 1 && elem.compareTo(parent) < 0) {
            // swap with parent
            swap(parentIndex, elemIndex);
            // reassign index and get new parent's index
            elemIndex = parentIndex;
            parentIndex = elemIndex / 2;
            parent = queue.get(parentIndex);
        }
    }

    /**
     * Removes, and returns, the element at the front of the queue.
     */
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T removedElem = queue.get(rootIndex);

        int lastIndex = size();
        T lastElem = queue.get(lastIndex);
        // move the last element to the root
        queue.set(rootIndex, lastElem);
        queue.remove(lastIndex);
        // move lastElem to the right position
        int curIndex = rootIndex;
        while((curIndex * 2) < size()) {
            // get both children
            int leftChildIndex = curIndex * 2;
            int rightChildIndex = (curIndex * 2) + 1;
            T leftChild = queue.get(leftChildIndex);
            T rightChild = queue.get(rightChildIndex);
            // compare with children and swap if child < lastElem
            if (leftChild.compareTo(lastElem) < 0) {
                swap(curIndex, leftChildIndex);
                curIndex = leftChildIndex;
            } else if (rightChild.compareTo(lastElem) < 0) {
                swap(curIndex, rightChildIndex);
                curIndex = rightChildIndex;
            } else {
                return removedElem;
            }
        }
        return removedElem;
    }

    /**
     * Returns true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    private void swap(int index1, int index2) {
        T elem1 = queue.get(index1);
        T elem2 = queue.get(index2);
        queue.set(index1, elem2);
        queue.set(index2, elem1);
    }
}
