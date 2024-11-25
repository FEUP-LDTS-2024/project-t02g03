package jumpking.model.game.scene;

import jumpking.model.Position;
import jumpking.model.game.elements.King;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SceneMockTest {

    private Scene scene;
    private King king;

    @BeforeEach
    public void setUp() {
        king = mock(King.class);
        scene = new Scene(0);
        scene.setKing(king); // Set the mocked King object after Scene initialization
    }

    @Test
    public void testSceneInitialization() {
        assertNotNull(scene);
        assertEquals(king, scene.getKing());
    }

    @Test
    public void testSetKing() {
        King newKing = mock(King.class);
        scene.setKing(newKing);
        assertEquals(newKing, scene.getKing());
    }

    @Test
    public void testKingInteraction() {
        when(king.getPosition()).thenReturn(new Position(100, 200));
        assertEquals(new Position(100, 200), king.getPosition());
        verify(king, times(1)).getPosition();
    }
}