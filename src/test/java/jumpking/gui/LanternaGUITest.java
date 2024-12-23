package jumpking.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import jumpking.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LanternaGUITest {
    private LanternaGUI gui;
    private TextGraphics tg;
    private ScreenCreator screenCreator;
    private Screen screen;

    @BeforeEach
    public void setUp() throws IOException, URISyntaxException, FontFormatException {
        screenCreator = mock(ScreenCreator.class);
        screen = mock(Screen.class);
        tg = mock(TextGraphics.class);
        when(screenCreator.createScreen(anyString(), any())).thenReturn(screen);
        when(screen.newTextGraphics()).thenReturn(tg);
        gui = new LanternaGUI(screenCreator, "test");
    }

    @Test
    public void drawImage() {
        BasicTextImage image = mock(BasicTextImage.class);
        gui.drawImage(image);
        verify(tg).drawImage(new TerminalPosition(0, 0), image);
    }

    @Test
    public void drawPixel() {
        Position position = new Position(0, 0);
        TextColor color = TextColor.ANSI.GREEN;
        gui.drawPixel(position, color);
        verify(tg).setBackgroundColor(color);
        verify(tg).setCharacter(position.getX(), position.getY(), ' ');
    }

    @Test
    public void drawTextImage() {
        Position position = new Position(0, 0);
        String[] image = {"line1", "line2"};
        TextColor color = TextColor.ANSI.GREEN;
        gui.drawTextImage(position, image, color, false);
        verify(tg, times(2)).putString(anyInt(), anyInt(), anyString());
    }

    @Test
    public void drawLineTextImage() {
        Position position = new Position(0, 0);
        String imageLine = "line";
        TextColor color = TextColor.ANSI.GREEN;
        gui.drawLineTextImage(position, imageLine, color, false);
        verify(tg).putString(position.getX(), position.getY(), imageLine);
    }

    @Test
    public void clear() {
        gui.clear();
        verify(screen).clear();
    }

    @Test
    public void refresh() throws IOException {
        gui.refresh();
        verify(screen).refresh();
    }

    @Test
    public void getNextAction() {
        assertEquals(GUI.Act.NONE, gui.getNextAction());
    }

    @Test
    public void testClose() throws IOException {
        gui.close();
        verify(screen).close();
    }

    @Test
    public void testDraw() throws IOException {
        gui.draw();
        verify(screen).refresh();
    }

    @Test
    public void testKeyPressedActions() {
        simulateKeyPress(KeyEvent.VK_LEFT);
        assertEquals(GUI.Act.LEFT, gui.getNextAction());

        simulateKeyPress(KeyEvent.VK_RIGHT);
        assertEquals(GUI.Act.RIGHT, gui.getNextAction());

        simulateKeyPress(KeyEvent.VK_UP);
        assertEquals(GUI.Act.UP, gui.getNextAction());

        simulateKeyPress(KeyEvent.VK_DOWN);
        assertEquals(GUI.Act.DOWN, gui.getNextAction());

        simulateKeyPress(KeyEvent.VK_ENTER);
        assertEquals(GUI.Act.SELECT, gui.getNextAction());

        simulateKeyPress(KeyEvent.VK_Q);
        assertEquals(GUI.Act.QUIT, gui.getNextAction());

        simulateKeyPress(KeyEvent.VK_P);
        assertEquals(GUI.Act.PAUSE, gui.getNextAction());

        simulateKeyPress(KeyEvent.VK_UNDEFINED);
        assertEquals(GUI.Act.NONE, gui.getNextAction());
    }

    private void simulateKeyPress(int keyCode) {
        KeyEvent keyEvent = new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
        gui.getKeyAdapter().keyPressed(keyEvent);
    }
}