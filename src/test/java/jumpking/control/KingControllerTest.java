package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.Position;
import jumpking.model.game.elements.King;
import jumpking.model.game.scene.Scene;
import jumpking.states.CreditsState;
import jumpking.states.PauseState;
import jumpking.view.SpriteLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KingControllerTest {
    private KingController kingController;
    private Scene scene;
    private King king;
    private Application app;
    private GUI gui;
    private SpriteLoader spriteLoader;

    @BeforeEach
    void setUp(){
        scene = mock(Scene.class);
        king = mock(King.class);
        app = mock(Application.class);
        gui = mock(GUI.class);
        spriteLoader = mock(SpriteLoader.class);

        when(scene.getKing()).thenReturn(king);
        when(king.getJumpPositions()).thenReturn(new ArrayDeque<>());
        when(app.getSpriteLoader()).thenReturn(spriteLoader);

        kingController = new KingController(scene);
    }

    @Test
    void testStepUpAction() throws IOException {
        when(king.getState()).thenReturn(King.PlayerState.IDLE);
        kingController.step(app, GUI.Act.UP, System.currentTimeMillis());
        verify(king, times(1)).setState(King.PlayerState.CROUCHING);
    }

    @Test
    void testStepLeftAction() throws IOException, InterruptedException {
        when(king.getState()).thenReturn(King.PlayerState.IDLE);
        kingController.step(app, GUI.Act.LEFT, System.currentTimeMillis());
        verify(scene, times(1)).moveLeft(5);
        verify(king, times(1)).setFacingRight(false);
        verify(king, times(1)).setState(King.PlayerState.RUNNING);
    }

    @Test
    void testStepRightAction() throws IOException, InterruptedException {
        when(king.getState()).thenReturn(King.PlayerState.IDLE);
        kingController.step(app, GUI.Act.RIGHT, System.currentTimeMillis());
        verify(scene, times(1)).moveRight(5);
        verify(king, times(1)).setFacingRight(true);
        verify(king, times(1)).setState(King.PlayerState.RUNNING);
    }

    @Test
    void testStepPauseAction() throws IOException {
        kingController.step(app, GUI.Act.PAUSE, System.currentTimeMillis());
        verify(app, times(1)).setState(any(PauseState.class));
    }

    @Test
    void testStepQuitAction() throws IOException {
        kingController.step(app, GUI.Act.QUIT, System.currentTimeMillis());
        verify(app, times(1)).setState(any(CreditsState.class));
    }

    @Test
    void testStepNoneAction() throws IOException {
        when(king.getState()).thenReturn(King.PlayerState.RUNNING);
        kingController.step(app, GUI.Act.NONE, System.currentTimeMillis());
        kingController.step(app, GUI.Act.NONE, System.currentTimeMillis());
        verify(king, times(1)).setState(King.PlayerState.IDLE);
    }

    @Test
    void testHandleFalling() throws IOException, InterruptedException {
        when(scene.isKingFalling()).thenReturn(true);
        kingController.handleFalling(gui);
        verify(scene, times(2)).moveDown();
        verify(king, times(1)).setState(King.PlayerState.FALLING);
        verify(gui, times(1)).draw();
    }

    @Test
    void testStepUpActionRelease() throws IOException{
        when(king.getState()).thenReturn(King.PlayerState.CROUCHING);
        kingController.step(app, GUI.Act.UP, System.currentTimeMillis());
        kingController.step(app, GUI.Act.UP, System.currentTimeMillis() + 100);
        verify(king, times(1)).setState(King.PlayerState.JUMPING);
    }

    @Test
    void testStepLeftJump() throws IOException{
        when(king.getState()).thenReturn(King.PlayerState.CROUCHING);
        kingController.step(app, GUI.Act.UP, System.currentTimeMillis());
        kingController.step(app, GUI.Act.LEFT, System.currentTimeMillis() + 100);
        verify(king, times(1)).setFacingRight(false);
        verify(king, times(2)).setState(King.PlayerState.IDLE);
    }

    @Test
    void testStepRightJump() throws IOException{
        when(king.getState()).thenReturn(King.PlayerState.CROUCHING);
        kingController.step(app, GUI.Act.UP, System.currentTimeMillis());
        kingController.step(app, GUI.Act.RIGHT, System.currentTimeMillis() + 100);
        verify(king, times(1)).setFacingRight(true);
        verify(king, times(2)).setState(King.PlayerState.IDLE);
    }

    @Test
    void testGetJumpPositions() {
        Queue<Position> expectedJumpPositions = new ArrayDeque<>();
        when(king.getJumpPositions()).thenReturn(expectedJumpPositions);

        Queue<Position> actualJumpPositions = kingController.getJumpPositions();

        assertEquals(expectedJumpPositions, actualJumpPositions);
        verify(king, times(1)).getJumpPositions();
    }

    @Test
    void testUpdateKingPositionClearsJumpPositions() throws IOException {
        // Arrange
        Queue<Position> jumpPositions = new ArrayDeque<>();
        jumpPositions.add(new Position(0, 0));
        when(scene.getKing()).thenReturn(king);
        when(king.getJumpPositions()).thenReturn(jumpPositions);
        when(scene.updateKingPosition(jumpPositions)).thenReturn(false);
        KingController kingController = new KingController(scene);
        kingController.step(app, GUI.Act.NONE, System.currentTimeMillis());
        assertTrue(jumpPositions.isEmpty());
    }

    @Test
    void KingStateChangetoJumping() throws IOException {
        Queue<Position> jumpPositions = new ArrayDeque<>();
        jumpPositions.add(new Position(0, 0));
        when(king.getJumpPositions()).thenReturn(jumpPositions);
        when(scene.updateKingPosition(jumpPositions)).thenReturn(true);
        when(king.getPosition()).thenReturn(new Position(0, 1));
        when(king.getState()).thenReturn(King.PlayerState.JUMPING);
        kingController.step(app, GUI.Act.NONE, System.currentTimeMillis());
        assertEquals(King.PlayerState.JUMPING, king.getState());
    }
}