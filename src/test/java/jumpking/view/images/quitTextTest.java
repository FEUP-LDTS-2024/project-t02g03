package jumpking.view.images;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class quitTextTest {

    @Test
    void testQuitTextReturnsNull() {
        assertNotNull(quitText.getExitText(), "getExitText() method should not return null");
    }
}
