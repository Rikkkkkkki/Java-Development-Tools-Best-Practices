package BenchmarkingAssignment1.util;

import java.util.Random;

public class DataGenerator {

    private static final Random rand = new Random();

    // Uniform random integers
    public static int[] uniformRandom(int n) {

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = rand.nextInt();
        }

        return a;
    }

    // Already sorted ascending
    public static int[] sortedAscending(int n) {

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = i;
        }

        return a;
    }

    // Reverse sorted descending
    public static int[] reverseSorted(int n) {

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = n - i;
        }

        return a;
    }

    // Nearly sorted (1% swaps)
    public static int[] nearlySorted(int n) {

        int[] a = sortedAscending(n);

        int swaps = n / 100; // 1%

        for (int i = 0; i < swaps; i++) {

            int x = rand.nextInt(n);
            int y = rand.nextInt(n);

            int temp = a[x];
            a[x] = a[y];
            a[y] = temp;
        }

        return a;
    }
}