package jumpking.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import jumpking.model.Position;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static java.awt.event.KeyEvent.*;

public class LanternaGUI implements GUI {

    private final Screen screen;
    private static final List<Integer> SPAM_KEYS = Arrays.asList(VK_LEFT, VK_RIGHT);
    private KeyEvent priorityKeyPressed;
    private KeyEvent keyPressed;

    public LanternaGUI(ScreenCreator screenCreator, String title) throws IOException, URISyntaxException, FontFormatException {
        KeyAdapter keyAdapter = createKeyAdapter();
        this.screen = screenCreator.createScreen(title, keyAdapter);
        this.screen.startScreen();
        this.keyPressed = null;
        this.priorityKeyPressed = null;
    }

    private KeyAdapter createKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (SPAM_KEYS.contains(e.getKeyCode()))
                    keyPressed = priorityKeyPressed = e;
                else
                    keyPressed = e;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (SPAM_KEYS.contains(e.getKeyCode()))
                    keyPressed = priorityKeyPressed = null;
                else
                    keyPressed = priorityKeyPressed;
            }
        };
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
    public void drawTextImage(Position position, String[] image, TextColor color,boolean isCredits) {
        int y = position.getY();
        for (String imageLine : image) {
            drawLineTextImage(new Position(position.getX(), y), imageLine,color,isCredits);
            y++;
        }
    }

    @Override
    public void drawLineTextImage(Position position, String imageLine, TextColor color, boolean isCredits) {
        TextGraphics tg = screen.newTextGraphics();
        if(!isCredits){
            tg.putString(position.getX(), position.getY(), imageLine);
        }
        int x = position.getX();
        int y = position.getY();
        for (int i = 0; i < imageLine.length(); i++) {
            char character = imageLine.charAt(i);
            if (character != ' ') {
                tg.setForegroundColor(color);
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
        if (keyPressed == null){
            return Act.NONE;
        }
        int keyCode = keyPressed.getKeyCode();
        keyPressed = priorityKeyPressed;
        return switch (keyCode) {
            case VK_LEFT -> Act.LEFT;
            case VK_RIGHT -> Act.RIGHT;
            case VK_UP -> Act.UP;
            case VK_DOWN -> Act.DOWN;
            case VK_ENTER -> Act.SELECT;
            case VK_Q -> Act.QUIT;
            case VK_P -> Act.PAUSE;
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

    public KeyListener getKeyAdapter() {
        return createKeyAdapter();
    }
}
