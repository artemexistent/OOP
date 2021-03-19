import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    Matrix matrix = new Matrix(new Box[][]{
            {new Box(1), new Box(2), new Box(3)},
            {new Box(4), new Box(5), new Box(6)},
            {new Box(7), new Box(8), new Box(9)},
            {new Box(10), new Box(11), new Box(12)},

    });
    Matrix a = new Matrix(new Box[][]{
            {new Box(1), new Box(2), new Box(3)},
            {new Box(4), new Box(5), new Box(6)},
            {new Box(7), new Box(8), new Box(9)},
            {new Box(10), new Box(11), new Box(12)},

    });

    @org.junit.jupiter.api.Test
    void maidDiagonal() {
        matrix.maidDiagonal();
        matrix.maidDiagonal();
        assertEquals(a.write(), matrix.write());
    }

    @org.junit.jupiter.api.Test
    void sideDiagonal() {
        matrix.sideDiagonal();
        matrix.sideDiagonal();
        assertEquals(a.write(), matrix.write());
    }

    @org.junit.jupiter.api.Test
    void lineVertical() {
        matrix.lineVertical();
        matrix.lineVertical();
        assertEquals(a.write(), matrix.write());
    }

    @org.junit.jupiter.api.Test
    void lineHorizontal() {
        matrix.lineHorizontal();
        matrix.lineHorizontal();
        assertEquals(a.write(), matrix.write());
    }
}