package jumpking.model.menu;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.Arrays;
import java.util.List;

public class MainMenu extends Menu {

    @Override
    protected List<Item> createItems() {
        int screenWidth = 111;
        int screenHeight = 74;
        Item title = new Item(screenWidth / 2 - 10, screenHeight / 4, Item.Type.TITLE, 30, 30); // Título no topo
        Item start = new Item(screenWidth / 2 - 10, screenHeight / 2 + 5, Item.Type.START_GAME, 30, 30); // Início no meio
        Item exit = new Item(screenWidth / 2 - 10, screenHeight / 2 + 10, Item.Type.EXIT, 30, 30); // Sair logo abaixo
        return Arrays.asList(title, start, exit);
    }

}
