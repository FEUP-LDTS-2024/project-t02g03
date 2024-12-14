package jumpking;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import jumpking.control.KingController;
import jumpking.control.SceneController;
import jumpking.gui.GUI;
import jumpking.gui.LanternaGUI;
import jumpking.gui.LanternaScreenCreator;
import jumpking.gui.ScreenCreator;
import jumpking.model.game.elements.King;
import jumpking.model.game.scene.Scene;
import jumpking.model.game.scene.SceneBuilder;
import jumpking.states.GameState;
import jumpking.states.State;
import jumpking.view.screens.GameViewer;
import jumpking.view.IngameSpriteLoader;
import jumpking.view.SpriteLoader;
import jumpking.view.ViewProvider;
import jumpking.sound.BackgroundSoundPlayer;
import jumpking.sound.SoundLoader;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import java.io.IOException;
import java.util.Objects;

public class Application {

    public static final int PIXEL_WIDTH = 333;
    public static final int PIXEL_HEIGHT = 250;
    private final LanternaGUI gui;
    private final Scene scene;
    private State<?> state;
    private final GameViewer gameViewer;
    private final SpriteLoader spriteLoader;
    private final SceneController sceneController;
    private final BackgroundSoundPlayer backgroundSoundPlayer;

    private Boolean running = true;

    public Application() throws Exception {
        ScreenCreator screenCreator = new LanternaScreenCreator(
                new DefaultTerminalFactory(),
                new TerminalSize(PIXEL_WIDTH, PIXEL_HEIGHT)
        );
        this.gui = new LanternaGUI(screenCreator, "Jump King");
        this.spriteLoader = new IngameSpriteLoader();
        King king = new King(168,228); // Create a King instance
        this.scene = new SceneBuilder(0).buildScene(king);
        ViewProvider viewProvider = new ViewProvider(spriteLoader);
        this.gameViewer = new GameViewer(scene, viewProvider);
        KingController kingController = new KingController(scene);
        this.sceneController = new SceneController(scene, kingController);
        this.backgroundSoundPlayer = new BackgroundSoundPlayer(new SoundLoader().loadSound(AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("sounds/demo.wav"))), AudioSystem.getClip()));

        FloatControl gainControl = (FloatControl) backgroundSoundPlayer.getSound().getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-15f);

    }

    public static void main(String[] args) throws Exception {
        Application app = new Application();
        app.run();
    }

    public void run() throws IOException {
        long time = System.currentTimeMillis();
        backgroundSoundPlayer.start();
        while (running) {
            gameViewer.draw(gui, time);
            gui.refresh();
            GUI.Act act = gui.getNextAction(); // Get the next action from the GUI
            sceneController.step(this, act, time); // Call the step method of SceneController
            time = System.currentTimeMillis();
        }
        backgroundSoundPlayer.stop();
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
