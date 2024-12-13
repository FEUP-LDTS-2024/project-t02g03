package jumpking.states;

import jumpking.control.Controller;
import jumpking.control.CreditsController;
import jumpking.control.ItemController;
import jumpking.control.PauseMenuController;
import jumpking.model.credits.Credits;
import jumpking.view.SpriteLoader;
import jumpking.view.ViewProvider;
import jumpking.view.screens.ScreenViewer;

import java.io.IOException;

public class CreditsState extends State<Credits> {

    public CreditsState(Credits model, SpriteLoader spriteLoader) throws IOException {
        super(model, spriteLoader);

    }

    @Override
    protected Controller<Credits> createController() {
        return new CreditsController<Credits>(getModel());
    }

    @Override
    protected ScreenViewer<Credits> createScreenViewer(ViewProvider viewProvider) {
        return null;
    }
}
