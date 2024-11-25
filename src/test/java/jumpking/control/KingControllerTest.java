package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.game.scene.Scene;
import java.io.IOException;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class KingControllerTest {
    private Scene scene;
    private Application app;
    private KingController kingController;

    @BeforeEach
    void setUp() {
        this.scene = Mockito.mock(Scene.class);
        this.app = Mockito.mock(Application.class);
        this.kingController = new KingController(scene);
    }

    @Test
    void jumpUpTest() throws IOException {
        kingController.step(app, GUI.Act.UP, 0);
        kingController.step(app, GUI.Act.UP, 0);
        verify(scene, times(1)).moveUp(KingController.MIN_JUMP_HEIGHT);
    }

    @Test
    void moveLeftTest() throws IOException, InterruptedException {
        kingController.step(app, GUI.Act.LEFT, 0);
        verify(scene, times(1)).moveLeft(5);
    }

    @Test
    void jumpLeftTest() throws IOException {
        kingController.step(app, GUI.Act.UP, 0);
        kingController.step(app, GUI.Act.LEFT, 0);
        verify(scene, times(1)).jump(KingController.MIN_JUMP_HEIGHT, -1);
    }

    @Test
    void moveRightTest() throws IOException, InterruptedException {
        kingController.step(app, GUI.Act.RIGHT, 0);
        verify(scene, times(1)).moveRight(5);
    }

    @Test
    void testJumpRight() throws IOException {
        kingController.step(app, GUI.Act.UP, 0);
        kingController.step(app, GUI.Act.RIGHT, 0);
        verify(scene, times(1)).jump(KingController.MIN_JUMP_HEIGHT, 1);
    }

    @Test
    void quitTest() throws IOException {
        kingController.step(app, GUI.Act.QUIT, 0);
        verify(app, times(1)).setRunning(false);
    }
}
