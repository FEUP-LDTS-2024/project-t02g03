package jumpking.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PauseMenuTest {
    private PauseMenu pauseMenu;

    @BeforeEach
    void setUp() {
        pauseMenu = new PauseMenu(100, 200, 1, 10, 1000L);
    }

    @Test
    public void testPauseMenuInitialization() {
        assertEquals(100, pauseMenu.getKingX());
        assertEquals(200, pauseMenu.getKingY());
        assertEquals(1, pauseMenu.getSceneCode());
        assertEquals(10, pauseMenu.getKingJumps());
        assertEquals(1000L, pauseMenu.getKingStartTime());
    }

    @Test
    void testCreateItems() {
        int expectedScreenWidth = 333;
        int expectedScreenHeight = 250;

        int expectedStartX = expectedScreenWidth / 2 - 30;
        int expectedStartY = expectedScreenHeight / 2 - 10;
        int expectedQuitX = expectedScreenWidth / 2 - 30;
        int expectedQuitY = expectedScreenHeight / 2 + 5;

        List<Item> items = pauseMenu.createItems();
        assertEquals(2, items.size());

        Item resumeItem = items.get(0);
        assertEquals(Item.Type.RESUME, resumeItem.getType());
        assertEquals(expectedStartX, resumeItem.getPosition().getX());
        assertEquals(expectedStartY, resumeItem.getPosition().getY());

        Item quitItem = items.get(1);
        assertEquals(Item.Type.QUIT, quitItem.getType());
        assertEquals(expectedQuitX, quitItem.getPosition().getX());
        assertEquals(expectedQuitY, quitItem.getPosition().getY());
    }

    @Test
    public void testMoveDown() {
        pauseMenu.moveDown();
        assertEquals(Item.Type.QUIT, pauseMenu.getCurrentItem().getType());

        pauseMenu.moveDown();
        assertEquals(Item.Type.RESUME, pauseMenu.getCurrentItem().getType());
    }

    @Test
    public void testMoveUp() {
        pauseMenu.moveUp();
        assertEquals(Item.Type.QUIT, pauseMenu.getCurrentItem().getType());

        pauseMenu.moveUp();
        assertEquals(Item.Type.RESUME, pauseMenu.getCurrentItem().getType());
    }
}
