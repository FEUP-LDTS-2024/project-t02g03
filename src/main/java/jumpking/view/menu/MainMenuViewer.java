package jumpking.view.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import jumpking.gui.GUI;
import jumpking.model.menu.Item;
import jumpking.view.images.quitText;
import jumpking.view.images.startText;


public class MainMenuViewer {
    //meter imagem
    public void draw(Item item, GUI gui, Item currentItem) {
        TextColor color;
        if(item.equals(currentItem)) {
            color = TextColor.Factory.fromString("#FFFFFF");
        }else {
            color = TextColor.Factory.fromString("#FF5000");
        }
        Screen screen = gui.getScreen();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000")); // meter imagem inicial

         if (item.getType() == Item.Type.START_GAME) {
            gui.drawTextImage(item.getPosition(), startText.getStartText(),color);
        } else if (item.getType() == Item.Type.EXIT) {
            gui.drawTextImage(item.getPosition(), quitText.getExitText(),color);
        }
    }
}