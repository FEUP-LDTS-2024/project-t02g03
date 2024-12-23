package jumpking.states;

import jumpking.Application;
import jumpking.control.Controller;
import jumpking.gui.GUI;
import jumpking.view.SpriteLoader;
import jumpking.view.screens.ScreenViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class StateTest {
    private TestState testState;
    private GUI gui;
    private Application app;
    private Controller<Object> controller;
    private ScreenViewer<Object> screenViewer;

    @BeforeEach
    public void setUp() throws IOException {
        gui = mock(GUI.class);
        app = mock(Application.class);
        SpriteLoader spriteLoader = mock(SpriteLoader.class);
        testState = new TestState(new Object(), spriteLoader);
        controller = testState.controller;
        screenViewer = testState.screenViewer;
    }

    @Test
    public void testStep() throws Exception {
        when(gui.getNextAction()).thenReturn(GUI.Act.NONE);

        testState.step(app, gui, 100L);

        verify(controller).step(app, GUI.Act.NONE, 100L);
        verify(screenViewer).draw(gui, 100L);
    }

    @Test
    public void testStepWithException() throws Exception {
        when(gui.getNextAction()).thenThrow(new RuntimeException("Test Exception"));

        try {
            testState.step(app, gui, 100L);
            fail("Expected a RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertEquals("Test Exception", e.getMessage());
        }
    }
}