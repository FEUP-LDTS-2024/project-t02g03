package jumpking.model;

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
    public void setPositionTest() {
        position.setPosition(15, 20);
        assertEquals(15, position.getX());
        assertEquals(20, position.getY());
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

    @Test
    public void hashCodeTest() {
        Position samePosition = new Position(5, 10);
        assertEquals(position.hashCode(), samePosition.hashCode());

        Position differentPosition = new Position(10, 20);
        assertNotEquals(position.hashCode(), differentPosition.hashCode());
    }
}
