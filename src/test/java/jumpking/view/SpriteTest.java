package jumpking.view;

import com.googlecode.lanterna.TextColor;
import jumpking.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import jumpking.gui.GUI;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class SpriteTest {
    private GUI gui;

    @BeforeEach
    public void setUp() {
        this.gui = Mockito.mock(GUI.class);
    }

    @Test
    public void drawTest() throws IOException {
        Sprite sprite1 = new Sprite("sprites/king-running-1.png");
        Position position1 = new Position(0, 0);
        sprite1.draw(gui, position1);
        verify(gui, times(1)).drawPixel(position1.getX(), position1.getY(), new TextColor.RGB(255, 0, 0));
        verify(gui, times(1)).drawPixel(position1.getX() + 1, position1.getY(), new TextColor.RGB(0, 255, 0));

        Sprite sprite2 = new Sprite("sprites/test-sprite-transparent.png");  // Image with transparent pixels
        Position position2 = new Position(10, 10);
        sprite2.draw(gui, position2);
        verify(gui, times(0)).drawPixel(position2.getX() + 1, position2.getY(), any(TextColor.class));
    }
}
