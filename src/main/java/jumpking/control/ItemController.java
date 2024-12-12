package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.game.elements.King;
import jumpking.model.game.scene.Scene;
import jumpking.model.game.scene.SceneBuilder;
import jumpking.model.menu.Menu;
import jumpking.states.GameState;
import jumpking.view.SpriteLoader;

public class ItemController extends Controller<Menu> {
    public ItemController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Application app, GUI.Act act, long time) throws Exception {
//        if (act == GUI.Act.UP) {
//            this.getModel().moveUp();
//        } else if (act == GUI.Act.DOWN) {
//            this.getModel().moveUp();
//        }if (act == GUI.Act.SELECT) {
//                app.setState(new GameState(new SceneBuilder(0).buildScene(new King(168, 228)), app.getSpriteLoader()));
//            }
        }
    }
