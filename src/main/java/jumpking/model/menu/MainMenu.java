package jumpking.model.menu;

import jumpking.Application;

import java.util.Arrays;
import java.util.List;

public class MainMenu extends Menu {

    @Override
    protected List<Item> createItems() {
        Item start = new Item(160/ 2 - (5 / 2) * 3 - (5 / 2) - 2, 55, Item.Type.START_GAME);
        Item exit = new Item(160/ 2 - (4 / 2) * 3 - (4 / 2), 65, Item.Type.EXIT);
        return Arrays.asList(start, exit);
    }
}
