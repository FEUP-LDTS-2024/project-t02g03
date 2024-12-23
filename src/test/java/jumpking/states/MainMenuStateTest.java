package jumpking.states;

import jumpking.control.Controller;
import jumpking.control.MainMenuController;
import jumpking.model.menu.MainMenu;
import jumpking.view.SpriteLoader;
import jumpking.view.ViewProvider;
import jumpking.view.screens.MenuViewer;
import jumpking.view.screens.ScreenViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class MainMenuStateTest {
    private MainMenuState mainMenuState;
    private MainMenu mainMenu;
    private SpriteLoader spriteLoader;

    @BeforeEach
    public void setUp() throws IOException {
        mainMenu = mock(MainMenu.class);
        spriteLoader = mock(SpriteLoader.class);
        mainMenuState = new MainMenuState(mainMenu, spriteLoader);
    }

    @Test
    public void testCreateScreenViewer() {
        ViewProvider viewProvider = mock(ViewProvider.class);
        ScreenViewer<MainMenu> screenViewer = mainMenuState.createScreenViewer(viewProvider);
        assertNotNull(screenViewer);
        assert(screenViewer instanceof MenuViewer);
    }

    @Test
    public void testCreateController() {
        Controller<MainMenu> controller = mainMenuState.createController();
        assertNotNull(controller);
        assert(controller instanceof MainMenuController);
    }
}
