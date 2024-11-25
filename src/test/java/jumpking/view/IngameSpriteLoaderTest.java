package jumpking.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class IngameSpriteLoaderTest {
    private IngameSpriteLoader spriteLoader;

    @BeforeEach
    public void setUp() {
        this.spriteLoader = new IngameSpriteLoader();
    }

    @Test
    public void getSpriteTest() throws IOException {
        String filePath1 = "sprites/test-sprite1.png";
        URL source1 = getClass().getClassLoader().getResource("sprites/test-sprite1.png");
        assertNotNull(source1);
        BufferedImage image1 = ImageIO.read(new File(source1.getFile()));


        String filePath2 = "sprites/test-sprite2.png";
        URL source2 = getClass().getClassLoader().getResource("sprites/test-sprite1.png");
        assertNotNull(source2);
        BufferedImage image2 = ImageIO.read(new File(source1.getFile()));

        Sprite sprite1 = spriteLoader.getSprite(filePath1);
        assertEquals(image1, sprite1.getSprite());

        Sprite sprite2 = spriteLoader.getSprite(filePath2);
        assertEquals(image2, sprite2.getSprite());

        Sprite sprite3 = spriteLoader.getSprite(filePath1);
        assertSame(sprite1, sprite3);
    }
}
