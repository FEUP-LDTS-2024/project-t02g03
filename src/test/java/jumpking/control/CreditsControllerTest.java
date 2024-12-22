package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.credits.Credits;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import static org.mockito.Mockito.mock;

public class CreditsControllerTest {
    private CreditsController<Credits> creditsController;
    private Credits credits;
    private Application app;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        credits = mock(Credits.class);
        app = mock(Application.class);
        gui = mock(GUI.class);
        creditsController = new CreditsController<>(credits);
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
