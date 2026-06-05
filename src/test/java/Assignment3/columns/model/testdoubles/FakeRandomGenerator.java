package Assignment3.columns.model.testdoubles;

import Assignment3.columns.model.kernel.RandomGenerator;

public class FakeRandomGenerator implements RandomGenerator {
    private final int[] values;
    private int index = 0;

    public FakeRandomGenerator(int... values) {
        this.values = values;
    }

    @Override
    public int nextInt() {
        return values[index++ % values.length];
    }
}