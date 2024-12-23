package jumpking.view.images;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class startTextTest {

    @Test
    void testStartTextReturnsNull() {
        assertNotNull(startText.getStartText(), "getStartText() method should not return null");
    }
}
