package jumpking.model.menu;

import jumpking.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    @Test
    void testGetPosition() {
        Item item = new Item(10, 20, Item.Type.START_GAME);
        Position position = item.getPosition();
        assertEquals(10, position.getX());
        assertEquals(20, position.getY());
    }

    @Test
    void testGetType() {
        Item item = new Item(10, 20, Item.Type.QUIT);
        assertEquals(Item.Type.QUIT, item.getType());
    }
}
