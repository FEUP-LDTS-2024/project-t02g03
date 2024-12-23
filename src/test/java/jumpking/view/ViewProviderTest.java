package jumpking.view;

import jumpking.view.elements.KingViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.io.IOException;

//public class ViewProviderTest {
//    private SpriteLoader spriteLoader;
//    private ViewProvider viewProvider;
//
//    @BeforeEach
//    public void setUp() throws IOException {
//        this.spriteLoader = Mockito.mock(SpriteLoader.class);
//        viewProvider = new ViewProvider(spriteLoader);
//    }
//
//    @Test
//    public void kingViewerTest() throws IOException {
//        KingViewer kingViewer = viewProvider.getKingViewer();
//        assertNotNull(kingViewer);
//        verify(spriteLoader, times(1)).getSprite("sprites/king-idle.png");
//        verify(spriteLoader, times(1)).getSprite("sprites/king-fallen.png");
//        verify(spriteLoader, times(1)).getSprite("sprites/king-crouching.png");
//        verify(spriteLoader, times(1)).getSprite("sprites/king-jumping.png");
//        verify(spriteLoader, times(1)).getSprite("sprites/king-falling.png");
//        verify(spriteLoader, times(1)).getSprite("sprites/king-running-1.png");
//        verify(spriteLoader, times(1)).getSprite("sprites/king-running-2.png");
//        verify(spriteLoader, times(1)).getSprite("sprites/king-running-3.png");
//        verify(spriteLoader, times(1)).getSprite("sprites/king-rebound.png");
//    }
//}
