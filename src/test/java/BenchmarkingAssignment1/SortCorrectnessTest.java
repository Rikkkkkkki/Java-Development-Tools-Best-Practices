package BenchmarkingAssignment1;

import BenchmarkingAssignment1.Algorithms.BubbleSort;
import BenchmarkingAssignment1.Algorithms.LSDRadixSort;
import BenchmarkingAssignment1.Algorithms.QuickSort;
import BenchmarkingAssignment1.util.DataGenerator;
import BenchmarkingAssignment1.util.SortVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;


@DisplayName("Sorting Algorithm Correctness Verification")
class SortCorrectnessTest {

       private static final int N = 10_000;

      record Dataset(String name, int[] data) {
        @Override
        public String toString() { return name; }
    }

    /** Provides the four required input distributions as test arguments. */
    static Stream<Dataset> distributions() {
        return Stream.of(
                new Dataset("uniform-random",   DataGenerator.uniformRandom(N)),
                new Dataset("ascending-sorted", DataGenerator.sortedAscending(N)),
                new Dataset("descending-sorted",DataGenerator.reverseSorted(N)),
                new Dataset("nearly-sorted-1%", DataGenerator.nearlySorted(N))
        );
    }

    // Bubble Sort
    @ParameterizedTest(name = "BubbleSort – {0}")
    @MethodSource("distributions")
    @DisplayName("BubbleSort correctness")
    void bubbleSortIsCorrect(Dataset ds) {
        int[] original = Arrays.copyOf(ds.data(), ds.data().length);
        int[] actual   = Arrays.copyOf(ds.data(), ds.data().length);

        BubbleSort.sort(actual);

        // assertCorrect checks BOTH isSorted() AND equality with Arrays.sort()
        SortVerifier.assertCorrect(original, actual);
    }

    // Quick Sort
    @ParameterizedTest(name = "QuickSort – {0}")
    @MethodSource("distributions")
    @DisplayName("QuickSort correctness")
    void quickSortIsCorrect(Dataset ds) {
        int[] original = Arrays.copyOf(ds.data(), ds.data().length);
        int[] actual   = Arrays.copyOf(ds.data(), ds.data().length);

        QuickSort.sort(actual);

        SortVerifier.assertCorrect(original, actual);
    }

    @ParameterizedTest(name = "LSDRadixSort – {0}")
    @MethodSource("distributions")
    @DisplayName("LSDRadixSort correctness")
    void lsdRadixSortIsCorrect(Dataset ds) {
        int[] original = Arrays.copyOf(ds.data(), ds.data().length);
        int[] actual   = Arrays.copyOf(ds.data(), ds.data().length);

        LSDRadixSort.sort(actual);

        SortVerifier.assertCorrect(original, actual);
    }

       @ParameterizedTest(name = "LSDRadixSort with negatives – {0}")
    @MethodSource("distributions")
    @DisplayName("LSDRadixSort correctness – mixed negative/positive values")
    void lsdRadixSortHandlesNegatives(Dataset ds) {
        // Shift every element down so roughly half are negative
        int[] original = Arrays.stream(ds.data())
                .map(v -> v - (N / 2))
                .toArray();
        int[] actual   = Arrays.copyOf(original, original.length);

        LSDRadixSort.sort(actual);

        SortVerifier.assertCorrect(original, actual);
    }
}