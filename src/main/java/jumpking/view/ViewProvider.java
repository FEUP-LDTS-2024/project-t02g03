package jumpking.view;

import jumpking.view.elements.KingViewer;
import jumpking.view.menu.LogoViewer;
import jumpking.view.menu.DrawViewer;

import java.io.IOException;

public class ViewProvider {
    private final KingViewer kingViewer;
    private final DrawViewer drawViewer;
    private final LogoViewer logoViewer;

    public ViewProvider(SpriteLoader spriteLoader) throws IOException {
        this.kingViewer = new KingViewer(spriteLoader);
        this.drawViewer = new DrawViewer();
        this.logoViewer= new LogoViewer(spriteLoader);
    }

    public KingViewer getKingViewer() {
        return kingViewer;
    }

    public DrawViewer getDrawViewer() {
        return drawViewer;
    }

    public LogoViewer getLogoViewer() {
        return logoViewer;
    }
}
