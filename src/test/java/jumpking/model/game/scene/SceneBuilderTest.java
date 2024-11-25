package jumpking.model.game.scene;

import jumpking.model.game.elements.King;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SceneBuilderTest {

    private SceneBuilder sceneBuilder;
    private King king;

    @BeforeEach
    public void setUp() throws IOException {
        sceneBuilder = new SceneBuilder(0);
        king = mock(King.class);
    }

    @Test
    public void testBuildSceneWithMockKing() {
        Scene scene = sceneBuilder.buildScene(king);
        assertNotNull(scene);
        assertEquals(king, scene.getKing());
    }
}