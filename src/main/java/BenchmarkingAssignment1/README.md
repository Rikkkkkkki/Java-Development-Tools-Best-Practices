# Assignment 1: Sorting Algorithm Benchmarking

## Overview
Benchmarking and correctness verification of four sorting algorithms using JMH.

## Algorithms
- **Bubble Sort** — with early exit optimisation
- **Quick Sort** — in-place, randomised pivot
- **LSD Radix Sort** — 4 byte-wise passes, base 256, supports negatives
- **Arrays.sort()** — used as reference baseline

## Input Distributions
Each algorithm is tested against:
- Uniform random
- Ascending sorted
- Descending sorted
- Nearly sorted (~1% swaps)

## Benchmark Design
- Bubble Sort is benchmarked separately at 50,000 elements due to O(n²) performance
- All other algorithms are benchmarked at 1,000,000 elements
- JMH is used for all microbenchmarks

## Correctness Verification
All algorithms are verified against `Arrays.sort()` across all four input distributions.
Both sortedness and value-equality are checked via `SortVerifier.assertCorrect()`.

Run tests with:
```bash
mvn test
```

Tests are located in `src/test/java/BenchmarkingAssignment1/SortCorrectnessTest.java`.

## Report
A PDF analytical report with execution times and analysis is included in `src/main/java/BenchmarkingAssignment1/`.