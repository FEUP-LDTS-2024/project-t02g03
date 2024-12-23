package jumpking.states;

import jumpking.control.Controller;
import jumpking.control.CreditsController;
import jumpking.model.credits.Credits;
import jumpking.view.SpriteLoader;
import jumpking.view.ViewProvider;
import jumpking.view.screens.CreditsViewer;
import jumpking.view.screens.ScreenViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class CredistStateTest {
    private CreditsState creditsState;
    private Credits credits;
    private SpriteLoader spriteLoader;

    @BeforeEach
    public void setUp() throws IOException {
        credits = mock(Credits.class);
        spriteLoader = mock(SpriteLoader.class);
        creditsState = new CreditsState(credits, spriteLoader);
    }

    @Test
    public void testCreateScreenViewer() {
        ViewProvider viewProvider = mock(ViewProvider.class);
        ScreenViewer<Credits> screenViewer = creditsState.createScreenViewer(viewProvider);
        assertNotNull(screenViewer);
        assert(screenViewer instanceof CreditsViewer);
    }

    @Test
    public void testCreateController() {
        Controller<Credits> controller = creditsState.createController();
        assertNotNull(controller);
        assert(controller instanceof CreditsController);
    }
}
