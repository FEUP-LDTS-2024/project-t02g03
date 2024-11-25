package jumpking.view.elements;

import jumpking.gui.GUI;
import jumpking.model.game.elements.King;
import jumpking.view.Sprite;
import jumpking.view.SpriteLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class KingViewerTest {
    private KingViewer kingViewer;
    private SpriteLoader spriteLoader;
    private GUI gui;
    private King king;

    @BeforeEach
    public void setUp() {
        this.kingViewer = Mockito.mock(KingViewer.class);
        this.spriteLoader = Mockito.mock(SpriteLoader.class);
        this.gui = Mockito.mock(GUI.class);
        this.king = Mockito.mock(King.class);
    }

    @Test
    public void drawTest() {
        Sprite fallingSprite = Mockito.mock(Sprite.class);
        Sprite runningSprite1 = Mockito.mock(Sprite.class);
        Sprite runningSprite2 = Mockito.mock(Sprite.class);
        King king = new King(20, 30);


    }
}
