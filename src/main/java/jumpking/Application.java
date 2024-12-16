package jumpking;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import jumpking.gui.GUI;
import jumpking.gui.LanternaGUI;
import jumpking.gui.LanternaScreenCreator;
import jumpking.gui.ScreenCreator;
import jumpking.model.menu.MainMenu;
import jumpking.states.MainMenuState;
import jumpking.states.State;
import jumpking.view.IngameSpriteLoader;
import jumpking.view.SpriteLoader;
import jumpking.sound.BackgroundSoundPlayer;
import jumpking.sound.SoundLoader;

import javax.sound.sampled.AudioSystem;
import java.util.Objects;

public class Application {

    public static final int PIXEL_WIDTH = 333;
    public static final int PIXEL_HEIGHT = 250;

    private final LanternaGUI gui;
    private final SpriteLoader spriteLoader;
    private final BackgroundSoundPlayer backgroundSoundPlayer;
    private State<?> state;
    private Boolean running = true;

    public Application() throws Exception {
        ScreenCreator screenCreator = new LanternaScreenCreator(
                new DefaultTerminalFactory(),
                new TerminalSize(PIXEL_WIDTH, PIXEL_HEIGHT)
        );
        this.gui = new LanternaGUI(screenCreator, "Jump King");
        this.spriteLoader = new IngameSpriteLoader();
        this.state = new MainMenuState(new MainMenu(), spriteLoader);
        this.backgroundSoundPlayer = new BackgroundSoundPlayer(
                new SoundLoader().loadSound(
                        AudioSystem.getAudioInputStream(
                                Objects.requireNonNull(
                                        getClass().getClassLoader().getResource("sounds/jump-king-theme.wav")
                                )
                        ),
                        AudioSystem.getClip()
                )
        );
    }

    public static void main(String[] args) throws Exception {
        Application app = new Application();
        app.run();
    }

    public void run() throws Exception {
        long time = System.currentTimeMillis();
        backgroundSoundPlayer.start();
        while (running) {
            state.step(this, gui, time);
            time = System.currentTimeMillis();
        }
        backgroundSoundPlayer.stop();
        gui.close();
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
