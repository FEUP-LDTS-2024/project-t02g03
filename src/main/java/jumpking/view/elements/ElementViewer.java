package jumpking.view.elements;

import jumpking.gui.GUI;

public interface ElementViewer<T> {
    void draw(T model, GUI gui, long time);
}
