package jumpking.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import jumpking.model.Position;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class LanternaGUI implements GUI {

    private final ScreenCreator screenCreator;
    private final String title;
    private Screen screen;

    public LanternaGUI(ScreenCreator screenCreator, String title) throws IOException, URISyntaxException, FontFormatException {
        this.screenCreator = screenCreator;
        this.title = title;
        this.screen = screenCreator.createScreen(title, null);
        this.screen.startScreen();
    }

    @Override
    public void drawPixel(Position position, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);
        tg.setCharacter(position.getX(), position.getY(), ' ');
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

}
