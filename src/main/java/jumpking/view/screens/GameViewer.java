package jumpking.view.screens;

import com.googlecode.lanterna.TextColor;
import jumpking.gui.GUI;
import jumpking.model.game.elements.Block;
import jumpking.model.game.elements.Element;
import jumpking.model.game.scene.Scene;
import jumpking.view.elements.ElementViewer;
import jumpking.view.elements.KingViewer;
import jumpking.view.ViewProvider;
import jumpking.view.elements.PrincessViewer;

import java.util.List;

public class GameViewer extends ScreenViewer<Scene> {

    private final KingViewer kingViewer;
    private final PrincessViewer princessViewer;

    public GameViewer(Scene model, ViewProvider viewProvider) {
        super(model);
        this.kingViewer = viewProvider.getKingViewer();
        this.princessViewer = viewProvider.getPrincessViewer();
    }

    @Override
    public void draw(GUI gui, long time) {
        gui.clear();
        drawBackground(gui);
        drawElement(gui, getModel().getKing(), kingViewer, time);
        drawElement(gui, getModel().getPrincess(), princessViewer, time);
        //drawBlocks(gui);
    }

    <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer, long time) {
        viewer.draw(element, gui, time);
    }

    <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer, long time) {
        for (T element : elements) {
            drawElement(gui, element, viewer, time);
        }
    }

//for visual testing purposes
    public void drawBlocks(GUI gui) {
        for (Block block : getModel().getBlocks()) {
            gui.drawPixel(block.getPosition(), TextColor.Factory.fromString("#00FF00"));
        }
    }
}