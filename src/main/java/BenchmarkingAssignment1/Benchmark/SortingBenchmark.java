package BenchmarkingAssignment1.Benchmark;

import BenchmarkingAssignment1.Algorithms.QuickSort;
import BenchmarkingAssignment1.Algorithms.LSDRadixSort;
import BenchmarkingAssignment1.util.DataGenerator;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

// Fast algorithms (No BubbleSort) on N = 1,000,000

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
public class SortingBenchmark {

    private static final int N = 1_000_000;

    private int[] random;
    private int[] sorted;
    private int[] reverse;
    private int[] nearly;

    @Setup(Level.Trial)
    public void setup() {
        random  = DataGenerator.uniformRandom(N);
        sorted  = DataGenerator.sortedAscending(N);
        reverse = DataGenerator.reverseSorted(N);
        nearly  = DataGenerator.nearlySorted(N);
    }

    // Quick Sort
    @Benchmark public void quickSort_random()  { int[] a = Arrays.copyOf(random,  N); QuickSort.sort(a); }
    @Benchmark public void quickSort_sorted()  { int[] a = Arrays.copyOf(sorted,  N); QuickSort.sort(a); }
    @Benchmark public void quickSort_reverse() { int[] a = Arrays.copyOf(reverse, N); QuickSort.sort(a); }
    @Benchmark public void quickSort_nearly()  { int[] a = Arrays.copyOf(nearly,  N); QuickSort.sort(a); }

    // LSD Radix Sort
    @Benchmark public void radixSort_random()  { int[] a = Arrays.copyOf(random,  N); LSDRadixSort.sort(a); }
    @Benchmark public void radixSort_sorted()  { int[] a = Arrays.copyOf(sorted,  N); LSDRadixSort.sort(a); }
    @Benchmark public void radixSort_reverse() { int[] a = Arrays.copyOf(reverse, N); LSDRadixSort.sort(a); }
    @Benchmark public void radixSort_nearly()  { int[] a = Arrays.copyOf(nearly,  N); LSDRadixSort.sort(a); }

    // Arrays.sort
    @Benchmark public void javaSort_random()   { int[] a = Arrays.copyOf(random,  N); Arrays.sort(a); }
    @Benchmark public void javaSort_sorted()   { int[] a = Arrays.copyOf(sorted,  N); Arrays.sort(a); }
    @Benchmark public void javaSort_reverse()  { int[] a = Arrays.copyOf(reverse, N); Arrays.sort(a); }
    @Benchmark public void javaSort_nearly()   { int[] a = Arrays.copyOf(nearly,  N); Arrays.sort(a); }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(new String[]{"BenchmarkingAssignment1.Benchmark.SortingBenchmark"});    }
}
