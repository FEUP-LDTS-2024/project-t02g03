package jumpking.model.game.scene;

import jumpking.model.game.elements.King;
import jumpking.model.game.elements.Princess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
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
        assertNotNull(scene.getBlocks());
        assertNotNull(scene.getBackgroundImage());
    }

    @Test
    public void testBackgroundImageIsLoaded() {
        Scene scene = sceneBuilder.buildScene(king);
        assertNotNull(scene.getBackgroundImage());
        assertEquals(sceneBuilder.getBackgroundImage(), scene.getBackgroundImage());
    }

    @Test
    public void testBuildSceneWithPrincess() throws IOException {
        sceneBuilder = new SceneBuilder(4);
        Scene scene = sceneBuilder.buildScene(king);
        assertNotNull(scene);
        assertEquals(king, scene.getKing());
        assertNotNull(scene.getPrincess());
        assertEquals(115, scene.getPrincess().getX());
        assertEquals(33, scene.getPrincess().getY());
    }

    @Test
    public void testFileNotFoundException() {
        Exception exception = assertThrows(FileNotFoundException.class, () -> new SceneBuilder(999));
        assertEquals("Level file not found!", exception.getMessage());
    }

    @Test
    public void testPrincessPosition() throws IOException {
        sceneBuilder = new SceneBuilder(4);
        Scene scene = sceneBuilder.buildScene(king);
        Princess princess = scene.getPrincess();
        assertNotNull(princess);
        assertEquals(115, princess.getX());
        assertEquals(33, princess.getY());
    }

}