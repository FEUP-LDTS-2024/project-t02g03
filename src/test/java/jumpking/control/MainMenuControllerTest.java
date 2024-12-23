package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.menu.Item;
import jumpking.model.menu.MainMenu;
import jumpking.states.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class MainMenuControllerTest{

    private MainMenuController mainMenuController;
    private MainMenu mainMenu;
    private Application app;
    private ItemController itemController;
    private Item currentItem;

    @BeforeEach
    public void setUp() {
        mainMenu = mock(MainMenu.class);
        app = mock(Application.class);
        currentItem = mock(Item.class);
        when(mainMenu.getCurrentItem()).thenReturn(currentItem);
        itemController =  mock(ItemController.class);
        mainMenuController = new MainMenuController(mainMenu,itemController);
    }

    @Test
    public void actionDownItem() throws Exception{
        mainMenuController.step(app, GUI.Act.DOWN, 0);
        Mockito.verify(mainMenu, Mockito.times(1)).moveDown();
    }

    @Test
    public void actionUpItem() throws Exception{
        mainMenuController.step(app, GUI.Act.UP, 0);
        Mockito.verify(mainMenu, Mockito.times(1)).moveUp();
    }

    @Test
    public void actionQuit() throws Exception {
        mainMenuController.step(app, GUI.Act.QUIT, 0);
        verify(app, times(1)).setRunning(false);
    }

    @Test
    public void actionOther() throws Exception {
        mainMenuController.step(app, GUI.Act.SELECT, 0);
        verify(itemController, times(1)).step(app, GUI.Act.SELECT, 0);
    }

}
