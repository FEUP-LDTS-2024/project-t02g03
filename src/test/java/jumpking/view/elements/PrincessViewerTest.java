package jumpking.view.elements;

import jumpking.gui.GUI;
import jumpking.model.Position;
import jumpking.model.game.elements.Princess;
import jumpking.view.Sprite;
import jumpking.view.SpriteLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PrincessViewerTest {
    private PrincessViewer princessViewer;
    private SpriteLoader spriteLoader;
    private GUI gui;
    private Princess princess;

    @BeforeEach
    public void setUp() throws IOException {
        this.spriteLoader = Mockito.mock(SpriteLoader.class);
        this.gui = Mockito.mock(GUI.class);
        this.princess = new Princess(10, 20);

        // Mocking the sprite
        Sprite sprite = Mockito.mock(Sprite.class);
        Mockito.when(spriteLoader.getSprite("sprites/princess.png")).thenReturn(sprite);

        this.princessViewer = new PrincessViewer(spriteLoader);
    }

    @Test
    public void testPrincessViewerInitialization() throws IOException {
        assertNotNull(princessViewer);
    }

    @Test
    public void testDraw() throws IOException {
        long time = 1000;
        princessViewer.draw(princess, gui, time);
        verify(spriteLoader.getSprite("sprites/princess.png"), times(1)).draw(gui, princess.getPosition());
    }

    @Test
    public void testMockDraw()  {
        PrincessViewer mockPrincessViewer = Mockito.mock(PrincessViewer.class);
        long time = 1000;
        mockPrincessViewer.draw(princess, gui, time);
        verify(mockPrincessViewer, times(1)).draw(princess, gui, time);
    }

    @Test
    public void testDrawPrincessNull() throws IOException {
        Princess nullPrincess = null;
        long time = 1000;
        princessViewer.draw(nullPrincess, gui, time);
        verify(spriteLoader.getSprite("sprites/princess.png"), never()).draw(gui, null);
    }
}