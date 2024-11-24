package jumpking.view;

import com.googlecode.lanterna.TextColor;
import jumpking.gui.GUI;
import jumpking.model.game.elements.Block;
import jumpking.model.game.elements.Element;
import jumpking.model.game.scene.Scene;

import java.util.List;

public class GameViewer extends ScreenViewer<Scene> {

    private final KingViewer kingViewer;

    public GameViewer(Scene model, ViewProvider viewProvider) {
        super(model);
        this.kingViewer = viewProvider.getKingViewer();
    }

    @Override
    public void draw(GUI gui, long time) {
        gui.clear();
        drawBackground(gui);
        drawElement(gui, getModel().getKing(), kingViewer, time);
        drawBlocks(gui);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer, long time) {
        viewer.draw(element, gui, time);
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer, long time) {
        for (T element : elements) {
            drawElement(gui, element, viewer, time);
        }
    }

    //remove
    public void drawBlocks(GUI gui) {
        for (Block block : getModel().getBlocks()) {
            gui.drawPixel(block.getPosition(), TextColor.Factory.fromString("#00FF00"));
        }
    }
}