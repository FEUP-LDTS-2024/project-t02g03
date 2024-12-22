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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainMenuControllerTest{

    private MainMenuController mainMenuController;
    private MainMenu mainMenu;
    private Application app;
    private Item item;
    private ItemController itemController;

    @BeforeEach
    public void setUp() {
        mainMenu = mock(MainMenu.class);
        app = mock(Application.class);
        item = mock(Item.class);
        itemController =  new ItemController(mainMenu);
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

}
