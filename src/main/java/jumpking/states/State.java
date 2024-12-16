package jumpking.states;

import jumpking.Application;
import jumpking.control.Controller;
import jumpking.gui.GUI;
import jumpking.view.screens.ScreenViewer;
import jumpking.view.SpriteLoader;
import jumpking.view.ViewProvider;

import java.io.IOException;

public abstract class State<T> {

    private final T model;
    protected final Controller<T> controller;
    protected final ScreenViewer<T> screenViewer;

    public State(T model, SpriteLoader spriteLoader) throws IOException {
        this.model = model;
        this.controller = createController();
        this.screenViewer = createScreenViewer(new ViewProvider(spriteLoader));
    }

    protected abstract ScreenViewer<T> createScreenViewer(ViewProvider viewProvider);
    protected abstract Controller<T> createController();

    public T getModel(){
        return model;
    }

    public void step(Application app, GUI gui, long time) throws Exception {
        GUI.Act act = gui.getNextAction();
        controller.step(app, act, time);
        screenViewer.draw(gui, time);
    }
}
