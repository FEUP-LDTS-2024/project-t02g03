package jumpking.states;

import jumpking.Application;
import jumpking.control.Controller;
import jumpking.gui.GUI;
import jumpking.view.screens.ScreenViewer;
import jumpking.view.SpriteLoader;
import jumpking.view.ViewProvider;

import java.io.IOException;

import static org.mockito.Mockito.mock;

public class TestState extends State<Object> {

    public TestState(Object model, SpriteLoader spriteLoader) throws IOException {
        super(model, spriteLoader);
    }

    @Override
    protected ScreenViewer<Object> createScreenViewer(ViewProvider viewProvider) {
        return mock(ScreenViewer.class);
    }

    @Override
    protected Controller<Object> createController() throws IOException {
        return mock(Controller.class);
    }
}