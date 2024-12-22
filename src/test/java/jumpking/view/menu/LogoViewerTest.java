package jumpking.view.menu;

import jumpking.gui.GUI;
import jumpking.model.Position;
import jumpking.view.Sprite;
import jumpking.view.SpriteLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class LogoViewerTest {
    private LogoViewer logoViewer;
    private SpriteLoader spriteLoader;
    private Sprite sprite;
    private GUI gui;

    @BeforeEach
    void setUp() throws IOException {
        spriteLoader = mock(SpriteLoader.class);
        sprite = mock(Sprite.class);
        gui = mock(GUI.class);
        when(spriteLoader.getSprite("sprites/logo.png")).thenReturn(sprite);
        logoViewer = new LogoViewer(spriteLoader);
    }

    @Test
    void testDraw() {
        int x = 10;
        int y = 20;
        logoViewer.draw(gui, x, y);
        verify(sprite, times(1)).draw(gui, new Position(x, y));
    }

    @Test
    public void testSpriteIsLoadedCorrectly() throws IOException {
        verify(spriteLoader, times(1)).getSprite("sprites/logo.png");
    }
}
