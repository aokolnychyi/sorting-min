package com.aokolnychyi.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeapSort {

  public static <T extends Comparable<T>> void sort(List<T> elements) {
    if (elements.size() > 0) {
      buildMaxHeap(elements);
      final int numberOfElements = elements.size();
      int heapSize = numberOfElements;
      for (int index = numberOfElements - 1; index >= 1; index--) {
        Collections.swap(elements, 0, index);
        heapSize--;
        maxHeapify(elements, 0, heapSize);
      }
    }
  }

  // takes O(n) time
  private static <T extends Comparable<T>> void buildMaxHeap(List<T> elements) {
    final int numberOfElements = elements.size() - 1;
    for (int index = numberOfElements / 2; index >= 0; index--) {
      maxHeapify(elements, index, elements.size());
    }
  }

  // We assume that trees rooted at left and right are already max heaps,
  // but the element at `index` might be smaller than its children
  private static <T extends Comparable<T>> void maxHeapify(List<T> elements, int index, int heapSize) {
    final int indexOfLeftChild = getIndexOfLeftChild(index);
    final int indexOfRightChild = getIndexOfRightChild(index);
    final T currentElement = elements.get(index);
    int indexOfLargestElement = index;

    if (indexOfLeftChild < heapSize) {
      final T leftChild = elements.get(indexOfLeftChild);
      if (leftChild.compareTo(currentElement) > 0) {
        indexOfLargestElement = indexOfLeftChild;
      }
    }
    final T currentLargestElement = elements.get(indexOfLargestElement);
    if (indexOfRightChild < heapSize) {
      final T rightChild = elements.get(indexOfRightChild);
      if (rightChild.compareTo(currentLargestElement) > 0) {
        indexOfLargestElement = indexOfRightChild;
      }
    }
    if (indexOfLargestElement != index) {
      Collections.swap(elements, index, indexOfLargestElement);
      maxHeapify(elements, indexOfLargestElement, heapSize);
    }
  }

  private static int getIndexOfLeftChild(final int index) {
    return 2 * index + 1;
  }

  private static int getIndexOfRightChild(final int index) {
    return 2 * index + 2;
  }

  public static void main(String[] args) {
    final List<Integer> list = new ArrayList<>();
    list.add(4);
    list.add(1);
    list.add(5);
    list.add(3);
    list.add(9);
    list.add(10);
    list.add(1);
    sort(list);
    System.out.println(list);
    sort(new ArrayList<Integer>());
  }
}
