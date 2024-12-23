package jumpking.model.credits;

import com.googlecode.lanterna.graphics.BasicTextImage;
import jumpking.model.BackgroundImageLoader;
import jumpking.model.game.elements.King;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class CreditsTest {
    private Credits credits;
    private King king;
    private BackgroundImageLoader backgroundImageLoader;
    private BasicTextImage backgroundImage;

    @BeforeEach
    void setUp() throws IOException {
        king = mock(King.class);
        backgroundImageLoader = mock(BackgroundImageLoader.class);
        backgroundImage = mock(BasicTextImage.class);
        when(king.getJumps()).thenReturn(10);
        when(king.getStartTime()).thenReturn(System.currentTimeMillis() - 61000);
        when(backgroundImageLoader.loadBackgroundImage("backgrounds/credits.png")).thenReturn(backgroundImage);
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

//    @Test
//    void testGetBackgroundImage() {
//        assertEquals(backgroundImage, credits.getBackgroundImage());
//    }


}