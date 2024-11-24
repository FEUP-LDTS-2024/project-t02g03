package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;

public abstract class Controller<T> {

    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(Application app, GUI.Act act, long time) throws Exception;

}
