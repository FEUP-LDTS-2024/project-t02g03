package jumpking.model.game.elements.king;

import jumpking.model.Position;
import jumpking.model.game.elements.King;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KingTest {

    @Test
    public void testKingInitialization() {
        King king = new King(100, 200);
        assertEquals(100, king.getPosition().getX());
        assertEquals(200, king.getPosition().getY());
        assertEquals(114, king.getBottomRight().getX());
        assertEquals(200, king.getBottomRight().getY());
        assertEquals(100, king.getTopLeft().getX());
        assertEquals(185, king.getTopLeft().getY());
        assertEquals(114, king.getTopRight().getX());
        assertEquals(185, king.getTopRight().getY());
    }

    @Test
    public void testSetPosition() {
        King king = new King(100, 200);
        Position newPosition = new Position(150, 250);
        king.setPosition(newPosition);
        assertEquals(newPosition, king.getPosition());
        assertEquals(164, king.getBottomRight().getX());
        assertEquals(250, king.getBottomRight().getY());
        assertEquals(150, king.getTopLeft().getX());
        assertEquals(235, king.getTopLeft().getY());
        assertEquals(164, king.getTopRight().getX());
        assertEquals(235, king.getTopRight().getY());
    }

//    @Test
//    public void testSetIsJumping() {
//        King king = new King(100, 200);
//        king.setIsJumping(true);
//        assertTrue(king.isJumping());
//        king.setIsJumping(false);
//        assertFalse(king.isJumping());
//    }

    @Test
    public void testMoveMethods() {
        King king = new King(100, 200);
        assertEquals(new Position(100, 199), king.moveUp());
        assertEquals(new Position(100, 201), king.moveDown());
        assertEquals(new Position(99, 200), king.moveLeft());
        assertEquals(new Position(101, 200), king.moveRight());
    }
}