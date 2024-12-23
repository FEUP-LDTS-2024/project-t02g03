package jumpking.view;

import com.googlecode.lanterna.TextColor;
import jumpking.gui.GUI;
import jumpking.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class SpriteTest {
    private Sprite sprite;
    private GUI gui;

    @BeforeEach
    public void setUp() throws IOException {
        sprite = new Sprite("sprites/king-fallen.png");
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void testSpriteInitialization() throws IOException {
        assertNotNull(sprite.getSprite());
    }

    @Test
    public void testDraw() throws IOException {
        Position position = new Position(0, 0);
        sprite.draw(gui, position);
        // Verify that drawPixel is called with the correct parameters
        BufferedImage image = sprite.getSprite();
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int color = image.getRGB(i, j);
                int alpha = (color >> 24) & 0xff;
                if (alpha > 0) {
                    Position p = new Position(position.getX() + i, position.getY() + j - 14);
                    verify(gui).drawPixel(p, TextColor.Factory.fromString(String.format("#%06X", (0xFFFFFF & color))));
                }
            }
        }
    }

    @Test
    public void testDrawFlipped() throws IOException {
        Position position = new Position(0, 0);
        sprite.drawFlipped(gui, position);
        // Verify that drawPixel is called with the correct parameters
        BufferedImage image = sprite.getSprite();
        int width = image.getWidth();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int color = image.getRGB(i, j);
                int alpha = (color >> 24) & 0xff;
                if (alpha > 0) {
                    Position p = new Position(position.getX() + (width - 1 - i), position.getY() + j - 14);
                    verify(gui).drawPixel(p, TextColor.Factory.fromString(String.format("#%06X", (0xFFFFFF & color))));
                }
            }
        }
    }

    @Test
    public void testMockDraw() throws IOException {
        Sprite mockSprite = Mockito.mock(Sprite.class);
        Position position = new Position(0, 0);
        mockSprite.draw(gui, position);
        verify(mockSprite, times(1)).draw(gui, position);
    }

    @Test
    public void testMockDrawFlipped() throws IOException {
        Sprite mockSprite = Mockito.mock(Sprite.class);
        Position position = new Position(0, 0);
        mockSprite.drawFlipped(gui, position);
        verify(mockSprite, times(1)).drawFlipped(gui, position);
    }
}