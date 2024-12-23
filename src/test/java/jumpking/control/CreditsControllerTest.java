package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.credits.Credits;
import jumpking.view.SpriteLoader;
import jumpking.view.elements.KingViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreditsControllerTest {
    private CreditsController<Credits> creditsController;
    private Credits credits;
    private Application app;
    private GUI gui;
    private SpriteLoader spriteLoader;

    @BeforeEach
    public void setUp() throws IOException {
        credits = mock(Credits.class);
        app = mock(Application.class);
        gui = mock(GUI.class);
        spriteLoader = mock(SpriteLoader.class);
        creditsController = new CreditsController<>(credits);
        when(app.getSpriteLoader()).thenReturn(spriteLoader);
    }

    @Test
    public void testStepQuitAction() throws Exception {
        creditsController.step(app, GUI.Act.QUIT, 0L);
        Mockito.verify(app, Mockito.times(1)).setState(Mockito.any());
    }

    @Test
    public void stepWithoutQuit() throws Exception {
        creditsController.step(app, GUI.Act.NONE, 0L);
        Mockito.verify(app, Mockito.times(0)).setState(Mockito.any());
    }
}
