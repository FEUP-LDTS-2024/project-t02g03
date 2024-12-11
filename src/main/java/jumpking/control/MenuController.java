package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.menu.Item;
import jumpking.model.menu.Menu;

public abstract class MenuController<T extends Menu> extends Controller<T> {

    private final ItemController itemController;

    public MenuController(T model, ItemController itemController) {
        super(model);
        this.itemController = itemController;
    }

    @Override
    public void step(Application app, GUI.Act act, long time) throws Exception {
        if (act == GUI.Act.UP) {
            this.getModel().moveUp();
        } else if (act == GUI.Act.DOWN) {
            this.getModel().moveUp();
        }
//        else if(act == GUI.Act.SELECT){
//            this.getModel().select();
//        }
    }
}
