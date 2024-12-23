package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.menu.Item;
import jumpking.model.menu.PauseMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class PauseMenuControllerTest {
    private PauseMenuController pauseMenuController;
    private PauseMenu pauseMenu;
    private Application app;
    private ItemController itemController;

    @BeforeEach
    public void setUp() {
        pauseMenu = mock(PauseMenu.class);
        app = mock(Application.class);
        itemController = new ItemController(pauseMenu);
        pauseMenuController = new PauseMenuController(pauseMenu, itemController);
    }

    @Test
    public void actionDownItem() throws Exception {
        pauseMenuController.step(app, GUI.Act.DOWN, 0);
        Mockito.verify(pauseMenu, Mockito.times(1)).moveDown();
    }

    @Test
    public void actionUpItem() throws Exception {
        pauseMenuController.step(app, GUI.Act.UP, 0);
        Mockito.verify(pauseMenu, Mockito.times(1)).moveUp();
    }
}
