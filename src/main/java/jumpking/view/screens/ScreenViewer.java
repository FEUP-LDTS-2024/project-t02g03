package jumpking.view.screens;

import jumpking.gui.GUI;
import jumpking.model.credits.Credits;
import jumpking.model.game.scene.Scene;
import jumpking.model.menu.Menu;

import java.io.IOException;

public abstract class ScreenViewer<T> {

    private final T model;

    public ScreenViewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void draw(GUI gui, long time) throws IOException;

    protected void drawBackground(GUI gui) {
        if (model instanceof Scene) {
            Scene scene = (Scene) model;
            gui.drawImage(scene.getBackgroundImage());
        }else if(model instanceof Menu){
            Menu menu = (Menu) model;
            gui.drawImage(menu.getBackgroundImage());
        }else if(model instanceof Credits){
            Credits credits = (Credits) model;
            gui.drawImage(credits.getBackgroundImage());
        }
    }
}
