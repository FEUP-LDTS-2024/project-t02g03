package jumpking.view.elements;

import jumpking.gui.GUI;
import jumpking.model.game.elements.King;
import jumpking.view.Sprite;
import jumpking.view.SpriteLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

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
        this.kingViewer = new KingViewer(spriteLoader);
    }

    @Test
    public void drawTest() throws IOException {
        Sprite fallingSprite = Mockito.mock(Sprite.class);
        Sprite runningSprite1 = Mockito.mock(Sprite.class);
        Sprite runningSprite2 = Mockito.mock(Sprite.class);
        Sprite runningSprite3 = Mockito.mock(Sprite.class);
        Sprite jumpingSprite = Mockito.mock(Sprite.class);

        Mockito.when(spriteLoader.getSprite("sprites/king-falling.png")).thenReturn(fallingSprite);
        Mockito.when(spriteLoader.getSprite("sprites/king-running-1.png")).thenReturn(runningSprite1);
        Mockito.when(spriteLoader.getSprite("sprites/king-running-2.png")).thenReturn(runningSprite2);
        Mockito.when(spriteLoader.getSprite("sprites/king-running-3.png")).thenReturn(runningSprite3);
        Mockito.when(spriteLoader.getSprite("sprites/king-jumping.png")).thenReturn(jumpingSprite);

        long time = 1000;

        king.setIsFalling(true);
        kingViewer.draw(king, gui, time);
        verify(fallingSprite, times(1)).draw(gui, king.getPosition());

        king.setIsRunning(true);
        kingViewer.draw(king, gui, time);
        verify(runningSprite1, times(1)).draw(gui, king.getPosition());
        verify(runningSprite2, times(1)).draw(gui, king.getPosition());
        verify(runningSprite3, times(1)).draw(gui, king.getPosition());

        king.setIsJumping(true);
        kingViewer.draw(king, gui, time);
        verify(jumpingSprite, times(1)).draw(gui, king.getPosition());
    }
}
