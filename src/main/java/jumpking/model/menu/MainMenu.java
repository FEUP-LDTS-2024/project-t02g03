package jumpking.model.menu;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.BasicTextImage;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MainMenu extends Menu {

    @Override
    protected List<Item> createItems() {
        int screenWidth = 333;
        int screenHeight = 250;

        Item start = new Item(screenWidth / 2 -30, screenHeight / 2 - 10, Item.Type.START_GAME);
        Item exit = new Item(screenWidth / 2 -30, screenHeight / 2 +5 , Item.Type.QUIT);
        return Arrays.asList(start, exit);
    }

}
