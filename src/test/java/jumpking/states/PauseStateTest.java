package jumpking.states;

import jumpking.control.Controller;
import jumpking.control.PauseMenuController;
import jumpking.model.menu.PauseMenu;
import jumpking.view.SpriteLoader;
import jumpking.view.ViewProvider;
import jumpking.view.screens.PauseMenuViewer;
import jumpking.view.screens.ScreenViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class PauseStateTest {
    private PauseState pauseState;
    private PauseMenu pauseMenu;
    private SpriteLoader spriteLoader;

    @BeforeEach
    public void setUp() throws IOException {
        pauseMenu = mock(PauseMenu.class);
        spriteLoader = mock(SpriteLoader.class);
        pauseState = new PauseState(pauseMenu, spriteLoader);
    }

    @Test
    public void testCreateScreenViewer() {
        ViewProvider viewProvider = mock(ViewProvider.class);
        ScreenViewer<PauseMenu> screenViewer = pauseState.createScreenViewer(viewProvider);
        assertNotNull(screenViewer);
        assert(screenViewer instanceof PauseMenuViewer);
    }

    @Test
    public void testCreateController() {
        Controller<PauseMenu> controller = pauseState.createController();
        assertNotNull(controller);
        assert(controller instanceof PauseMenuController);
    }
}
