# Basic Sorting Algorithms in Java and Scala

This repo contains sample implementations of basic sorting algorithms in Java and Scala.
There are also some notes that I use to refresh my knowledge from time to time. Most of the notes
are borrowed from the Internet or books and included here with references.

## Binary Search

- Performs on a sorted array.
- O(log n) time complexity.
- Can cause an overflow if the middle index is computed incorrectly.
- There is a version for finding the next greater element.

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
