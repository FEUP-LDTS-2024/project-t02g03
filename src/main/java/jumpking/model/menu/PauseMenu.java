package jumpking.model.menu;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Arrays;
import java.util.List;

public class PauseMenu extends Menu {
    @Override
    protected List<Item> createItems() {
        //valores provisorios
        Item start = new Item(160/ 2 - (5 / 2) * 3 - (5 / 2) - 2, 55, Item.Type.START_GAME); //trocar para resume
        Item exit = new Item(160/ 2 - (4 / 2) * 3 - (4 / 2), 65, Item.Type.EXIT);
        return Arrays.asList(start, exit);
    }
}
