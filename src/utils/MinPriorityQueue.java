package utils;


import java.util.ArrayList;


public class MinPriorityQueue<T extends Comparable<T>> {

//    public static void main(String[] args) {
//        MinPriorityQueue<Integer> test = new MinPriorityQueue<>();
//        test.remove();
//        test.add(3);
//        test.add(2);
//        test.add(22);
//        test.add(7);
//        test.add(5);
//        test.add(26);
//        test.add(21);
//        test.add(12);
//        test.add(6);
//        test.add(1);
//        for (int i = 1; i <= test.size(); i++)
//            System.out.println(test.queue.get(i));
//
//        System.out.println("===================");
//        test.remove();
//        test.remove();
//        for (int i = 1; i <= test.size(); i++)
//            System.out.println(test.queue.get(i));
//    }

  private ArrayList<T> queue;

  /**
   * Creates an empty queue.
   */
  public MinPriorityQueue() {
    queue = new ArrayList<T>();
    queue.add(null);
  }

  /**
   * Returns the number of elements currently in the queue.
   */
  public int size() {
    return queue.size() - 1;
  }

  /**
   * Adds elem to the queue.
   */
  public void add(T elem) {
    queue.add(elem);
    int elemLoc = size();
    int parentLoc = (elemLoc) / 2;

    while (parentLoc >= 1 && elem.compareTo(queue.get(parentLoc)) < 0) {
      T savedParent = queue.get(parentLoc);
      queue.set(parentLoc, elem);
      queue.set(elemLoc, savedParent);
      elemLoc = parentLoc;
      parentLoc = (elemLoc) / 2;
    }
  }

  /**
   * Removes, and returns, the element at the front of the queue.
   */
  public T remove() {

    if (isEmpty())
      return null;

    int topLoc = 1;
    T elem = queue.get(topLoc);
    queue.set(topLoc, queue.get(size()));
    queue.remove(size());

    sort(topLoc);

    return elem;
  }

  private void sort(int startIndex) {

    int leftChild = 2 * startIndex;
    int rightChild = 2 * startIndex + 1;

    if (leftChild > size())
      return;

    int swapIndex = startIndex;

    if (rightChild > size() || queue.get(leftChild).compareTo(queue.get(rightChild)) < 0) {
      swapIndex = leftChild;
    } else {
      swapIndex = rightChild;
    }

    if (!compareSwap(startIndex, swapIndex))
      return;

    sort(swapIndex);
  }

  private boolean compareSwap(int startIndex, int swapIndex) {
    if (queue.get(swapIndex).compareTo(queue.get(startIndex)) < 0) {
      T saved = queue.get(swapIndex);
      queue.set(swapIndex, queue.get(startIndex));
      queue.set(startIndex, saved);
      return true;
    }
    return false;
  }


  /**
   * Returns true if the queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    return (size() == 0);
  }

}
