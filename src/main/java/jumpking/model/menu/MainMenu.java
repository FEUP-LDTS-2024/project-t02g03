package jumpking.model.menu;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.Arrays;
import java.util.List;

public class MainMenu extends Menu {

    @Override
    protected List<Item> createItems() {
        int screenWidth = 333;
        int screenHeight = 250;

        Item start = new Item(screenWidth / 2 - 10, screenHeight / 2 - 10, Item.Type.START_GAME); // Início no meio
        Item exit = new Item(screenWidth / 2 - 10, screenHeight / 2 +5 , Item.Type.EXIT); // Sair logo abaixo
        return Arrays.asList(start, exit);
    }

}
