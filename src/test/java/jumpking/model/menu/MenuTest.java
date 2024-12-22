package jumpking.model.menu;
import com.googlecode.lanterna.graphics.BasicTextImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class MenuTest {
    private Menu menu;
    private BasicTextImage backgroundImage;

    @BeforeEach
    void setUp(){
        backgroundImage = mock(BasicTextImage.class);
        menu = new Menu(){
            @Override
            protected List<Item> createItems() {
                return List.of(new Item(10, 20, Item.Type.START_GAME), new Item(30, 40, Item.Type.QUIT));
            }
        };
    }

    @Test
    void testGetItems() {
        List<Item> items = menu.getItems();
        assertEquals(2, items.size());
        assertEquals(Item.Type.START_GAME, items.get(0).getType());
        assertEquals(Item.Type.QUIT, items.get(1).getType());
        assertEquals(2, items.size());
    }

    @Test
    void testMoveDown() {
        menu.moveDown();
        assertEquals(Item.Type.QUIT, menu.getCurrentItem().getType());
    }

    @Test
    void testMoveUp() {
        menu.moveUp();
        assertEquals(Item.Type.QUIT, menu.getCurrentItem().getType());
    }

    @Test
    void testGetCurrentItem() {
        assertEquals(Item.Type.START_GAME, menu.getCurrentItem().getType());
    }

    @Test
    void testGetBackgroundImage() {
        assertNotNull(menu.getBackgroundImage());
    }
}
