package jumpking.model.credits;

import com.googlecode.lanterna.graphics.BasicTextImage;
import jumpking.model.BackgroundImageLoader;
import jumpking.model.game.elements.King;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.UncheckedIOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreditsTest {
    private Credits credits;
    private King king;
    private BasicTextImage backgroundImage;

    @BeforeEach
    void setUp() throws IOException {
        king = mock(King.class);
        BackgroundImageLoader loader = mock(BackgroundImageLoader.class);
        backgroundImage = mock(BasicTextImage.class);
        when(loader.loadBackgroundImage("backgrounds/credits.png")).thenReturn(backgroundImage);
        when(king.getJumps()).thenReturn(10);
        when(king.getStartTime()).thenReturn(System.currentTimeMillis() - 61000);

        credits = new Credits(king);
    }

    @Test
    void testGetJumps() {
        assertEquals(10, credits.getJumps());
    }

    @Test
    void testGetSeconds() {
        assertEquals(1, credits.getSeconds());
    }

    @Test
    void testGetMinutes() {
        assertEquals(1, credits.getMinutes());
    }

    @Test
    void testGetBackgroundImage() {
        assertNotNull(credits.getBackgroundImage(), "Background image should not be null");
    }
}
