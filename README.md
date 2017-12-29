# Basic Sorting Algorithms in Java and Scala

This repo contains sample implementations of basic sorting algorithms in Java and Scala.
There are also some notes that I use to refresh my knowledge from time to time. Most of the notes
are borrowed from the Internet or books and included here with references.

## Bubble Sort

- At each iteration you identify the min/max element and bubble it up.
- After ``i`` iterations, the last (or first) ``i`` elements are the biggest (smallest) and ordered.
- O(n^2) time complexity. The best time complexity can be O(n) if the underlying collection/array
is sorted and we keep track of the number of swaps during each pass.
- Can be done in-place (if mutable collections/arrays are used).
- Stable.

See ``com.aokolnychyi.sorting.BubbleSort`` and ``com.aokolnychyi.sorting.ScalaBubbleSort``.
One can argue that the latter implementation resembles more Selection Sort, which is partially true.
However, we still look at pairs of elements and a version of functional Selection Sort is
presented later.

## Selection Sort

- Find the smallest (biggest) element using a linear scan and move it to the front (end) by
swapping. Then, find the second smallest element and repeat the procedure.
Continue doing this until all the elements are in place.
- O(n^2) time on average and in the worst case.
- Can be done in-place (if mutable collections/arrays are used).
- Not stable. Find a nice explanation why ([here](https://stackoverflow.com/a/20761529/4108401)).
- Outperforms Bubble Sort because uses less swaps.

See ``com.aokolnychyi.sorting.SelectionSort`` and ``com.aokolnychyi.sorting.ScalaSelectionSort``.

## Insertion Sort

- After ``i`` iterations, the first ``i`` elements are ordered but not necessarily at their
final positions as in the Bubble Sorting algorithm.
- Insertion Sort is better than Bubble Sort since you do not have to do all ``i`` comparisons at the ``i``th
iteration. In the worst case - yes, but on average you can find a place for an element using
``i/2`` comparisons. This gives a small performance benefit compared to Bubble Sort.
- O(n^2) time complexity in the worst case.
- In-place.
- Stable.
- Performs well on almost sorted data.
- The main advantage of Insertion Sort is that it's an online algorithm. There is no need
to have all values at the beginning. This could be useful while dealing with a stream of data.

## Merge Sort

- Utilizes a divide-and-conquer approach.
- Time complexity: ``T(n) = 2 T(n/2) + O(n)``, O(n log n) overall.
- O(n) (for allocating array/linked list) + O(log n) (for recursion) = O(n) space complexity.
Can be implemented in-place using nodes that represent a linked list. Hence, the space
requirement will be O(log (n) (only for recursion).
- Merge Sort is often the best choice for sorting a linked list as the slow random-access
performance of a linked list makes some other algorithms (such as Quick Sort) perform poorly,
and others (such as Heap Sort) completely impossible.
- Stable.

## Binary Search

This algorithm is included here as it is performs on a sorted array.

- O(log n) time complexity.
- Can cause an overflow if the middle index is computed incorrectly.
- There is a version for finding the next greater element.

See ``com.aokolnychyi.sorting.BinarySearch`` and ``com.aokolnychyi.sorting.ScalaBinarySearch``.

## Other Points

- Java's ``Arrays.sort()`` uses Quick Sort while ``Collections.sort`` uses Merge Sort. Quick Sort
differs in two major points:
    - It is not stable.
    - It does not guarantee O(n log n) performance as it can degrade to O(n^2) on some inputs.

Stability is not a problem for primitive values as we do not have to reason about identity vs
equality. In addition, there are versions of Quick Sort that behave extremely well in practice
and have low probability to degrade to O(n^2) (e.g., Dual Pivot Quick Sort).

Stability is an important aspect when it comes to sorting objects. Suppose you want to sort a
collection of objects by date and then by customer id. You expect that the objects for each sender
are ordered by date. This is possible only with a stable sort. Therefore, ``Collections.sort()``
uses Merge Sort. It is a nice benefit that Merge Sort guarantees O(n log n) performance on any
input. There is also a downside of Merge Sort: it uses O(n) additional space (there are versions
for linked lists, which can perform in place and require just O(log n) space for recursion).
Quick Sort requires only O(log n) space.

- Timsort is a hybrid stable sorting algorithm, derived from Merge Sort and Insertion Sort,
designed to perform well on many kinds of real-world data. The algorithm finds subsequences of the
data that are already ordered, and uses that knowledge to sort the remainder more efficiently.
It is used to sort arrays of non-primitive type in Java SE 7. The algorithm has O(n) time
complexity in the best case and O(n log n) in the worst case ([source](https://en.wikipedia.org/wiki/Timsort)).
