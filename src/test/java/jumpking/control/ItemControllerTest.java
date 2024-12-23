package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.menu.Item;
import jumpking.model.menu.Menu;
import jumpking.model.menu.PauseMenu;
import jumpking.states.GameState;
import jumpking.states.State;
import jumpking.view.SpriteLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ItemControllerTest {
    private ItemController itemController;
    private Menu menu;
    private Application app;
    private Item item;
    private SpriteLoader spriteLoader;

    @BeforeEach
    public void setUp() {
        menu = mock(Menu.class);
        app = mock(Application.class);
        item = mock(Item.class);
        itemController = new ItemController(menu);
        spriteLoader = mock(SpriteLoader.class);
        when(app.getSpriteLoader()).thenReturn(spriteLoader);
    }

    @Test
    public void testStepStartGame() throws Exception {
        when(menu.getCurrentItem()).thenReturn(item);
        when(item.getType()).thenReturn(Item.Type.START_GAME);
        when(app.getSpriteLoader()).thenReturn(spriteLoader);
        itemController.step(app, GUI.Act.SELECT, 0L);
        verify(app, times(1)).setState(any(GameState.class));
    }

    @Test
    public void testStepQuit() throws Exception {
        when(menu.getCurrentItem()).thenReturn(item);
        when(item.getType()).thenReturn(Item.Type.QUIT);
        itemController.step(app, GUI.Act.SELECT, 0L);
        verify(app, times(1)).setRunning(false);
    }

    @Test
    public void testStepResume() throws Exception {
        PauseMenu pauseMenu = mock(PauseMenu.class);
        Item item = mock(Item.class); // Ensure item is mocked
        when(pauseMenu.getCurrentItem()).thenReturn(item); // Use pauseMenu instead of menu
        when(item.getType()).thenReturn(Item.Type.RESUME);
        when(pauseMenu.getSceneCode()).thenReturn(0);
        when(pauseMenu.getKingX()).thenReturn(168);
        when(pauseMenu.getKingY()).thenReturn(228);
        when(pauseMenu.getKingStartTime()).thenReturn(0L);
        when(pauseMenu.getKingJumps()).thenReturn(0);
        itemController = new ItemController(pauseMenu);
        itemController.step(app, GUI.Act.SELECT, 0L);
        verify(app, times(1)).setState(any(GameState.class));
    }

    @Test
    public void NoneStepGame() throws Exception {
        when(menu.getCurrentItem()).thenReturn(item);
        when(item.getType()).thenReturn(Item.Type.START_GAME);
        itemController.step(app, GUI.Act.NONE, 0);
        verify(app, Mockito.times(0)).setState(Mockito.any(State.class));
    }

}
