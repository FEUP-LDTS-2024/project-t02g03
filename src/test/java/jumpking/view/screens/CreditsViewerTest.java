package jumpking.view.screens;
import com.googlecode.lanterna.TextColor;
import jumpking.gui.GUI;
import jumpking.model.Position;
import jumpking.model.credits.Credits;
import jumpking.view.ViewProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CreditsViewerTest {
    private CreditsViewer<Credits> creditsViewer;
    private Credits credits;
    private ViewProvider viewProvider;
    private GUI gui;

    @BeforeEach
    void setUp() {
        credits = mock(Credits.class);
        viewProvider = mock(ViewProvider.class);
        gui = mock(GUI.class);
        creditsViewer = spy(new CreditsViewer<>(credits, viewProvider));
        when(credits.getJumps()).thenReturn(1234);
        when(credits.getMinutes()).thenReturn(12);
        when(credits.getSeconds()).thenReturn(34);
    }
    @Test
    void testDraw() throws IOException {
        creditsViewer.draw(gui, 0L);
        verify(gui, times(1)).clear();
        verify(creditsViewer, times(1)).drawBackground(gui);
        verify(gui, times(1)).refresh();
        verify(gui, times(1)).drawTextImage(eq(new Position(47, 10)), any(String[].class), eq(TextColor.Factory.fromString("#000000")), eq(true));
        verify(gui, times(1)).drawTextImage(eq(new Position(230, 225)), any(String[].class), eq(TextColor.Factory.fromString("#000000")), eq(true));
        verify(gui, times(1)).drawTextImage(eq(new Position(256, 117)), any(String[].class), eq(TextColor.Factory.fromString("#000000")), eq(true));
        verify(gui, times(1)).drawTextImage(eq(new Position(262, 148)), any(String[].class), eq(TextColor.Factory.fromString("#000000")), eq(true));
    }

    @Test
    public void testGetDigitsJumps() {
        List<Integer> expected = Arrays.asList(1, 2, 3, 4);
        List<Integer> actual = creditsViewer.getDigitsJumps();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDigitsTime() {
        List<Integer> expected = Arrays.asList(1, 2, 10, 3, 4);
        List<Integer> actual = creditsViewer.getDigitsTime();

        assertEquals(expected, actual, "Digits for time are incorrect");
    }

    @Test
    void testDrawMessages(){
        creditsViewer.drawMessages(gui);
        verify(gui, times(1)).drawTextImage(any(Position.class), any(String[].class), any(TextColor.class), eq(true));
    }

    @Test
    void testDrawNames(){
        creditsViewer.drawNames(gui);
        verify(gui, times(1)).drawTextImage(any(Position.class), any(String[].class), any(TextColor.class), eq(true));
    }

    @Test
    void testDrawStatistics(){
        creditsViewer.drawStatistics(gui);
        verify(gui, atLeastOnce()).drawTextImage(any(Position.class), any(String[].class), any(TextColor.class), eq(true));
    }

    @Test
    void testDrawDigit(){
        Position position = new Position(0, 0);
        creditsViewer.drawDigit(1, position, gui);
        verify(gui, times(1)).drawTextImage(eq(position), any(String[].class), any(TextColor.class), eq(true));
    }
}
