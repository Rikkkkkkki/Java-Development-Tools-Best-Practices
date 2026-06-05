package Assignment3.columns.model.testdoubles;

import Assignment3.columns.model.kernel.ModelListener;
import java.util.ArrayList;
import java.util.List;

public class FakeModelListener implements ModelListener {
    public int lastLevel = -1;
    public long lastScore = -1;
    public int fieldUpdateCount = 0;
    public List<int[]> triplets = new ArrayList<>();

    @Override
    public void levelHasChanged(int level) { this.lastLevel = level; }

    @Override
    public void tripletDetected(int a, int b, int c, int d, int i, int j) {
        triplets.add(new int[]{a, b, c, d, i, j});
    }

    @Override
    public void fieldWasUpdated(int[][] newField) { fieldUpdateCount++; }

    @Override
    public void scoreUpdated(long score) { this.lastScore = score; }
}