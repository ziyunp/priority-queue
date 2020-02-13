package utils;

import java.util.ArrayList;

public class MinPriorityQueue<T extends Comparable<T>> {

  private ArrayList<T> queue;
  private final int rootIndex = 1;

  /** Creates an empty queue. */
  public MinPriorityQueue() {
    queue = new ArrayList<T>();
    queue.add(null);
  }

  /** Returns the number of elements currently in the queue. */
  public int size() {
    return queue.size() - 1;
  }

  /** Adds elem to the queue. */
  public void add(T elem) {
    queue.add(elem);
    int elemIndex = size();
    int parentIndex = (elemIndex) / 2;

    while (parentIndex >= 1 && elem.compareTo(queue.get(parentIndex)) < 0) {
      swap(parentIndex, elemIndex);
      elemIndex = parentIndex;
      parentIndex = (elemIndex) / 2;
    }
  }

  /** Removes, and returns, the element at the front of the queue. */
  public T remove() {

    if (isEmpty()) return null;

    T elem = queue.get(rootIndex);
    queue.set(rootIndex, queue.get(size()));
    queue.remove(size());

    sort(rootIndex);

    return elem;
  }

  private void sort(int targetIndex) {

    int leftChild = 2 * targetIndex;
    int rightChild = 2 * targetIndex + 1;

    if (leftChild > size()) return;

    int swapIndex;

    if (rightChild > size() || queue.get(leftChild).compareTo(queue.get(rightChild)) < 0)
      swapIndex = leftChild;
    else swapIndex = rightChild;

    if (queue.get(swapIndex).compareTo(queue.get(targetIndex)) < 0) {
      swap(targetIndex, swapIndex);
    } else {
      return;
    }

    sort(swapIndex);
  }

  private void swap(int index1, int index2) {
    T elem1 = queue.get(index1);
    T elem2 = queue.get(index2);
    queue.set(index1, elem2);
    queue.set(index2, elem1);
  }

  /** Returns true if the queue is empty, false otherwise. */
  public boolean isEmpty() {
    return (size() == 0);
  }
}
