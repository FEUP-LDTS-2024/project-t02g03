package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.game.elements.King;
import jumpking.model.game.scene.Scene;
import jumpking.model.game.scene.SceneBuilder;
import jumpking.model.menu.Item;
import jumpking.model.menu.Menu;
import jumpking.model.menu.PauseMenu;
import jumpking.states.GameState;

public class ItemController extends Controller<Menu> {

    public ItemController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Application app, GUI.Act act, long time) throws Exception {
        if (act == GUI.Act.SELECT) {
            if (this.getModel().getCurrentItem().getType()== Item.Type.START_GAME){
                Scene scene = new SceneBuilder(0).buildScene(new King(168, 228));
                app.setState(new GameState(scene, app.getSpriteLoader()));
            } else if (this.getModel().getCurrentItem().getType()== Item.Type.QUIT){
                app.setRunning(false);
            } else if (this.getModel().getCurrentItem().getType()== Item.Type.RESUME){
                PauseMenu pauseMenu = (PauseMenu) this.getModel();
                Scene scene = new SceneBuilder(pauseMenu.getSceneCode()).buildScene(new King(pauseMenu.getKingX(), pauseMenu.getKingY()));
                scene.getKing().setStartTime(pauseMenu.getKingStartTime());
                scene.getKing().setJumps(pauseMenu.getKingJumps());
                app.setState(new GameState(scene, app.getSpriteLoader()));
            }
        }
    }
}
