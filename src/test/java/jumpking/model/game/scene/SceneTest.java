package jumpking.model.game.scene;

import jumpking.Application;
import jumpking.model.Position;
import jumpking.model.game.elements.Block;
import jumpking.model.game.elements.King;
import jumpking.model.game.elements.Princess;
import jumpking.states.GameState;
import jumpking.view.SpriteLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SceneTest {

    private Scene scene;
    private King king;
    private Application app;
    private SpriteLoader spriteLoader;
    private SceneBuilder sceneBuilder;

    @BeforeEach
    public void setUp() {
        king = new King(100, 200);
        scene = new Scene(1);
        scene.setKing(king);
        app = Mockito.mock(Application.class);
        spriteLoader = Mockito.mock(SpriteLoader.class);
        when(app.getSpriteLoader()).thenReturn(spriteLoader);
        sceneBuilder = mock(SceneBuilder.class);
    }

    @Test
    public void testSceneInitialization() {
        assertNotNull(scene);
        assertEquals(king, scene.getKing());
    }

    @Test
    public void testGetSceneCode() {
        assertEquals(1, scene.getSceneCode());
    }

    @Test
    public void testSetKing() {
        King newKing = new King(150, 250);
        scene.setKing(newKing);
        assertEquals(newKing, scene.getKing());
    }

    @Test
    public void testSetStartingPosition() {
        Position newPosition = new Position(150, 250);
        scene.setStartingPosition(newPosition);
        assertEquals(newPosition, king.getPosition());
    }

    @Test
    public void testSetBlocks() {
        Block[] blocks = {new Block(0, 0), new Block(1, 1)};
        scene.setBlocks(blocks);
        assertArrayEquals(blocks, scene.getBlocks());
    }

    @Test
    public void testSetPrincess() {
        Princess princess = new Princess(115, 33);
        scene.setPrincess(princess);
        assertEquals(princess, scene.getPrincess());
    }

    @Test
    public void testCanKingMove() {
        Block[] blocks = {new Block(0, 0), new Block(1, 1)};
        scene.setBlocks(blocks);
        Position position = new Position(2, 2);
        assertTrue(scene.canKingMove(position));
    }

    @Test
    public void testCanKingMoveOutOfBounds() {
        Position position = new Position(333, 2);
        assertFalse(scene.canKingMove(position));
    }

    @Test
    public void testCanKingMoveCollision() {
        Block[] blocks = {new Block(2, 2)};
        scene.setBlocks(blocks);
        Position position = new Position(2, 2);
        assertFalse(scene.canKingMove(position));
    }

    @Test
    public void testIsKingFalling() {
        Block[] blocks = {new Block(0, 0), new Block(1, 1)};
        scene.setBlocks(blocks);
        assertTrue(scene.isKingFalling());
    }

    @Test
    public void testIsKingNotFalling() {
        Block[] blocks = {new Block(100, 201), new Block(113, 201)};
        scene.setBlocks(blocks);
        assertFalse(scene.isKingFalling());
    }

    @Test
    public void testIsKingOnPrincess() {
        Princess princess = new Princess(100, 200);
        scene.setPrincess(princess);
        assertTrue(scene.isKingOnPrincess());
    }

    @Test
    public void testIsKingNotOnPrincess() {
        Princess princess = new Princess(300, 300);
        scene.setPrincess(princess);
        assertFalse(scene.isKingOnPrincess());
    }

    @Test
    public void testMoveDown() {
        scene.moveDown();
        assertEquals(new Position(100, 201), king.getPosition());
    }

    @Test
    public void testMoveRight() throws InterruptedException {
        scene.moveRight(5);
        assertEquals(new Position(105, 200), king.getPosition());
    }

    @Test
    public void testMoveLeft() throws InterruptedException {
        scene.moveLeft(5);
        assertEquals(new Position(95, 200), king.getPosition());
    }

    @Test
    public void testJump() {
        List<Position> positions = scene.jump(100, 1);
        assertFalse(positions.isEmpty());
    }

    @Test
    public void testUpdateKingPosition() {
        Queue<Position> jumpPositions = new LinkedList<>();
        jumpPositions.add(new Position(100, 199));
        assertTrue(scene.updateKingPosition(jumpPositions));
    }

    @Test
    public void testCanKingMoveBoundary() {
        Position position = new Position(0, 0);
        assertFalse(scene.canKingMove(position));
    }

    @Test
    public void testIsKingFallingNotFalling() {
        Block[] blocks = {new Block(100, 201)};
        scene.setBlocks(blocks);
        assertFalse(scene.isKingFalling());
    }

    @Test
    public void testIsKingOnPrincessNotOnPrincess() {
        Princess princess = new Princess(300, 300);
        scene.setPrincess(princess);
        assertFalse(scene.isKingOnPrincess());
    }

    @Test
    public void testMoveDownCannotMove() {
        Block[] blocks = {new Block(100, 201)};
        scene.setBlocks(blocks);
        scene.moveDown();
        assertEquals(new Position(100, 200), king.getPosition());
    }

    @Test
    public void testMoveRightCannotMove() throws InterruptedException {
        Block[] blocks = {new Block(101, 200)};
        scene.setBlocks(blocks);
        scene.moveRight(1);
        assertEquals(new Position(100, 200), king.getPosition());
    }

    @Test
    public void testMoveLeftCannotMove() throws InterruptedException {
        Block[] blocks = {new Block(99, 200)};
        scene.setBlocks(blocks);
        scene.moveLeft(1);
        assertEquals(new Position(100, 200), king.getPosition());
    }

    @Test
    public void testJumpDifferentHeights() {
        List<Position> positions = scene.jump(50, 1);
        assertFalse(positions.isEmpty());
    }

    @Test
    public void testUpdateKingPositionCannotMove() {
        Queue<Position> jumpPositions = new LinkedList<>();
        jumpPositions.add(new Position(333, 2));
        assertFalse(scene.updateKingPosition(jumpPositions));
    }

}