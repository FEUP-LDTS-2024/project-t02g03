package jumpking.view.screens;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import jumpking.gui.GUI;
import jumpking.model.menu.Item;
import jumpking.model.menu.Menu;
import jumpking.view.ViewProvider;
import jumpking.view.images.quitText;
import jumpking.view.images.resumeText;
import jumpking.view.images.startText;
import jumpking.view.menu.DrawViewer;
import jumpking.view.menu.LogoViewer;

import java.io.IOException;
import java.util.List;

public class PauseMenuViewer<T extends Menu> extends ScreenViewer<T> {
    private final LogoViewer logoViewer;
    private final DrawViewer drawViewer;

    public PauseMenuViewer(T model, ViewProvider viewProvider) {
        super(model);
        this.logoViewer= viewProvider.getLogoViewer();
        this.drawViewer = viewProvider.getDrawViewer();
    }

    @Override
    public void draw(GUI gui, long time) throws IOException {
        gui.clear();
        drawBackground(gui);
        logoViewer.draw(gui, 70, 50);
        drawItems(gui, getModel().getItems());
        gui.refresh();
    }

    public void drawItems(GUI gui, List<Item> items) {
        for (Item item : items) {
            drawViewer.draw(item, gui, getModel().getCurrentItem());
        }
    }

}
