package jumpking.model.game.elements;

import jumpking.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrincessTest {

    private Princess princess;

    @BeforeEach
    public void setUp() {
        princess = new Princess(100, 200);
    }

    @Test
    public void testPrincessInitialization() {
        assertEquals(100, princess.getPosition().getX());
        assertEquals(200, princess.getPosition().getY());
        assertEquals(115, princess.getBottomRight().getX());
        assertEquals(200, princess.getBottomRight().getY());
        assertEquals(100, princess.getTopLeft().getX());
        assertEquals(181, princess.getTopLeft().getY());
    }

    @Test
    public void testSetPosition() {
        Position newPosition = new Position(150, 250);
        princess.setPosition(newPosition);
        assertEquals(newPosition, princess.getPosition());
        assertEquals(165, princess.getBottomRight().getX());
        assertEquals(250, princess.getBottomRight().getY());
        assertEquals(150, princess.getTopLeft().getX());
        assertEquals(231, princess.getTopLeft().getY());
    }
}