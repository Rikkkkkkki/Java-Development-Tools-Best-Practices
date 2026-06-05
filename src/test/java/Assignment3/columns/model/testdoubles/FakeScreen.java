package Assignment3.columns.model.testdoubles;

import Assignment3.columns.model.kernel.Screen;

public class FakeScreen implements Screen {
    @Override public void setColor(int color) {}
    @Override public void fillRect(int x, int y, int w, int h) {}
    @Override public void drawRect(int x, int y, int w, int h) {}
    @Override public void drawString(String s, int x, int y) {}
    @Override public void clearRect(int x, int y, int w, int h) {}
    @Override public int Black() { return 0; }
    @Override public int White() { return 8; }
}