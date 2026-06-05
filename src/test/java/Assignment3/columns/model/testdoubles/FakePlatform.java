package Assignment3.columns.model.testdoubles;

import Assignment3.columns.model.GameEvent;
import Assignment3.columns.model.kernel.Platform;
import Assignment3.columns.model.kernel.RandomGenerator;
import Assignment3.columns.model.kernel.Screen;

public class FakePlatform implements Platform {
    private long time = 0;
    private long tc = 0;
    private boolean keyPressed = false;
    private GameEvent event = GameEvent.NONE;
    private final Screen screen = new FakeScreen();
    private final RandomGenerator random;

    public FakePlatform(RandomGenerator random) {
        this.random = random;
    }

    public void setTime(long time) { this.time = time; }
    public void setEvent(GameEvent event) { this.event = event; }

    @Override public void delay(long t) { /* no-op */ }
    @Override public long currentTime() { return time; }
    @Override public boolean isKeyPressed() { return keyPressed; }
    @Override public void setKeyPressed(boolean v) { this.keyPressed = v; }
    @Override public Screen getScreen() { return screen; }
    @Override public long getTc() { return tc; }
    @Override public void setTc(long time) { this.tc = time; }
    @Override public int getKeyPressed() { return 0; }
    @Override public GameEvent getEvent() { return event; }
    @Override public RandomGenerator getRandomGenerator() { return random; }
}