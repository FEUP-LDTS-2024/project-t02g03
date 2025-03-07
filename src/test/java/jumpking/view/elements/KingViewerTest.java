package jumpking.view.elements;

import jumpking.gui.GUI;
import jumpking.model.game.elements.King;
import jumpking.view.Sprite;
import jumpking.view.SpriteLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class KingViewerTest {
    private KingViewer kingViewer;
    private SpriteLoader spriteLoader;
    private GUI gui;
    private King king;

    @BeforeEach
    public void setUp() throws IOException {
        this.spriteLoader = Mockito.mock(SpriteLoader.class);
        this.gui = Mockito.mock(GUI.class);
        this.king = new King(20, 30);

        // Mocking the sprites
        Sprite idleSprite = Mockito.mock(Sprite.class);
        Sprite fallenSprite = Mockito.mock(Sprite.class);
        Sprite crouchingSprite = Mockito.mock(Sprite.class);
        Sprite jumpingSprite = Mockito.mock(Sprite.class);
        Sprite fallingSprite = Mockito.mock(Sprite.class);
        Sprite runningSprite1 = Mockito.mock(Sprite.class);
        Sprite runningSprite2 = Mockito.mock(Sprite.class);
        Sprite runningSprite3 = Mockito.mock(Sprite.class);
        Sprite reboundSprite = Mockito.mock(Sprite.class);

        // Setting up the sprite loader to return the mocked sprites
        Mockito.when(spriteLoader.getSprite("sprites/king-idle.png")).thenReturn(idleSprite);
        Mockito.when(spriteLoader.getSprite("sprites/king-fallen.png")).thenReturn(fallenSprite);
        Mockito.when(spriteLoader.getSprite("sprites/king-crouching.png")).thenReturn(crouchingSprite);
        Mockito.when(spriteLoader.getSprite("sprites/king-jumping.png")).thenReturn(jumpingSprite);
        Mockito.when(spriteLoader.getSprite("sprites/king-falling.png")).thenReturn(fallingSprite);
        Mockito.when(spriteLoader.getSprite("sprites/king-running-1.png")).thenReturn(runningSprite1);
        Mockito.when(spriteLoader.getSprite("sprites/king-running-2.png")).thenReturn(runningSprite2);
        Mockito.when(spriteLoader.getSprite("sprites/king-running-3.png")).thenReturn(runningSprite3);
        Mockito.when(spriteLoader.getSprite("sprites/king-rebound.png")).thenReturn(reboundSprite);

        this.kingViewer = new KingViewer(spriteLoader);
    }

    @Test
    public void testKingViewerInitialization() throws IOException {
        assertNotNull(kingViewer);
    }

    @Test
    public void drawIdleTest() throws IOException {
        long time = 1000;
        kingViewer.draw(king, gui, time);
        verify(spriteLoader.getSprite("sprites/king-idle.png"), times(1)).draw(gui, king.getPosition());
    }

    @Test
    public void testMockDraw() throws IOException {
        KingViewer mockKingViewer = Mockito.mock(KingViewer.class);
        long time = 1000;
        mockKingViewer.draw(king, gui, time);
        verify(mockKingViewer, times(1)).draw(king, gui, time);
    }

    @Test
    public void testDrawKingFlipped() throws IOException {
        king.setFacingRight(false);
        long time = 1000;
        kingViewer.draw(king, gui, time);
        verify(spriteLoader.getSprite("sprites/king-idle.png"), times(1)).drawFlipped(gui, king.getPosition());
    }

}