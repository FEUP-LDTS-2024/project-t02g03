package jumpking.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class LanternaScreenCreator implements ScreenCreator {

    private final DefaultTerminalFactory terminalFactory;

    public LanternaScreenCreator(DefaultTerminalFactory terminalFactory, TerminalSize terminalSize) {
        this.terminalFactory = terminalFactory;
        terminalFactory.setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
    }

    @Override
    public Screen createScreen(String title, KeyListener keyListener) throws IOException, URISyntaxException, FontFormatException {
        int fontSize = 3;
        AWTTerminalFontConfiguration fontConfig = loadFont(fontSize);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        TerminalScreen screen = terminalFactory.createScreen();
        AWTTerminalFrame terminal = (AWTTerminalFrame) screen.getTerminal();
        terminal.getComponent(0).addKeyListener(keyListener);
        terminal.setTitle(title);
        return screen;
    }

    private AWTTerminalFontConfiguration loadFont(int fontSize) throws URISyntaxException, IOException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource("fonts/Square.ttf");
        File fontFile = new File(Objects.requireNonNull(resource).toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, fontSize);
        return AWTTerminalFontConfiguration.newInstance(font);
    }
}
