package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.menu.PauseMenu;

public class PauseMenuController extends MenuController<PauseMenu> {
    public PauseMenuController(PauseMenu pauseMenu, ItemController itemController) {
        super(pauseMenu, itemController);
    }

    @Override
    public void step(Application app, GUI.Act act, long time) throws Exception {
        super.step(app, act, time);
    }
}
