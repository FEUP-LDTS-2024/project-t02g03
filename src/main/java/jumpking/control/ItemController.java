package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.game.elements.King;
import jumpking.model.game.scene.Scene;
import jumpking.model.game.scene.SceneBuilder;
import jumpking.model.menu.Item;
import jumpking.model.menu.Menu;
import jumpking.states.GameState;
import jumpking.view.SpriteLoader;

public class ItemController extends Controller<Menu> {
    public ItemController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Application app, GUI.Act act, long time) throws Exception {
        if (act == GUI.Act.SELECT) {
            if(this.getModel().getCurrentItem().getType()== Item.Type.START_GAME){
                Scene scene = new SceneBuilder(0).buildScene(new King(168, 228));
                app.setState(new GameState(scene, app.getSpriteLoader()));
            }if(this.getModel().getCurrentItem().getType()== Item.Type.EXIT){
                app.setRunning(false);
            }
        }
    }
}
