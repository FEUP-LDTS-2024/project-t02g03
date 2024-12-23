package jumpking.model.game.elements;

import jumpking.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {

    private Block block;

    @BeforeEach
    public void setUp() {
        block = new Block(100, 200);
    }

    @Test
    public void testBlockInitialization() {
        assertEquals(100, block.getPosition().getX());
        assertEquals(200, block.getPosition().getY());
    }

    @Test
    public void testSetPosition() {
        Position newPosition = new Position(150, 250);
        block.setPosition(newPosition);
        assertEquals(newPosition, block.getPosition());
    }
}