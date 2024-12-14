package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.menu.MainMenu;

public class MainMenuController extends MenuController<MainMenu>{
    public MainMenuController(MainMenu mainMenu, ItemController itemController) {
        super(mainMenu,itemController);
    }

    @Override
    public void step(Application app, GUI.Act act, long time) throws Exception {
        super.step(app, act, time);
    }
}
