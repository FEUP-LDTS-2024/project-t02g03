package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.Position;
import jumpking.model.credits.Credits;
import jumpking.model.game.elements.King;
import jumpking.model.game.scene.Scene;
import jumpking.states.CreditsState;
import jumpking.view.SpriteLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.mockito.Mockito.*;

public class SceneControllerTest {
    private Scene scene;
    private Application app;
    private KingController kingController;
    private GUI gui;
    private SceneController sceneController;

    @BeforeEach
    void setUp() throws IOException {
        scene = mock(Scene.class);
        app = mock(Application.class);
        kingController = mock(KingController.class);
        gui = mock(GUI.class);
        sceneController = new SceneController(scene, kingController);

        when(app.getGui()).thenReturn(gui);
    }

    @Test
    void stepTest() throws IOException {
        GUI.Act act = GUI.Act.NONE;
        long time = 1000L;
        Queue<Position> jumpPositions = new ArrayDeque<>();

        when(kingController.getJumpPositions()).thenReturn(jumpPositions);

        sceneController.step(app, act, time);

        verify(kingController).step(app, act, time);
        verify(scene).changeScene(app);
        verify(gui, never()).draw();
    }

    @Test
    void handleFallingTest() throws IOException, InterruptedException {
        when(scene.isKingFalling()).thenReturn(true);
        when(kingController.getJumpPositions()).thenReturn(new ArrayDeque<>());

        sceneController.step(app, GUI.Act.NONE, 1000L);

        verify(kingController).handleFalling(gui);
        verify(gui, never()).draw();

        reset(scene);
        when(scene.isKingFalling()).thenReturn(false);
        when(kingController.getJumpPositions()).thenReturn(new ArrayDeque<>());

        sceneController.step(app, GUI.Act.NONE, 1000L);

        verify(gui, never()).draw();
    }

    @Test
    void kingOnPrincessTest() throws IOException {
        King king = mock(King.class);
        SpriteLoader spriteLoader = mock(SpriteLoader.class);
        when(app.getSpriteLoader()).thenReturn(spriteLoader);
        when(scene.isKingOnPrincess()).thenReturn(true);
        when(kingController.getJumpPositions()).thenReturn(new ArrayDeque<>());
        when(scene.getKing()).thenReturn(king);

        sceneController.step(app, GUI.Act.NONE, 1000L);

        verify(app).setState(any(CreditsState.class));
    }

    @Test
    void testStepWithEmptyJumpPositions() throws IOException, InterruptedException {
        when(kingController.getJumpPositions()).thenReturn(new ArrayDeque<>());
        when(scene.isKingOnPrincess()).thenReturn(false);

        sceneController.step(app, GUI.Act.NONE, 1000L);

        verify(kingController).handleFalling(gui);
        verify(gui, never()).draw();
        verify(app, never()).setState(any(CreditsState.class));
    }

    @Test
    void testStepWithNonEmptyJumpPositions() throws IOException, InterruptedException {
        Queue<Position> jumpPositions = new ArrayDeque<>();
        jumpPositions.add(new Position(0, 0));
        when(kingController.getJumpPositions()).thenReturn(jumpPositions);
        when(scene.isKingOnPrincess()).thenReturn(false);

        sceneController.step(app, GUI.Act.NONE, 1000L);

        verify(gui).draw();
        verify(kingController, never()).handleFalling(gui);
        verify(app, never()).setState(any(CreditsState.class));
    }

    @Test
    void testStepKingOnPrincess() throws IOException, InterruptedException {
        when(kingController.getJumpPositions()).thenReturn(new ArrayDeque<>());
        when(scene.isKingOnPrincess()).thenReturn(true);
        King king = mock(King.class);
        when(scene.getKing()).thenReturn(king);
        SpriteLoader spriteLoader = mock(SpriteLoader.class);
        when(app.getSpriteLoader()).thenReturn(spriteLoader);

        sceneController.step(app, GUI.Act.NONE, 1000L);

        verify(app).setState(any(CreditsState.class));
    }
}