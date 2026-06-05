package Assignment3.columns.model.kernel;

import Assignment3.columns.model.GameEvent;

public interface Platform {

	void delay(long t);

	long currentTime();

	boolean isKeyPressed();

	void setKeyPressed(boolean isKeyPressed);

	Screen getScreen();

	long getTc();

	void setTc(long time);

	int getKeyPressed();

	GameEvent getEvent();

	RandomGenerator getRandomGenerator();

}