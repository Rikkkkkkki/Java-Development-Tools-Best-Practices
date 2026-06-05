package Assignment3.columns.model;

import Assignment3.columns.model.testdoubles.FakePlatform;
import Assignment3.columns.model.testdoubles.FakeRandomGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    private FakePlatform platform;
    private GameController controller;

    @BeforeEach
    void setUp() {
        // colors: c[1]=1, c[2]=2, c[3]=3 for every figure
        platform = new FakePlatform(new FakeRandomGenerator(0, 1, 2));
        controller = new GameController(platform);
        controller.board.initBoard();
        controller.board.figure = new Figure(new FakeRandomGenerator(0, 1, 2));
        controller.board.figure.x = 4;
        controller.board.figure.y = 5;
    }

    @Test
    void left_movesFigureLeft_whenSpaceAvailable() {
        int before = controller.board.figure.x;
        controller.processEvent(GameEvent.LEFT);
        assertEquals(before - 1, controller.board.figure.x);
    }

    @Test
    void left_doesNotMoveFigure_atLeftEdge() {
        controller.board.figure.x = 1;
        controller.processEvent(GameEvent.LEFT);
        assertEquals(1, controller.board.figure.x);
    }

    @Test
    void right_movesFigureRight_whenSpaceAvailable() {
        int before = controller.board.figure.x;
        controller.processEvent(GameEvent.RIGHT);
        assertEquals(before + 1, controller.board.figure.x);
    }

    @Test
    void right_doesNotMoveFigure_atRightEdge() {
        controller.board.figure.x = GameConfig.WIDTH;
        controller.processEvent(GameEvent.RIGHT);
        assertEquals(GameConfig.WIDTH, controller.board.figure.x);
    }

    @Test
    void up_rotatesFigureUpward() {
        controller.board.figure.c[1] = 1;
        controller.board.figure.c[2] = 2;
        controller.board.figure.c[3] = 3;
        controller.processEvent(GameEvent.UP);
        assertEquals(2, controller.board.figure.c[1]);
        assertEquals(3, controller.board.figure.c[2]);
        assertEquals(1, controller.board.figure.c[3]);
    }

    @Test
    void down_rotatesFigureDownward() {
        controller.board.figure.c[1] = 1;
        controller.board.figure.c[2] = 2;
        controller.board.figure.c[3] = 3;
        controller.processEvent(GameEvent.DOWN);
        assertEquals(3, controller.board.figure.c[1]);
        assertEquals(1, controller.board.figure.c[2]);
        assertEquals(2, controller.board.figure.c[3]);
    }

    @Test
    void drop_movesFigureToBottom() {
        controller.processEvent(GameEvent.DROP);
        assertEquals(GameConfig.DEPTH - 2, controller.board.figure.y);
    }

    @Test
    void drop_resetsTc() {
        platform.setTime(9999);
        controller.processEvent(GameEvent.DROP);
        assertEquals(0, platform.getTc());
    }

    @Test
    void levelUp_increasesLevel() {
        controller.board.level = 3;
        controller.processEvent(GameEvent.LEVEL_UP);
        assertEquals(4, controller.board.level);
    }

    @Test
    void levelUp_doesNotExceedMaxLevel() {
        controller.board.level = GameConfig.MAX_LEVEL;
        controller.processEvent(GameEvent.LEVEL_UP);
        assertEquals(GameConfig.MAX_LEVEL, controller.board.level);
    }

    @Test
    void levelDown_decreasesLevel() {
        controller.board.level = 3;
        controller.processEvent(GameEvent.LEVEL_DOWN);
        assertEquals(2, controller.board.level);
    }

    @Test
    void levelDown_doesNotGoBelowZero() {
        controller.board.level = 0;
        controller.processEvent(GameEvent.LEVEL_DOWN);
        assertEquals(0, controller.board.level);
    }

    @Test
    void levelChange_resetsMatchCounter() {
        controller.board.figuresMatchedCounter = 15;
        controller.processEvent(GameEvent.LEVEL_UP);
        assertEquals(0, controller.board.figuresMatchedCounter);
    }
}