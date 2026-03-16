package BenchmarkingAssignment1.Algorithms;

public class LSDRadixSort {

    public static void sort(int[] a) {

        int[] output = new int[a.length];

        for (int shift = 0; shift < 32; shift += 8) {

            int[] count = new int[256];

            // counting occurrences
            for (int value : a) {
                int digit = (value >>> shift) & 0xFF;
                count[digit]++;
            }

            // prefix sums
            for (int i = 1; i < 256; i++) {
                count[i] += count[i - 1];
            }

            // build output array
            for (int i = a.length - 1; i >= 0; i--) {

                int digit = (a[i] >>> shift) & 0xFF;

                output[--count[digit]] = a[i];
            }

            // copy back
            System.arraycopy(output, 0, a, 0, a.length);
        }

        // handle negative numbers
        int[] temp = new int[a.length];
        int index = 0;

        for (int value : a)
            if (value < 0)
                temp[index++] = value;

        for (int value : a)
            if (value >= 0)
                temp[index++] = value;

        System.arraycopy(temp, 0, a, 0, a.length);

    }
}
