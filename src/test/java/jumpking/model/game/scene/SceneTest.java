package jumpking.model.game.scene;

import jumpking.model.game.elements.King;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SceneTest {

    private Scene scene;
    private King king;

    @BeforeEach
    public void setUp() {
        king = new King(100, 200);
        scene = new Scene(0);
        scene.setKing(king); // Set the King object after Scene initialization
    }

    @Test
    public void testSceneInitialization() {
        assertNotNull(scene);
        assertEquals(king, scene.getKing());
    }

    @Test
    public void testSetKing() {
        King newKing = new King(150, 250);
        scene.setKing(newKing);
        assertEquals(newKing, scene.getKing());
    }
}