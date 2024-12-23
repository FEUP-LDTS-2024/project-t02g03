package jumpking.model;

import com.googlecode.lanterna.graphics.BasicTextImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class BackgroundImageLoaderTest {

    private BackgroundImageLoader backgroundImageLoader;

    @BeforeEach
    public void setUp() {
        backgroundImageLoader = new BackgroundImageLoader();
    }

    @Test
    public void testLoadBackgroundImageSuccess() throws IOException {
        // Ensure the path is correct and the image file exists at this location
        BasicTextImage image = backgroundImageLoader.loadBackgroundImage("backgrounds/scene0.png");
        assertNotNull(image);
    }

    @Test
    public void testLoadBackgroundImageFailure() {
        Exception exception = assertThrows(IOException.class, () -> {
            backgroundImageLoader.loadBackgroundImage("invalidImagePath");
        });
        assertEquals("Background image not found", exception.getMessage());
    }
}