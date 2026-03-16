package BenchmarkingAssignment1.Algorithms;

import java.util.Random;

public class QuickSort {

    private static final Random rng = new Random(42);

    public static void sort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(a, low, high);
            quickSort(a, low, pivotIndex - 1);
            quickSort(a, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] a, int low, int high) {
        // random pivot — prevents O(n^2) and stack overflow on sorted input
        int pivotIdx = low + rng.nextInt(high - low + 1);
        int tmp = a[pivotIdx]; a[pivotIdx] = a[high]; a[high] = tmp;

        int pivot = a[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (a[j] <= pivot) {
                i++;
                int t = a[i]; a[i] = a[j]; a[j] = t;
            }
        }
        int t = a[i + 1]; a[i + 1] = a[high]; a[high] = t;
        return i + 1;
    }
}