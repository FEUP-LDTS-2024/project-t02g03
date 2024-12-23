package jumpking.view.images;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class resumeTextTest {

    @Test
    void testResumeTextReturnsNull() {
        assertNotNull(resumeText.getResumeText(), "getResumeText() method should not return null");
    }
}
