import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import jumpking.gui.GUI;
import jumpking.gui.LanternaGUI;
import jumpking.gui.ScreenCreator;
import jumpking.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
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
        gui = new LanternaGUI(screenCreator,"test");
    }

    @Test
    public void drawImage(){
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
    public void clear() {
        screen = mock(Screen.class);
        screen.clear();
        verify(screen).clear();
    }

    @Test
    public void refresh() throws IOException {
        screen = mock(Screen.class);
        screen.refresh();
        verify(screen).refresh();
    }

    @Test
    public void getNextAction() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);

        when(screen.readInput()).thenReturn(keyStroke);

        when(screen.readInput()).thenReturn(keyStroke);
        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowLeft);
        assertEquals(GUI.Act.LEFT, gui.getNextAction());

        when(screen.readInput()).thenReturn(keyStroke);
        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowRight);
        assertEquals(GUI.Act.RIGHT, gui.getNextAction());

        when(keyStroke.getKeyType()).thenReturn(KeyType.Character);
        when(keyStroke.getCharacter()).thenReturn('q');
        assertEquals(GUI.Act.QUIT, gui.getNextAction());

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
}
