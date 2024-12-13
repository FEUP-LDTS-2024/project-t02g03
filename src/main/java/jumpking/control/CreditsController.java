package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.credits.Credits;
import jumpking.model.menu.MainMenu;
import jumpking.states.MainMenuState;

//verificar com timeless odysey
public class CreditsController<T extends Credits> extends Controller<Credits> {


    public CreditsController(Credits credits) {
        super(credits);
    }

    @Override
    public void step(Application app, GUI.Act act, long time) throws Exception {
        if(act== GUI.Act.QUIT){
            app.setState(new MainMenuState(new MainMenu(),app.getSpriteLoader()));
        }
    }
}
