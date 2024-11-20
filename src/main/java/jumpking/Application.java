package jumpking;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import jumpking.gui.LanternaGUI;
import jumpking.gui.LanternaScreenCreator;
import jumpking.gui.ScreenCreator;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Application {

    public static final int PIXEL_WIDTH = 333;
    public static final int PIXEL_HEIGHT = 250;
    private final LanternaGUI gui;

    private Boolean running = true;

    public Application() throws Exception {
        ScreenCreator screenCreator = new LanternaScreenCreator(
                new DefaultTerminalFactory(),
                new TerminalSize(PIXEL_WIDTH, PIXEL_HEIGHT)
        );
        this.gui = new LanternaGUI(screenCreator, "Jump King");
    }

    public static void main(String[] args) throws Exception {
        Application app = new Application();
        app.run();
    }

    public void run() throws IOException {
        while (running) {
            gui.clear();
            gui.refresh();
        }
        gui.close();
    }

}
