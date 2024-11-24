package jumpking.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import jumpking.model.Position;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class LanternaGUI implements GUI {

    private final ScreenCreator screenCreator;
    private final String title;
    private Screen screen;
    private KeyStroke keyPressed;

    public LanternaGUI(ScreenCreator screenCreator, String title) throws IOException, URISyntaxException, FontFormatException {
        this.screenCreator = screenCreator;
        this.title = title;
        this.screen = screenCreator.createScreen(title, null);
        this.screen.startScreen();
        this.keyPressed = null;
    }

    @Override
    public void drawImage(BasicTextImage image) {
        TextGraphics tg = screen.newTextGraphics();
        tg.drawImage(new TerminalPosition(0, 0), image);
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
    public Act getNextAction() {
        try {
            keyPressed = screen.readInput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (keyPressed == null){
            return Act.NONE;
        }
        KeyType keyType = keyPressed.getKeyType();
        return switch (keyType) {
            case ArrowLeft -> Act.LEFT;
            case ArrowUp -> Act.UP;
            case ArrowRight -> Act.RIGHT;
            case ArrowDown -> Act.DOWN;
            case Character -> {
                if (keyPressed.getCharacter() == 'q') {
                    yield Act.QUIT;
                } else if (keyPressed.getCharacter() == '\n') {
                    yield Act.SELECT;
                } else {
                    yield Act.NONE;
                }
            }
            default -> Act.NONE;
        };
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    @Override
    public void draw() throws IOException {
        screen.refresh();
    }

}
