# Basic Sorting Algorithms in Java and Scala

This repo contains sample implementations of basic sorting algorithms in Java and Scala.
There are also some notes that I use to refresh my knowledge from time to time. Most of the notes
are borrowed from the Internet or books and included here with references. Some Scala implementations
are inspired by the [Scalacaster](https://github.com/vkostyukov/scalacaster) project.

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
- A popular choice for small data sets.
- The main advantage of Insertion Sort is that it's an online algorithm. There is no need
to have all values at the beginning. This could be useful while dealing with a stream of data.

See ``com.aokolnychyi.sorting.InsertionSort`` and ``com.aokolnychyi.sorting.ScalaInsertionSort``.

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

The Scala implementation is inspired by [this](https://dzone.com/articles/how-could-scala-do-merge-sort) post.

See ``com.aokolnychyi.sorting.MergeSort`` and ``com.aokolnychyi.sorting.ScalaMergeSort``.

## Quick Sort

- Utilizes a divide-and-conquer approach.
- O(n * log n) time complexity on average. The worst time complexity is O(n^2) but this is unlikely
in practice if you use a modification of Quick Sort (e.g., randomized selection of the pivot element).
- Requires O (log n) additional space (on average) for recursion. Hence, it is a bit space costly,
especially when it comes to large data sets. The worst case space complexity is O(n).
- Non-stable. See an example [here](https://stackoverflow.com/questions/13498213/quicksort-algorithm-stability).

The Scala implementation is inspired by [this](https://stackoverflow.com/a/2962799/4108401) solution.

See ``com.aokolnychyi.sorting.QuickSort`` and ``com.aokolnychyi.sorting.ScalaQuickSort``. 

## Heap Sort

- O(n log n) time complexity.
- Can consume O(log n) space for recursion. Can be implemented in a tail-recursive manner or using
a loop to avoid the space overhead.
- Not applicable to linked lists as they cannot be converted to a heap.
- Can be a good choice for large data sets as the algorithm does not consume additional space.
- Involves building a heap that takes O(n) time.
- Not stable. Check out an example [here](https://stackoverflow.com/a/26668991/4108401).

See ``com.aokolnychyi.sorting.HeapSort`` and ``com.aokolnychyi.sorting.ScalaHeapSort``. 

## Counting Sort

- A non-comparison sorting algorithm, which can beat O(n log n) time complexity in some cases.
- O(n + k) time complexity, where k is the range of elements.
- Requires O(n + k) additional space.
- Makes sense if the range of elements (k) is O(n). In other words, not big.
- Stable.
- Can be extended to deal with negative numbers by shifting the range.

See ``com.aokolnychyi.sorting.CountingSort`` and ``com.aokolnychyi.sorting.ScalaCountingSort``. 

## Bucket Sort

- Counting Sort is applicable only to integers since elements are used as indices in auxiliary
arrays. Bucket Sort is more generic and can be used to sort a large set of floating point numbers,
which are uniformly distributed.
- As opposed to Counting and Radix Sorts, Bucket Sort heavily depends on the distribution of input data. This
algorithm is efficient only for data that is evenly distributed over an interval. Another critical
point is the number of buckets that can improve or degrade the overall runtime complexity.
- In the average case the time complexity is O(n + k), where n is the length of the input array
and k is the number of buckets.
- In the worst case the time complexity is O(n^2) (when all elements are placed to the same bucket).
- Why Insertion Sort is frequently used to sort buckets is described [here](https://cs.stackexchange.com/a/48827).
- Requires O(n) additional space.
- Can use either hash-based or value-based approaches for assigning elements to buckets.
For example, if the elements are in [0, 1), then you can just multiply them by the number of buckets
(implemented in the Java version). Alternatively, you can split all elements
into ranges by using the min/max values and the number of buckets (implemented in the Scala version).
If you decide to use the hash-based approach, you will need to merge k sorted lists at the end,
which will impact the overall performance.

See ``com.aokolnychyi.sorting.BucketSort`` and ``com.aokolnychyi.sorting.ScalaBucketSort``. 

## Radix Sort

- Counting Sort is a nice algorithm but what if the elements are in range from 1 to n^2? We can’t
use Counting sort as it needs O(n^2) time which is worse than some comparison-based approaches.
Radix Sort can sort such an array in linear time. The main idea is to do digit by digit sort
starting from least significant digit. Radix Sort can use Counting Sort to sort the elements for one
digit.
- The idea is simple: find the maximum element to know the number of digits; apply a modification
of Counting Sort on a digit basis. You can use 1, 10, 100, 1000 to represent which digit to sort against.
For example, ``(array[index] / exp) % 10`` can be used to obtain the needed digit of an element.   
- The time complexity is O(d * (n + b)) time, where b is the base for representing numbers, for example,
for decimal system, b is 10 (this number defines the length of the auxiliary array in Counting Sort).
If k is the maximum possible value, then d would be O(log_b k). In other words, d is the number of digits.
- Stable.
- Radix Sort uses Counting Sort as a subroutine and Counting Sort takes extra space to sort numbers.

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
