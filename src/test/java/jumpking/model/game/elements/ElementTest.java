package jumpking.model.game.elements;

import jumpking.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ElementTest {

    private TestElement element;

    @BeforeEach
    public void setUp() {
        element = new TestElement(100, 200);
    }

    @Test
    public void testGetters() {
        assertEquals(100, element.getX());
        assertEquals(200, element.getY());
    }

    @Test
    public void testElementInitialization() {
        assertEquals(100, element.getPosition().getX());
        assertEquals(200, element.getPosition().getY());
    }

    @Test
    public void testSetPosition() {
        Position newPosition = new Position(150, 250);
        element.setPosition(newPosition);
        assertEquals(newPosition, element.getPosition());
    }
}