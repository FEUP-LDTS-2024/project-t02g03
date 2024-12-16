package jumpking.view.screens;

import jumpking.gui.GUI;
import jumpking.model.menu.Item;
import jumpking.model.menu.Menu;
import jumpking.view.ViewProvider;
import jumpking.view.menu.LogoViewer;
import jumpking.view.menu.DrawViewer;

import java.io.IOException;
import java.util.List;

public class MenuViewer<T extends Menu> extends ScreenViewer<T> {
    private final DrawViewer drawViewer;
    private final LogoViewer logoViewer;

    public MenuViewer(T model, ViewProvider viewProvider) {
        super(model);
        this.drawViewer = viewProvider.getDrawViewer();
        this.logoViewer = viewProvider.getLogoViewer();
    }

    @Override
    public void draw(GUI gui, long time) throws IOException {
        gui.clear();
        drawBackground(gui);
        drawItems(gui, getModel().getItems());
        logoViewer.draw(gui, 70, 40);
        gui.refresh();
    }

    public void drawItems(GUI gui, List<Item>items) {
        for(Item item: items){
        drawViewer.draw(item,gui,getModel().getCurrentItem());
        }
    }
}
