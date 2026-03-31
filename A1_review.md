# Summary

This repository contains a strong Assignment 1 submission with real `JMH` benchmarks, all required algorithm implementations, a documented split benchmark design, and a PDF report.

The main remaining problem is not benchmarking structure or algorithm coverage. It is evidence. The repository defines a correctness helper, but it does not visibly exercise correctness verification in tracked tests or a tracked verification runner.

# Strengths

- Real `JMH` benchmark code is present in [`src/main/java/BenchmarkingAssignment1/Benchmark/SortingBenchmark.java`](src/main/java/BenchmarkingAssignment1/Benchmark/SortingBenchmark.java) and [`src/main/java/BenchmarkingAssignment1/Benchmark/BubbleSortBenchmark.java`](src/main/java/BenchmarkingAssignment1/Benchmark/BubbleSortBenchmark.java).
- The benchmark design follows the current Assignment 1 practical clarification by separating Bubble Sort from the `1,000,000`-element scalable benchmark in [`src/main/java/BenchmarkingAssignment1/Benchmark/BubbleSortBenchmark.java`](src/main/java/BenchmarkingAssignment1/Benchmark/BubbleSortBenchmark.java) and [`src/main/java/BenchmarkingAssignment1/Benchmark/SortingBenchmark.java`](src/main/java/BenchmarkingAssignment1/Benchmark/SortingBenchmark.java).
- Bubble Sort includes early exit in [`src/main/java/BenchmarkingAssignment1/Algorithms/BubbleSort.java`](src/main/java/BenchmarkingAssignment1/Algorithms/BubbleSort.java).
- Quick Sort is in-place and uses a randomized pivot strategy in [`src/main/java/BenchmarkingAssignment1/Algorithms/QuickSort.java`](src/main/java/BenchmarkingAssignment1/Algorithms/QuickSort.java).
- LSD Radix Sort is implemented with `4` byte-wise passes in [`src/main/java/BenchmarkingAssignment1/Algorithms/LSDRadixSort.java`](src/main/java/BenchmarkingAssignment1/Algorithms/LSDRadixSort.java).
- Required input distributions are implemented in [`src/main/java/BenchmarkingAssignment1/util/DataGenerator.java`](src/main/java/BenchmarkingAssignment1/util/DataGenerator.java).
- A PDF analytical report is included in [`src/main/java/BenchmarkingAssignment1/Sorting Algorithm Benchmark Report.pdf`](src/main/java/BenchmarkingAssignment1/Sorting Algorithm Benchmark Report.pdf).

# Findings

- Major: Correctness verification is not evidenced in a tracked execution path.
  Evidence: [`src/main/java/BenchmarkingAssignment1/util/SortVerifier.java`](src/main/java/BenchmarkingAssignment1/util/SortVerifier.java) defines both required checks, but no tracked file invokes `SortVerifier.assertCorrect(...)` or `SortVerifier.isSorted(...)`. There is also no test source tree in the repository. Under the current Assignment 1 workflow, helper code alone is not enough when actual verification execution is not visible.

# Requirement Checklist

- Java source code present: Pass
- JMH benchmark classes present: Pass
- PDF analytical report present: Pass
- Bubble Sort with early exit: Pass
- in-place Quick Sort: Pass
- Quick Sort pivot strategy documented: Pass
- LSD Radix Sort implemented: Pass
- Radix Sort uses 4 passes / base 256: Pass
- Radix Sort supports negative numbers: Pass
- `Arrays.sort(int[])` benchmark present: Pass
- `Arrays.sort(int[])` used as correctness reference: Fail
- uniform random dataset: Pass
- ascending sorted dataset: Pass
- descending sorted dataset: Pass
- nearly sorted dataset with about `1%` swaps: Pass
- arrays of `1,000,000` integers where required: Pass
- separate benchmark method per algorithm: Pass
- warmup iterations within required range: Pass
- measurement iterations within required range: Pass
- correctness check against `Arrays.sort()`: Fail
- sortedness verification: Fail

# Verdict

`Partially meets requirements`

Reasoning: the repository is strong on benchmark structure, algorithm coverage, dataset generation, and report quality. But the assignment explicitly requires correctness verification against `Arrays.sort()` plus sortedness checking, and this repository does not provide visible repository evidence that those checks are actually run.
