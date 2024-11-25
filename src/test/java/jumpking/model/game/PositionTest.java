package jumpking.model.game;

import jumpking.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    private Position position;

    @BeforeEach
    public void setUp() {
        position = new Position(5,10);
    }

    @Test
    public void setXTest() {
        position.setX(10);
        assertEquals(10, position.getX());
    }

    @Test
    public void setYTest() {
        position.setY(5);
        assertEquals(5, position.getY());
    }

    @Test
    public void equalsTest() {
        assertEquals(position, position);

        Position newPosition = new Position(5, 10);
        assertEquals(position, newPosition);

        Position differentPosition = new Position(10, 20);
        assertNotEquals(position, differentPosition);

        assertNotEquals(null, position);

        assertNotEquals(position, "Invalid");
    }
}
