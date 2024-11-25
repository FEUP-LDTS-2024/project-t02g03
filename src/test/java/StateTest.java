import jumpking.Application;
import jumpking.control.Controller;
import jumpking.gui.GUI;
import jumpking.model.game.scene.Scene;
import jumpking.states.State;
import jumpking.view.SpriteLoader;
import jumpking.view.ViewProvider;
import jumpking.view.screens.ScreenViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StateTest {
    private State<Scene> state;
    private Scene scene;
    private SpriteLoader spriteLoader;
    private Controller<Scene> controller;
    private ScreenViewer<Scene> screenViewer;
    private Application app;
    private GUI gui;

    @BeforeEach
    public void setUp() throws IOException {
        scene = Mockito.mock(Scene.class);
        spriteLoader = Mockito.mock(SpriteLoader.class);
        controller = Mockito.mock(Controller.class);
        screenViewer = Mockito.mock(ScreenViewer.class);
        app = Mockito.mock(Application.class);
        gui = Mockito.mock(GUI.class);

        state = new State<Scene>(scene, spriteLoader) {
            @Override
            protected ScreenViewer<Scene> createScreenViewer(ViewProvider viewProvider) {
                return screenViewer;
            }

            @Override
            protected Controller<Scene> createController() {
                return controller;
            }
        };
    }

//    @Test
//    public void testGetModel() {
//        assertEquals(scene, state.getModel());
//    }
//
//    @Test
//    public void testStep() throws Exception {
//        long time = System.currentTimeMillis();
//        GUI.Act act = GUI.Act.NONE;
//
//        when(gui.getNextAction()).thenReturn(act);
//
//        state.step(app, gui, time);
//
//        verify(gui).getNextAction();
//        verify(controller).step(app, act, time);
//        verify(screenViewer).draw(gui, time);
//    }
}
