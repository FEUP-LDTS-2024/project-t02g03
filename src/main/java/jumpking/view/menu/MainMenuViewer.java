package jumpking.view.menu;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import jumpking.gui.GUI;
import jumpking.gui.LanternaGUI;
import jumpking.model.menu.Item;
import jumpking.view.ViewProvider;

import java.util.List;

public class MainMenuViewer {

    public MainMenuViewer() {
    }

    //meter imagem
//aumentar tamanho
//reduzir codigo
    public void draw(Item item, GUI gui, Item currentItem) {
        Screen screen = gui.getScreen();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fill(' ');
        if (item.getType() == Item.Type.TITLE) {
            TextGraphics graphics_title = screen.newTextGraphics();
            graphics_title.putString(new TerminalPosition(item.getPosition().getX(), item.getPosition().getY()), "JUMP KING");
        } else if (item.getType() == Item.Type.START_GAME) {
            TextGraphics graphics_start = screen.newTextGraphics();
            if (item == currentItem) {
                graphics_start.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            } else {
                graphics_start.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
            }
            graphics_start.putString(new TerminalPosition(item.getPosition().getX(), item.getPosition().getY()), "[ START GAME ]");
        } else if (item.getType() == Item.Type.EXIT) {
            TextGraphics graphics_exit = screen.newTextGraphics();
            if (item == currentItem) {
                graphics_exit.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            } else {
                graphics_exit.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
            }
            graphics_exit.putString(new TerminalPosition(item.getPosition().getX(), item.getPosition().getY()), "[ EXIT ]");

        }
    }
}