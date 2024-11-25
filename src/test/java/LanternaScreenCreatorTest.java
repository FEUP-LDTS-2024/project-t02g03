import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import jumpking.gui.LanternaScreenCreator;
import jumpking.gui.ScreenCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LanternaScreenCreatorTest {

    private DefaultTerminalFactory terminalFactory;
    private ScreenCreator screenCreator;


    @BeforeEach
    public void setUp() {
        terminalFactory = Mockito.mock(DefaultTerminalFactory.class);
        screenCreator = mock(LanternaScreenCreator.class);
    }

    @Test
    public void testCreateScreen() throws IOException, URISyntaxException, FontFormatException {
        AWTTerminalFontConfiguration fontConfig = mock(AWTTerminalFontConfiguration.class);
        TerminalScreen screen = mock(TerminalScreen.class);
        AWTTerminalFrame terminal = mock(AWTTerminalFrame.class);
        KeyListener keyListener = mock(KeyListener.class);

        when(terminalFactory.createScreen()).thenReturn(screen);
        when(screen.getTerminal()).thenReturn(terminal);
        doNothing().when(terminal).setTitle(anyString());
        doNothing().when(terminal).getComponent(0).addKeyListener(keyListener);

        LanternaScreenCreator spyScreenCreator = spy(screenCreator);
        doReturn(fontConfig).when(spyScreenCreator);

        Screen createdScreen = spyScreenCreator.createScreen("", keyListener);

        assertNotNull(createdScreen);
        verify(terminalFactory).setTerminalEmulatorFontConfiguration(fontConfig);
        verify(terminal).setTitle("");
        verify(terminal.getComponent(0)).addKeyListener(keyListener);
    }

}
