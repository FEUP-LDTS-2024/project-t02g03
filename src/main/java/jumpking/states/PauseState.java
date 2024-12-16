package jumpking.states;

import jumpking.control.Controller;
import jumpking.control.ItemController;
import jumpking.control.PauseMenuController;
import jumpking.model.menu.PauseMenu;
import jumpking.view.SpriteLoader;
import jumpking.view.ViewProvider;
import jumpking.view.screens.PauseMenuViewer;
import jumpking.view.screens.ScreenViewer;

import java.io.IOException;

public class PauseState extends State<PauseMenu> {

    public PauseState(PauseMenu pauseMenu, SpriteLoader spriteLoader) throws IOException {
        super(pauseMenu,spriteLoader);
    }
    @Override
    protected ScreenViewer<PauseMenu> createScreenViewer(ViewProvider viewProvider) {
        return new PauseMenuViewer<>(getModel(),viewProvider);
    }

    @Override
    protected Controller<PauseMenu> createController() {
        return new PauseMenuController(getModel(), new ItemController(getModel()));
    }
}
