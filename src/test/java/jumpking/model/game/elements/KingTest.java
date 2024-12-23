package jumpking.model.game.elements;

import jumpking.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class KingTest {

    private King king;

    @BeforeEach
    public void setUp() {
        king = new King(100, 200);
    }

    @Test
    public void testKingInitialization() {
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

    @Test
    public void testGetters() {
        king.setStartTime(0);
        assertEquals(14, king.getWidth());
        assertEquals(15, king.getHeight());
        assertEquals(0, king.getJumps());
        assertEquals(0, king.getStartTime());
        assertEquals(King.PlayerState.IDLE, king.getState());
    }

    @Test
    public void testSetters() {
        king.setJumps(10);
        assertEquals(10, king.getJumps());
        king.setStartTime(1000);
        assertEquals(1000, king.getStartTime());
        king.setState(King.PlayerState.RUNNING);
        assertEquals(King.PlayerState.RUNNING, king.getState());
    }

    @Test
    public void testMoveMethods() {
        assertEquals(new Position(100, 199), king.moveUp());
        assertEquals(new Position(100, 201), king.moveDown());
        assertEquals(new Position(99, 200), king.moveLeft());
        assertEquals(new Position(101, 200), king.moveRight());
    }

    @Test
    public void testIncreaseJumps() {
        assertEquals(0, king.getJumps());
        king.increaseJumps();
        assertEquals(1, king.getJumps());
    }

    @Test
    public void testProjectileMotion() {
        List<Position> positions = king.projectileMotion(100, 1, 500);
        assertNotNull(positions);
        assertFalse(positions.isEmpty());
    }

    @Test
    public void testUpdateJumpPositions() {
        king.getJumpPositions().add(new Position(100, 200));
        king.getJumpPositions().add(new Position(100, 201));
        king.updateJumpPositions(10);
        Queue<Position> updatedPositions = king.getJumpPositions();
        assertEquals(2, updatedPositions.size());
        assertEquals(new Position(100, 210), updatedPositions.poll());
        assertEquals(new Position(100, 211), updatedPositions.poll());
    }

    @Test
    public void testSetFacingRight() {
        king.setFacingRight(false);
        assertFalse(king.isFacingRight());
        king.setFacingRight(true);
        assertTrue(king.isFacingRight());
    }

    @Test
    public void testGetJumpPositions() {
        Queue<Position> jumpPositions = king.getJumpPositions();
        assertNotNull(jumpPositions);
        assertTrue(jumpPositions.isEmpty());
        jumpPositions.add(new Position(100, 200));
        assertEquals(1, jumpPositions.size());
        assertEquals(new Position(100, 200), jumpPositions.peek());
    }

    @Test
    public void testProjectileMotionStraightUp() {
        List<Position> positions = king.projectileMotion(100, 0, 500);
        assertNotNull(positions);
        assertFalse(positions.isEmpty());
        for (Position pos : positions) {
            assertEquals(king.getPosition().getX(), pos.getX());
        }
    }

    @Test
    public void testProjectileMotionRight() {
        List<Position> positions = king.projectileMotion(100, 1, 500);
        assertNotNull(positions);
        assertFalse(positions.isEmpty());
        for (Position pos : positions) {
            assertTrue(pos.getX() >= king.getPosition().getX());
        }
    }

    @Test
    public void testProjectileMotionLeft() {
        List<Position> positions = king.projectileMotion(100, -1, 500);
        assertNotNull(positions);
        assertFalse(positions.isEmpty());
        for (Position pos : positions) {
            assertTrue(pos.getX() <= king.getPosition().getX());
        }
    }

    @Test
    public void testUpdateJumpPositionsWithNegativeOffset() {
        king.getJumpPositions().add(new Position(100, 200));
        king.getJumpPositions().add(new Position(100, 201));
        king.updateJumpPositions(-10);
        Queue<Position> updatedPositions = king.getJumpPositions();
        assertEquals(2, updatedPositions.size());
        assertEquals(new Position(100, 190), updatedPositions.poll());
        assertEquals(new Position(100, 191), updatedPositions.poll());
    }

    @Test
    public void testSetPositionWithNegativeCoordinates() {
        Position newPosition = new Position(-50, -50);
        king.setPosition(newPosition);
        assertEquals(newPosition, king.getPosition());
        assertEquals(-36, king.getBottomRight().getX());
        assertEquals(-50, king.getBottomRight().getY());
        assertEquals(-50, king.getTopLeft().getX());
        assertEquals(-65, king.getTopLeft().getY());
        assertEquals(-36, king.getTopRight().getX());
        assertEquals(-65, king.getTopRight().getY());
    }

    @Test
    public void testProjectileMotionWithZeroHeight() {
        List<Position> positions = king.projectileMotion(0, 1, 500);
        assertNotNull(positions);
        assertFalse(positions.isEmpty());
        for (Position pos : positions) {
            assertTrue(pos.getX() >= king.getPosition().getX());
            assertEquals(king.getPosition().getY(), pos.getY());
        }
    }

    @Test
    public void testProjectileMotionWithNegativeHeight() {
        List<Position> positions = king.projectileMotion(-100, 1, 500);
        assertNotNull(positions);
        assertFalse(positions.isEmpty());
        for (Position pos : positions) {
            assertTrue(pos.getX() >= king.getPosition().getX());
            assertTrue(pos.getY() >= king.getPosition().getY());
        }
    }

    @Test
    public void testProjectileMotionWithZeroMaxX() {
        List<Position> positions = king.projectileMotion(100, 1, 0);
        assertNotNull(positions);
        assertFalse(positions.isEmpty());
        for (Position pos : positions) {
            assertEquals(king.getPosition().getX(), pos.getX());
            assertTrue(pos.getY() <= king.getPosition().getY());
        }
    }
}