package jumpking.view.menu;

import com.googlecode.lanterna.TextColor;
import jumpking.gui.GUI;
import jumpking.model.Position;
import jumpking.model.menu.Item;
import jumpking.view.images.quitText;
import jumpking.view.images.resumeText;
import jumpking.view.images.startText;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DrawViewerTest {
    private DrawViewer drawViewer;
    private GUI gui;
    private Item startItem;
    private Item quitItem;
    private Item resumeItem;
    private Item currentItem;

    @BeforeEach
    void setUp() {
        drawViewer = new DrawViewer();
        gui = mock(GUI.class);
        startItem = mock(Item.class);
        quitItem = mock(Item.class);
        resumeItem = mock(Item.class);
        currentItem = mock(Item.class);
        when(startItem.getType()).thenReturn(Item.Type.START_GAME);
        when(quitItem.getType()).thenReturn(Item.Type.QUIT);
        when(resumeItem.getType()).thenReturn(Item.Type.RESUME);
        when(startItem.getPosition()).thenReturn(new Position(10, 10));
        when(quitItem.getPosition()).thenReturn(new Position(20, 20));
        when(resumeItem.getPosition()).thenReturn(new Position(30, 30));
    }

    @Test
    void testDrawCurrentStartItem() {
        currentItem  = startItem;
        drawViewer.draw(startItem, gui, currentItem);
        verify(gui, times(1)).drawTextImage(eq(startItem.getPosition()), eq(startText.getStartText()), eq(TextColor.Factory.fromString("#008000")), eq(false));
    }
    @Test
    void testDrawStartItem() {
        drawViewer.draw(startItem, gui, currentItem);
        verify(gui, times(1)).drawTextImage(eq(startItem.getPosition()), eq(startText.getStartText()), eq(TextColor.Factory.fromString("#FFFFFF")), eq(false));
    }

    @Test
    void testDrawCurrentQuitItem() {
        currentItem  = quitItem;
        drawViewer.draw(quitItem, gui, currentItem);
        verify(gui, times(1)).drawTextImage(eq(quitItem.getPosition()), eq(quitText.getExitText()), eq(TextColor.Factory.fromString("#008000")), eq(false));
    }

    @Test
    void testDrawQuitItem() {
        drawViewer.draw(quitItem, gui, currentItem);
        verify(gui, times(1)).drawTextImage(eq(quitItem.getPosition()), eq(quitText.getExitText()), eq(TextColor.Factory.fromString("#FFFFFF")), eq(false));
    }

    @Test
    void testDrawCurrentResumeItem() {
        currentItem  = resumeItem;
        drawViewer.draw(resumeItem, gui, currentItem);
        verify(gui, times(1)).drawTextImage(eq(resumeItem.getPosition()), eq(resumeText.getResumeText()), eq(TextColor.Factory.fromString("#008000")), eq(false));
    }

    @Test
    void testDrawResumeItem() {
        drawViewer.draw(resumeItem, gui, currentItem);
        verify(gui, times(1)).drawTextImage(eq(resumeItem.getPosition()), eq(resumeText.getResumeText()), eq(TextColor.Factory.fromString("#FFFFFF")), eq(false));
    }
}
