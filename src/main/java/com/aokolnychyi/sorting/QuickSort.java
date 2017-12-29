package com.aokolnychyi.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

  public static <T extends Comparable<T>> void sort(List<T> list) {
    sort(list, 0, list.size() - 1);
  }

  private static <T extends Comparable<T>> void sort(List<T> list, int startIndex, int endIndex) {
    if (startIndex < endIndex) {
      final int partitionIndex = partition(list, startIndex, endIndex);
      sort(list, startIndex, partitionIndex - 1);
      sort(list, partitionIndex + 1, endIndex);
    }
  }

  private static <T extends Comparable<T>> int partition(List<T> list, int startIndex, int endIndex) {

    // this step is optional and is used to improve the time complexity for already sorted lists
    // just pick up a random element to split against
    final int randomPivotIndex = ThreadLocalRandom.current().nextInt(startIndex, endIndex + 1);
    Collections.swap(list, randomPivotIndex, endIndex);

    final T pivotal = list.get(endIndex);
    int notGreaterBoundIndex = startIndex - 1;
    for (int currentIndex = startIndex; currentIndex < endIndex; currentIndex++) {
      final T currentElement = list.get(currentIndex);
      if (currentElement.compareTo(pivotal) <= 0) {
        notGreaterBoundIndex++;
        if (notGreaterBoundIndex != currentIndex) {
          Collections.swap(list, notGreaterBoundIndex, currentIndex); // important
        }
      }
    }

    notGreaterBoundIndex++; // important
    Collections.swap(list, notGreaterBoundIndex, endIndex);

    return notGreaterBoundIndex;
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
    sort(new ArrayList<Integer>());
    System.out.println(list);
  }
}
