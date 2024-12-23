package jumpking.view.screens;

import jumpking.gui.GUI;
import jumpking.model.menu.Item;
import jumpking.model.menu.Menu;
import jumpking.view.ViewProvider;
import jumpking.view.menu.DrawViewer;
import jumpking.view.menu.LogoViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class MenuViewerTest {
    private MenuViewer<Menu> menuViewer;
    private Menu menu;
    private ViewProvider viewProvider;
    private GUI gui;
    private DrawViewer drawViewer;
    private LogoViewer logoViewer;


    @BeforeEach
    void setUp() {
        menu = mock(Menu.class);
        viewProvider = mock(ViewProvider.class);
        gui = mock(GUI.class);
        drawViewer = mock(DrawViewer.class);
        logoViewer = mock(LogoViewer.class);

        when(viewProvider.getDrawViewer()).thenReturn(drawViewer);
        when(viewProvider.getLogoViewer()).thenReturn(logoViewer);

        menuViewer = new MenuViewer<>(menu, viewProvider);
    }

    @Test
    void testDraw() throws IOException {
        menuViewer.draw(gui, 0L);
        verify(gui, times(1)).clear();
        verify(gui, times(1)).refresh();
        verify(logoViewer, times(1)).draw(gui, 70, 40);
    }

    @Test
    void testDrawItems() throws  IOException{
        Item item1 = mock(Item.class);
        Item item2 = mock(Item.class);
        List<Item> items = Arrays.asList(item1, item2);

        when(menu.getItems()).thenReturn(items);
        when(menu.getCurrentItem()).thenReturn(item1);

        menuViewer.drawItems(gui, items);

        verify(drawViewer, times(1)).draw(item1, gui, item1);
        verify(drawViewer, times(1)).draw(item2, gui, item1);
    }
}
