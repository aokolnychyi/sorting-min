package com.aokolnychyi.sorting;

import java.util.Arrays;

public class CountingSort {

  public static int[] sort(final int[] inputArray, final int maxElement) {
    final int[] outputArray = new int[inputArray.length];
    final int[] auxiliaryArray = new int[maxElement + 1]; // we need to count 0 as well
    // The call to fill is not needed, since values get the default value for them
    // Arrays.fill(auxiliaryArray, 0);

    // count the number of elements
    for (int element : inputArray) {
      auxiliaryArray[element] += 1;
    }
    // count the number of smaller or equal elements
    for (int index = 1; index <= maxElement; index++) {
      auxiliaryArray[index] = auxiliaryArray[index] + auxiliaryArray[index - 1]; // important
    }

    for (int index = inputArray.length - 1; index >= 0; index--) {
      final int element = inputArray[index];
      final int indexAfterSorting = auxiliaryArray[element] - 1; // important
      outputArray[indexAfterSorting] = element;
      // do not forget to decrement the number of small or equal elements
      auxiliaryArray[element] = auxiliaryArray[element] - 1; // important
    }
    return outputArray;
  }

  public static void main(String[] args) {
    final int[] ints = {2, 5, 3, 0, 2, 3, 0, 3, 1};
    final int[] sortedInts = sort(ints, 5);
    System.out.println(Arrays.toString(sortedInts));
  }
}
