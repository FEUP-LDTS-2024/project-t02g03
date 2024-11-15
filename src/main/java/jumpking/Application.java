package jumpking;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import jumpking.model.game.ScreenRefresher;
import jumpking.model.game.scene.Scene;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Application implements ScreenRefresher {

    private Screen screen;
    private Scene scene;
    private boolean running = true;

    public Application() throws Exception {
        // Load the custom font
        URL resource = getClass().getClassLoader().getResource("fonts/Square.ttf");
        File fontFile = new File(resource.toURI());
        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font newfont = font.deriveFont(Font.PLAIN, 5);

        AWTTerminalFontConfiguration cfg = AWTTerminalFontConfiguration.newInstance(newfont);

        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(font);

        Terminal terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(111, 74))
                .setTerminalEmulatorFontConfiguration(cfg)
                .setForceAWTOverSwing(true)
                .createTerminal();

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary

        TerminalSize terminalSize = screen.getTerminalSize();
        //System.out.println("Terminal Size: " + terminalSize.getColumns() + "x" + terminalSize.getRows());
        scene = new Scene(terminalSize.getColumns(), terminalSize.getRows(), this);

        screen.clear();
        screen.refresh();
    }

    public static void main(String[] args) {
        try {
            Application app = new Application();
            app.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() throws IOException {
        handleFalling();
        while (running) {
            draw();
            KeyStroke key = screen.readInput();
            try {
                processKey(key);
                handleFalling();
            } catch (IOException e) {
                running = false;
            }
        }
        screen.close();
    }

    private void draw()  throws IOException  {
        screen.clear();
        scene.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(KeyStroke key) throws IOException {
        scene.processKey(key);
    }

    private void handleFalling() throws IOException {
        while (scene.isHeroFalling()) {
            scene.moveHeroDown();
            try {
                Thread.sleep(10); // Adjust the speed of falling as needed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            draw();
        }
    }

    @Override
    public void drawAndRefresh() throws IOException {
        draw();
    }
}
