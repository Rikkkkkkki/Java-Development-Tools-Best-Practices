package BenchmarkingAssignment1.Algorithms;

public class BubbleSort {

    public static void sort(int[] a) {

        int n = a.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {

            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {

                if (a[j] > a[j + 1]) {

                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;

                    swapped = true;
                }
            }

            // early exit if already sorted
            if (!swapped) {
                break;
            }
        }
    }
}