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

    //renomear
    @Override
    public void drawTextImage(Position position, String[] image, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        int y = position.getY();
        for (String imageline : image) {
            drawLine(new Position(position.getX(), y), imageline,color);
            y++;
        }
    }

    @Override
    public void drawLine(Position position, String imageline, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.putString(position.getX(), position.getY(), imageline);
        int x = position.getX();
        int y = position.getY();
        for (int i = 0; i < imageline.length(); i++) {
            char character = imageline.charAt(i);
            if (character != ' ') {
                tg.setBackgroundColor(color);
                tg.setCharacter(new TerminalPosition(x,y),character);
            }
            x++;
        }
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
            keyPressed = screen.pollInput();
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
            case Enter -> Act.SELECT;
            case EOF -> Act.QUIT;
            case Character -> {
                if (keyPressed.getCharacter() == 'q') {
                    yield Act.QUIT;
                }else if(keyPressed.getCharacter()== 'p'){
                    yield Act.PAUSE;
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
