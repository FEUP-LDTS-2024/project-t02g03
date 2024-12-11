package jumpking.states;

import com.sun.tools.javac.Main;
import jumpking.control.Controller;
import jumpking.control.ItemController;
import jumpking.control.MainMenuController;
import jumpking.control.MenuController;
import jumpking.model.game.scene.Scene;
import jumpking.model.menu.Item;
import jumpking.model.menu.MainMenu;
import jumpking.view.SpriteLoader;
import jumpking.view.ViewProvider;
import jumpking.view.screens.MenuViewer;
import jumpking.view.screens.ScreenViewer;

import java.io.IOException;

public class MainMenuState extends State<MainMenu> {

    public MainMenuState(MainMenu model, SpriteLoader spriteLoader)throws IOException {
        super(model, spriteLoader);
    }

    @Override
    protected Controller<MainMenu> createController() {
        return new MainMenuController(getModel(), new ItemController(getModel()));
    }

    @Override
    protected ScreenViewer<MainMenu> createScreenViewer(ViewProvider viewProvider) {
        return new MenuViewer<>(getModel(),viewProvider);
    }
}
