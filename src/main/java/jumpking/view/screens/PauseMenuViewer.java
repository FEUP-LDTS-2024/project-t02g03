package jumpking.view.screens;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import jumpking.gui.GUI;
import jumpking.model.menu.Item;
import jumpking.model.menu.Menu;
import jumpking.model.menu.PauseMenu;
import jumpking.view.ViewProvider;
import jumpking.view.images.quitText;
import jumpking.view.images.startText;
import jumpking.view.menu.MainMenuViewer;

import java.io.IOException;
import java.util.List;

public class PauseMenuViewer<T extends Menu> extends ScreenViewer<T> {

    public PauseMenuViewer(T model, ViewProvider viewProvider) {
        super(model);
    }

    @Override
    public void draw(GUI gui, long time) throws IOException {
        gui.clear();
        drawItems(gui, getModel().getItems());
        gui.refresh();
    }

    public void drawItems(GUI gui, List<Item> items) {
        for (Item item : items) {
            drawitems(item, gui, getModel().getCurrentItem());
        }
    }

    //trocar
    public void drawitems(Item item, GUI gui, Item currentItem){

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
