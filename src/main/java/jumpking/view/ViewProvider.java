package jumpking.view;

import jumpking.view.elements.KingViewer;
import jumpking.view.menu.LogoViewer;
import jumpking.view.menu.MainMenuViewer;
import jumpking.view.screens.MenuViewer;

import java.io.IOException;

public class ViewProvider {
    private final KingViewer kingViewer;
    private final MainMenuViewer mainMenuViewer;
    private final LogoViewer logoViewer;

    public ViewProvider(SpriteLoader spriteLoader) throws IOException {
        this.kingViewer = new KingViewer(spriteLoader);
        this.mainMenuViewer = new MainMenuViewer();
        this.logoViewer= new LogoViewer(spriteLoader);
    }

    public KingViewer getKingViewer() {
        return kingViewer;
    }

    public MainMenuViewer getMainMenuViewer() {
        return mainMenuViewer;
    }

    public LogoViewer getLogoViewer() {
        return logoViewer;
    }
}
