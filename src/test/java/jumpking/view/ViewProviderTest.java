package jumpking.view;

import jumpking.view.elements.KingViewer;
import jumpking.view.elements.PrincessViewer;
import jumpking.view.menu.DrawViewer;
import jumpking.view.menu.LogoViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ViewProviderTest {
    private SpriteLoader spriteLoader;
    private ViewProvider viewProvider;

    @BeforeEach
    public void setUp() throws IOException {
        this.spriteLoader = Mockito.mock(SpriteLoader.class);
        this.viewProvider = new ViewProvider(spriteLoader);
    }

    @Test
    public void testGetKingViewer() throws IOException {
        KingViewer kingViewer = viewProvider.getKingViewer();
        assertNotNull(kingViewer);
        verify(spriteLoader, times(12)).getSprite(anyString());
    }

    @Test
    public void testGetPrincessViewer() throws IOException {
        PrincessViewer princessViewer = viewProvider.getPrincessViewer();
        assertNotNull(princessViewer);
        verify(spriteLoader, times(12)).getSprite(anyString());
    }

    @Test
    public void testGetDrawViewer() {
        DrawViewer drawViewer = viewProvider.getDrawViewer();
        assertNotNull(drawViewer);
    }

    @Test
    public void testGetLogoViewer() throws IOException {
        LogoViewer logoViewer = viewProvider.getLogoViewer();
        assertNotNull(logoViewer);
        verify(spriteLoader, times(12)).getSprite(anyString());
    }
}