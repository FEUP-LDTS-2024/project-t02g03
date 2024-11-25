package jumpking.model.credits;

import jumpking.model.credits.Credits;
import jumpking.model.game.elements.King;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditsTest {
    private Credits credits;
    private King king;

    @BeforeEach
    public void setUp() {
        king = Mockito.mock(King.class);
        Mockito.when(king.getJumps()).thenReturn(5);
        Mockito.when(king.getStartTime()).thenReturn(System.currentTimeMillis() - 60000);
        this.credits = new Credits(king);
    }

    @Test
    public void equalsTest() {
        assertEquals(5, credits.getJumps());
        assertEquals(1, credits.getMinutes());
        assertEquals(0,credits.getSeconds());
        credits.setJumps(10);

        String[] names = new String[3];
        names[0] = "  André Cortim";
        names[1] = "  Hugo Azevedo";
        names[2] = "Joana Carvalhal";
        credits.setNames(names);

        assertEquals(10, credits.getJumps());
        assertArrayEquals(names, credits.getNames());

    }
}