package jumpking.view;

import jumpking.view.IngameSpriteLoader;
import jumpking.view.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class IngameSpriteLoaderTest {
    private IngameSpriteLoader spriteLoader;

    @BeforeEach
    public void setUp() {
        spriteLoader = new IngameSpriteLoader();
    }

    @Test
    public void getSpriteTest() throws IOException {
        String filePath1 = "sprites/king-fallen.png";
        URL source1 = getClass().getClassLoader().getResource(filePath1);
        assertNotNull(source1);
        BufferedImage image1 = ImageIO.read(new File(source1.getFile()));

        String filePath2 = "sprites/king-crouching.png";
        URL source2 = getClass().getClassLoader().getResource(filePath2);
        assertNotNull(source2);
        BufferedImage image2 = ImageIO.read(new File(source2.getFile()));

        Sprite sprite1 = spriteLoader.getSprite(filePath1);
        assertTrue(compareImages(image1, sprite1.getSprite()));

        Sprite sprite2 = spriteLoader.getSprite(filePath2);
        assertTrue(compareImages(image2, sprite2.getSprite()));
    }

    private boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
        if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
            return false;
        }
        for (int y = 0; y < imgA.getHeight(); y++) {
            for (int x = 0; x < imgA.getWidth(); x++) {
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void getSpriteFromCacheTest() throws IOException {
        String filePath = "sprites/king-fallen.png";
        URL source = getClass().getClassLoader().getResource(filePath);
        assertNotNull(source);
        BufferedImage image = ImageIO.read(new File(source.getFile()));

        // Load the sprite for the first time
        Sprite sprite1 = spriteLoader.getSprite(filePath);
        assertTrue(compareImages(image, sprite1.getSprite()));

        // Load the sprite for the second time (should be from cache)
        Sprite sprite2 = spriteLoader.getSprite(filePath);
        assertTrue(compareImages(image, sprite2.getSprite()));

        // Ensure the same instance is returned from the cache
        assertSame(sprite1, sprite2);
    }
}