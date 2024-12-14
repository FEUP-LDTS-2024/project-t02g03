package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.game.scene.Scene;
import java.io.IOException;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class SceneControllerTest {
    private Scene scene;
    private Application app;
    private KingController kingController;
    private GUI gui;
    private SceneController sceneController;

    @BeforeEach
    void setUp() throws IOException {
        this.scene = Mockito.mock(Scene.class);
        this.app = Mockito.mock(Application.class);
        this.kingController = Mockito.mock(KingController.class);
        this.gui = Mockito.mock(GUI.class);
        sceneController = new SceneController(scene, kingController);
    }

    @Test
    void stepTest() throws IOException {
        GUI.Act act = Mockito.mock(GUI.Act.class);
        long time = 1000;
        sceneController.step(app, act, time);
        verify(kingController).step(app, act, time);
    }

    @Test
    void handleFallingTest() throws IOException {
        Mockito.when(scene.isKingFalling()).thenReturn(true);
        sceneController.step(app, Mockito.mock(GUI.Act.class), 1000);
        verify(scene, times(1)).moveDown();
        verify(gui, atLeastOnce()).draw();

        Mockito.reset(scene);
        Mockito.when(scene.isKingFalling()).thenReturn(false);
        sceneController.step(app, Mockito.mock(GUI.Act.class), 1000);
        verify(scene, times(1)).moveDown();
        verify(gui, atLeast(2)).draw();
    }
}
