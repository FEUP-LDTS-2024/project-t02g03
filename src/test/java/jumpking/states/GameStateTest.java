package jumpking.states;

import jumpking.control.Controller;
import jumpking.control.CreditsController;
import jumpking.control.KingController;
import jumpking.control.SceneController;
import jumpking.model.game.scene.Scene;
import jumpking.view.SpriteLoader;
import jumpking.view.ViewProvider;
import jumpking.view.screens.GameViewer;
import jumpking.view.screens.ScreenViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.UncheckedIOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameStateTest {
    private GameState gameState;
    private Scene scene;
    private SpriteLoader spriteLoader;

    @BeforeEach
    public void setUp() throws IOException {
        scene = mock(Scene.class);
        spriteLoader = mock(SpriteLoader.class);
        gameState = new GameState(scene, spriteLoader);
    }

    @Test
    public void testCreateScreenViewer() {
        ViewProvider viewProvider = mock(ViewProvider.class);
        ScreenViewer<Scene> screenViewer = gameState.createScreenViewer(viewProvider);
        assertNotNull(screenViewer);
        assert(screenViewer instanceof GameViewer);
    }

    @Test
    public void testCreateController() throws IOException {
        Controller<Scene> controller = gameState.createController();
        assertNotNull(controller);
        assertTrue(controller instanceof SceneController);
    }

    @Test
    public void testCreateControllerFail() {
        try {
            gameState = new GameState(scene, null);
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }
}
