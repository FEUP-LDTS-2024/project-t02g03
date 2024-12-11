package jumpking;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.sun.tools.javac.Main;
import jumpking.control.KingController;
import jumpking.control.SceneController;
import jumpking.gui.GUI;
import jumpking.gui.LanternaGUI;
import jumpking.gui.LanternaScreenCreator;
import jumpking.gui.ScreenCreator;
import jumpking.model.game.elements.King;
import jumpking.model.game.scene.Scene;
import jumpking.model.game.scene.SceneBuilder;
import jumpking.model.menu.MainMenu;
import jumpking.states.GameState;
import jumpking.states.MainMenuState;
import jumpking.states.State;
import jumpking.view.screens.GameViewer;
import jumpking.view.IngameSpriteLoader;
import jumpking.view.SpriteLoader;
import jumpking.view.ViewProvider;
import jumpking.view.screens.MenuViewer;

import java.io.IOException;

public class Application {

    public static final int PIXEL_WIDTH = 333;
    public static final int PIXEL_HEIGHT = 250;
    private final LanternaGUI gui;
    //private final Scene scene;
    private State<?> state;
    //private final GameViewer gameViewer;
    private final SpriteLoader spriteLoader;
    //private final SceneController sceneController;
    private final MenuViewer menuViewer;

    private Boolean running = true;

    public Application() throws Exception {
        ScreenCreator screenCreator = new LanternaScreenCreator(
                new DefaultTerminalFactory(),
                new TerminalSize(PIXEL_WIDTH, PIXEL_HEIGHT)
        );
        this.gui = new LanternaGUI(screenCreator, "Jump King"); //Esta duas criam screen
        this.spriteLoader = new IngameSpriteLoader();
        King king = new King(168,228); // Create a King instance
        //this.scene = new SceneBuilder(0).buildScene(king); // desenham a tela em caracteres
        ViewProvider viewProvider = new ViewProvider(spriteLoader); //desenham na tela as imagens (hero)
        //this.gameViewer = new GameViewer(scene, viewProvider);
        this.menuViewer = new MenuViewer<>(new MainMenu(),viewProvider);
        //KingController kingController = new KingController(scene); //continuar aqui
        //this.sceneController = new SceneController(scene, kingController);

    }

    public static void main(String[] args) throws Exception {
        Application app = new Application();
        app.run();
    }

    public void run() throws IOException {

        state = new MainMenuState(new MainMenu(), spriteLoader);
        long time = System.currentTimeMillis();
        while (running) {
            menuViewer.draw(gui,time);
            gui.refresh();
            GUI.Act act = gui.getNextAction();
            time = System.currentTimeMillis();
        }
//        while (running) {
//            gameViewer.draw(gui, time);
//            gui.refresh();
//            GUI.Act act = gui.getNextAction(); // Get the next action from the GUI
//            sceneController.step(this, act, time); // Call the step method of SceneController
//            time = System.currentTimeMillis();
//        }
        gui.close();
    }

    public Scene getModel() {
        return ((GameState) state).getModel();
    }

    public void setState(State<?> state) {
        this.state = state;
    }

    public SpriteLoader getSpriteLoader() {
        return spriteLoader;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public GUI getGui() {
        return gui;
    }
}
