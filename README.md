# Course Assignments - Java Development Tools Best Practices
## Tatia Tkeshelashvili

This repository contains my assignments for the course.

## Assignment 1: Sorting Algorithm Benchmarking

- Implemented and benchmarked **Bubble Sort**, **Quick Sort**, **LSD Radix Sort**, and **Arrays.sort()**.
- Benchmarks performed on arrays of size 50,000 and 1,000,000 (Bubble Sort excluded for 1M due to performance).
- JMH used for microbenchmarking.
- Report included with execution times and analysis.

## Assignment 2: Refactoring Code Smells

- Identified and refactored **20 distinct code smells** across a Java package (e.g., Large Class, Primitive Obsession, and Divergent Change).
- Applied **Fowler-style refactorings** such as Extract Class, Move Method, and Replace Loop with Pipeline (Java 8 Streams).
- Focused on improving **cohesion, coupling, and encapsulation** while strictly preserving original observable behavior.
- Included detailed design notes for each refactoring to justify architectural improvements.