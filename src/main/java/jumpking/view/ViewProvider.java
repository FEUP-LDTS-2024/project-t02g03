package jumpking.view;

import jumpking.view.elements.KingViewer;
import jumpking.view.elements.PrincessViewer;

import java.io.IOException;

public class ViewProvider {
    private final KingViewer kingViewer;
    private final PrincessViewer princessViewer;

    public ViewProvider(SpriteLoader spriteLoader) throws IOException {
        this.kingViewer = new KingViewer(spriteLoader);
        this.princessViewer = new PrincessViewer(spriteLoader);
    }

    public KingViewer getKingViewer() {
        return kingViewer;
    }

    public PrincessViewer getPrincessViewer() {
        return princessViewer;
    }
}
