package jumpking.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class LanternaScreenCreatorTest {

    private DefaultTerminalFactory terminalFactory;
    private TerminalSize terminalSize;
    private LanternaScreenCreator screenCreator;
    private TerminalScreen terminalScreen;
    private AWTTerminalFrame terminalFrame;
    private KeyListener keyListener;
    private Component component;

    @BeforeEach
    public void setUp() {
        terminalFactory = mock(DefaultTerminalFactory.class);
        terminalSize = mock(TerminalSize.class);
        screenCreator = new LanternaScreenCreator(terminalFactory, terminalSize);
        terminalScreen = mock(TerminalScreen.class);
        terminalFrame = mock(AWTTerminalFrame.class);
        keyListener = mock(KeyListener.class);
        component = mock(Component.class);
    }

    @Test
    public void testCreateScreen() throws IOException, URISyntaxException, FontFormatException {
        when(terminalFactory.createScreen()).thenReturn(terminalScreen);
        when(terminalScreen.getTerminal()).thenReturn(terminalFrame);
        when(terminalFrame.getComponent(0)).thenReturn(component);

        Screen screen = screenCreator.createScreen("Test Title", keyListener);

        assertNotNull(screen);
        verify(terminalFactory).setInitialTerminalSize(terminalSize);
        verify(terminalFactory).setForceAWTOverSwing(true);
        verify(terminalFactory).setTerminalEmulatorFontConfiguration(any(AWTTerminalFontConfiguration.class));
        verify(terminalFactory).createScreen();
        verify(terminalFrame).getComponent(0);
        verify(terminalFrame).setTitle("Test Title");
        verify(component).addKeyListener(keyListener);
    }
}