package jumpking.view.screens;

import com.googlecode.lanterna.screen.Screen;
import jumpking.gui.GUI;
import jumpking.model.game.scene.Scene;
import jumpking.model.menu.Item;
import jumpking.model.menu.MainMenu;
import jumpking.model.menu.Menu;
import jumpking.view.ViewProvider;
import jumpking.view.menu.LogoViewer;
import jumpking.view.menu.MainMenuViewer;

import java.io.IOException;
import java.util.List;

public class MenuViewer<T extends Menu> extends ScreenViewer<T> {
    private final MainMenuViewer mainMenuViewer;
    private final LogoViewer logoViewer;

    public MenuViewer(T model, ViewProvider viewProvider) {
        super(model);
        this.mainMenuViewer = viewProvider.getMainMenuViewer();
        this.logoViewer = viewProvider.getLogoViewer();
    }

    @Override
    public void draw(GUI gui, long time) throws IOException {
        gui.clear();
        drawItems(gui, getModel().getItems());
        logoViewer.draw(gui, 0, 0);
        gui.refresh();
    }

    public void drawItems(GUI gui, List<Item>items) {
        for(Item item: items){
        mainMenuViewer.draw(item,gui,getModel().getCurrentItem());
        }
    }
}
