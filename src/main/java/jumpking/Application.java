package jumpking;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Application {

    private Screen screen;
    private Boolean running = true;

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

        screen.clear();
        screen.refresh();
    }

    public static void main(String[] args) throws Exception {
        Application app = new Application();
        app.run();
    }

    public void run() throws IOException {
        while (running) {
            draw();
        }
        screen.close();
    }

    private void draw()  throws IOException  {
        screen.clear();
        screen.refresh();
    }
}
