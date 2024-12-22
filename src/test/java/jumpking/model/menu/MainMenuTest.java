package jumpking.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainMenuTest {
    private MainMenu mainMenu;

    @BeforeEach
    void setUp() {
        mainMenu = new MainMenu();
    }

    @Test
    void testCreateItems() {
        int expectedScreenWidth = 333;
        int expectedScreenHeight = 250;

        int expectedStartX = expectedScreenWidth / 2 - 30;
        int expectedStartY = expectedScreenHeight / 2 - 10;
        int expectedQuitX = expectedScreenWidth / 2 - 30;
        int expectedQuitY = expectedScreenHeight / 2 + 5;

        List<Item> items = mainMenu.createItems();
        assertEquals(2, items.size());

        Item startItem = items.get(0);
        assertEquals(Item.Type.START_GAME, startItem.getType());
        assertEquals(expectedStartX, startItem.getPosition().getX());
        assertEquals(expectedStartY, startItem.getPosition().getY());

        Item exitItem = items.get(1);
        assertEquals(Item.Type.QUIT, exitItem.getType());
        assertEquals(expectedQuitX, exitItem.getPosition().getX());
        assertEquals(expectedQuitY, exitItem.getPosition().getY());
    }
    @Test
    public void testMoveDown() {
        mainMenu.moveDown();
        assertEquals(Item.Type.QUIT, mainMenu.getCurrentItem().getType());

        mainMenu.moveDown();
        assertEquals(Item.Type.START_GAME, mainMenu.getCurrentItem().getType());
    }

    @Test
    public void testMoveUp() {
        mainMenu.moveUp();
        assertEquals(Item.Type.QUIT, mainMenu.getCurrentItem().getType());

        mainMenu.moveUp();
        assertEquals(Item.Type.START_GAME,mainMenu.getCurrentItem().getType());
    }
}
