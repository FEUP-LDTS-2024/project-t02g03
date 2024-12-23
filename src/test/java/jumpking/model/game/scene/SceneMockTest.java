package jumpking.model.game.scene;

import jumpking.Application;
import jumpking.model.Position;
import jumpking.model.game.elements.King;
import jumpking.states.GameState;
import jumpking.view.SpriteLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SceneMockTest {

    private Scene scene;
    private King king;
    private Application app;
    private SpriteLoader spriteLoader;

    @BeforeEach
    public void setUp() {
        king = mock(King.class);
        app = mock(Application.class);
        scene = new Scene(0);
        scene.setKing(king);
        spriteLoader = mock(SpriteLoader.class);
        when(app.getSpriteLoader()).thenReturn(spriteLoader);
    }

    @Test
    public void testChangeSceneUp() throws IOException {
        when(king.getPosition()).thenReturn(new Position(100, -1));
        scene.changeScene(app);
        verify(app, times(1)).setState(any(GameState.class));
    }

    @Test
    public void testChangeSceneDownFromOneToZero() throws IOException {
        when(king.getPosition()).thenReturn(new Position(100, 251));
        scene = new Scene(1);
        scene.setKing(king);
        scene.changeScene(app);
        verify(app, times(1)).setState(any(GameState.class));
    }
}