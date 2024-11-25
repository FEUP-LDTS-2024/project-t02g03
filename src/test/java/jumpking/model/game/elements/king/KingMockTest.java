package jumpking.model.game.elements.king;

import jumpking.model.Position;
import jumpking.model.game.elements.King;
import jumpking.model.game.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class KingMockTest {

    private King king;
    private Scene scene;

    @BeforeEach
    public void setUp() {
        king = new King(100, 200);
        scene = mock(Scene.class);
        king.setScene(scene);
    }

    @Test
    public void testSetScene() {
        assertEquals(scene, king.getScene());
    }

    @Test
    public void testIncreaseJumps() {
        assertEquals(0, king.getJumps());
        king.increaseJumps();
        assertEquals(1, king.getJumps());
    }

    @Test
    public void testProjectileMotion() {
        List<Position> positions = king.projectileMotion(100, 1, 500);
        assertNotNull(positions);
        assertFalse(positions.isEmpty());
    }
}