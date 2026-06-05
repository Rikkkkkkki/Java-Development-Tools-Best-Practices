package Assignment3.columns.model;

import Assignment3.columns.model.testdoubles.FakeRandomGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FigureTest {

    private Figure figure(int c1, int c2, int c3) {
        return new Figure(new FakeRandomGenerator(c1 - 1, c2 - 1, c3 - 1));
    }

    @Test
    void moveLeft_decreasesX() {
        Figure f = figure(1, 2, 3);
        int before = f.x;
        f.moveLeft();
        assertEquals(before - 1, f.x);
    }

    @Test
    void moveRight_increasesX() {
        Figure f = figure(1, 2, 3);
        int before = f.x;
        f.moveRight();
        assertEquals(before + 1, f.x);
    }

    @Test
    void moveDown_increasesY() {
        Figure f = figure(1, 2, 3);
        int before = f.y;
        f.moveDown();
        assertEquals(before + 1, f.y);
    }

    @Test
    void rotateUp_shiftsColorsUpward() {
        Figure f = figure(1, 2, 3);
        // colors are c1=1, c2=2, c3=3 after construction
        f.rotateUp();
        assertEquals(2, f.c[1]);
        assertEquals(3, f.c[2]);
        assertEquals(1, f.c[3]);
    }

    @Test
    void rotateDown_shiftsColorsDownward() {
        Figure f = figure(1, 2, 3);
        f.rotateDown();
        assertEquals(3, f.c[1]);
        assertEquals(1, f.c[2]);
        assertEquals(2, f.c[3]);
    }

    @Test
    void rotateUp_thrice_returnsToOriginal() {
        Figure f = figure(1, 2, 3);
        f.rotateUp(); f.rotateUp(); f.rotateUp();
        assertEquals(1, f.c[1]);
        assertEquals(2, f.c[2]);
        assertEquals(3, f.c[3]);
    }

    @Test
    void initialPosition_isCenterColumn() {
        Figure f = figure(1, 2, 3);
        assertEquals(GameConfig.WIDTH / 2 + 1, f.x);
        assertEquals(1, f.y);
    }
}