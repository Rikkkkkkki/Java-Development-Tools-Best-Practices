package Assignment3.columns.model;

import Assignment3.columns.model.testdoubles.FakeModelListener;
import Assignment3.columns.model.testdoubles.FakeRandomGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;
    private FakeModelListener listener;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initFields();
        listener = new FakeModelListener();
        board.setModelListener(listener);
        board.initBoard();
        board.figure = new Figure(new FakeRandomGenerator(0, 1, 2));
    }

    @Test
    void initBoard_clearsScoreLevelAndCounter() {
        board.Score = 999;
        board.level = 5;
        board.figuresMatchedCounter = 10;
        board.initBoard();
        assertEquals(0, board.Score);
        assertEquals(0, board.level);
        assertEquals(0, board.figuresMatchedCounter);
    }

    @Test
    void initBoard_clearsField() {
        board.newField[1][1] = 3;
        board.initBoard();
        assertEquals(0, board.newField[1][1]);
    }

    @Test
    void pasteFigure_writesFigureColorsIntoField() {
        board.figure.x = 3;
        board.figure.y = 5;
        board.figure.c[1] = 1;
        board.figure.c[2] = 2;
        board.figure.c[3] = 3;
        board.pasteFigure(board.figure);
        assertEquals(1, board.newField[3][5]);
        assertEquals(2, board.newField[3][6]);
        assertEquals(3, board.newField[3][7]);
    }

    @Test
    void canMoveLeft_falseAtLeftEdge() {
        board.figure.x = 1;
        board.figure.y = 1;
        assertFalse(board.canMoveLeft());
    }

    @Test
    void canMoveLeft_trueWhenClearSpace() {
        board.figure.x = 4;
        board.figure.y = 1;
        assertTrue(board.canMoveLeft());
    }

    @Test
    void canMoveRight_falseAtRightEdge() {
        board.figure.x = GameConfig.WIDTH;
        board.figure.y = 1;
        assertFalse(board.canMoveRight());
    }

    @Test
    void canMoveRight_trueWhenClearSpace() {
        board.figure.x = 4;
        board.figure.y = 1;
        assertTrue(board.canMoveRight());
    }

    @Test
    void figureMayMoveDown_falseAtBottom() {
        board.figure.x = 3;
        board.figure.y = GameConfig.DEPTH - 2;
        assertFalse(board.figureMayMoveDown());
    }

    @Test
    void figureMayMoveDown_falseWhenBlockedBelow() {
        board.figure.x = 3;
        board.figure.y = 5;
        board.newField[3][8] = 1; // blocks row y+3
        assertFalse(board.figureMayMoveDown());
    }

    @Test
    void isFieldFull_falseWhenRow3Empty() {
        assertFalse(board.isFieldFull());
    }

    @Test
    void isFieldFull_trueWhenRow3HasBlock() {
        board.newField[2][3] = 1;
        assertTrue(board.isFieldFull());
    }

    @Test
    void dropFigure_movesYToLowestFreePosition() {
        board.figure.x = 3;
        board.figure.y = 1;
        board.dropFigure(board.figure);
        // should land at bottom: y = DEPTH - 2
        assertEquals(GameConfig.DEPTH - 2, board.figure.y);
    }

    @Test
    void findMatches_detectsVerticalTriplet() {
        // place 3 same-color cells vertically
        board.newField[3][10] = 2;
        board.newField[3][11] = 2;
        board.newField[3][12] = 2;
        board.noChanges = true;
        board.findMatches();
        assertFalse(board.noChanges);
    }

    @Test
    void findMatches_detectsHorizontalTriplet() {
        board.newField[2][10] = 3;
        board.newField[3][10] = 3;
        board.newField[4][10] = 3;
        board.noChanges = true;
        board.findMatches();
        assertFalse(board.noChanges);
    }

    @Test
    void findMatches_noMatchWhenDifferentColors() {
        board.newField[3][10] = 1;
        board.newField[3][11] = 2;
        board.newField[3][12] = 3;
        board.noChanges = true;
        board.findMatches();
        assertTrue(board.noChanges);
    }

    @Test
    void findMatches_increaseScore() {
        board.newField[3][10] = 2;
        board.newField[3][11] = 2;
        board.newField[3][12] = 2;
        board.findMatches();
        assertTrue(board.Score > 0);
    }

    @Test
    void collapse_packsRemainingCellsDown() {
        // put a cell in row 5, leave rows 6-15 empty
        board.newField[3][5] = 1;
        board.newField[3][10] = 2;
        board.newField[3][11] = 2;
        board.newField[3][12] = 2;
        board.findMatches(); // clears the triplet from oldField
        board.collapse();
        // the remaining cell (color 1) should have packed to the bottom
        assertEquals(1, board.newField[3][GameConfig.DEPTH]);
    }

    @Test
    void levelChanges_afterThresholdMatches() {
        board.figuresMatchedCounter = GameConfig.NEXT_LEVEL_THRESHOLD - 1;
        // trigger one more match
        board.newField[3][10] = 2;
        board.newField[3][11] = 2;
        board.newField[3][12] = 2;
        board.findMatches();
        board.collapse();
        assertEquals(1, board.level);
        assertEquals(1, listener.lastLevel);
    }

    @Test
    void level_doesNotExceedMaxLevel() {
        board.level = GameConfig.MAX_LEVEL;
        board.figuresMatchedCounter = GameConfig.NEXT_LEVEL_THRESHOLD - 1;
        board.newField[3][10] = 2;
        board.newField[3][11] = 2;
        board.newField[3][12] = 2;
        board.findMatches();
        board.collapse();
        assertEquals(GameConfig.MAX_LEVEL, board.level);
    }

    @Test
    void scoreUpdated_callsListener() {
        board.newField[3][10] = 2;
        board.newField[3][11] = 2;
        board.newField[3][12] = 2;
        board.findMatches();
        board.collapse();
        assertTrue(listener.lastScore > 0);
    }
}