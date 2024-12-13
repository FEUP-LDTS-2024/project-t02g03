package jumpking.view.screens;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
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
        Screen screen = gui.getScreen();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000")); // meter imagem inicial
        drawItems(gui, getModel().getItems());
        logoViewer.draw(gui, 70, 50);
        //desenhar imagem com tg
        gui.refresh();
    }

    public void drawItems(GUI gui, List<Item>items) {
        for(Item item: items){
        drawViewer.draw(item,gui,getModel().getCurrentItem());
        }
    }
}
