package jumpking.view.screens;

import jumpking.gui.GUI;
import jumpking.model.game.elements.Block;
import jumpking.model.game.elements.King;
import jumpking.model.game.elements.Princess;
import jumpking.model.game.scene.Scene;
import jumpking.view.SpriteLoader;
import jumpking.view.ViewProvider;
import jumpking.view.elements.ElementViewer;
import jumpking.view.elements.KingViewer;
import jumpking.view.elements.PrincessViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

public class GameViewerTest {
    private GUI gui;
    private KingViewer kingViewer;
    private PrincessViewer princessViewer;
    private Princess princess;
    private Scene scene;
    private GameViewer gameViewer;
    private King king;

    @BeforeEach
    public void setUp() throws IOException {
        this.gui = Mockito.mock(GUI.class);
        this.kingViewer = Mockito.mock(KingViewer.class);
        princess = Mockito.mock(Princess.class);
        this.princessViewer = Mockito.mock(PrincessViewer.class);
        this.king = Mockito.mock(King.class);
        this.scene = Mockito.mock(Scene.class);
        SpriteLoader spriteLoader = Mockito.mock(SpriteLoader.class);
        ViewProvider viewProvider = Mockito.mock(ViewProvider.class);

        Mockito.when(scene.getKing()).thenReturn(king);
        Mockito.when(viewProvider.getKingViewer()).thenReturn(kingViewer);

        gameViewer = new GameViewer(scene, viewProvider);
    }

    @Test
    public void drawTest() {
        long time = 1000;
        gameViewer.draw(gui, time);
        verify(gui, times(1)).clear();
        verify(kingViewer, times(1)).draw(king, gui, time);
        verify(princessViewer, times(1)).draw(princess, gui, time);

    }

    @Test
    public void drawElementTest() {
        long time = 1000;
        ElementViewer<King> viewer = Mockito.mock(ElementViewer.class);
        gameViewer.drawElement(gui, king, viewer, time);
        verify(viewer, times(1)).draw(king, gui, time);
    }

    @Test
    public void drawElementsTest() {
        long time = 1000;
        ElementViewer<Block> blockViewer = Mockito.mock(ElementViewer.class);
        Block block1 = Mockito.mock(Block.class);
        Block block2 = Mockito.mock(Block.class);
        List<Block> blocks = List.of(block1, block2);
        gameViewer.drawElements(gui, blocks, blockViewer, time);
        verify(blockViewer, times(2)).draw(any(Block.class), eq(gui), eq(time));
    }
}