package jumpking.view;

import jumpking.view.elements.KingViewer;

import java.io.IOException;

public class ViewProvider {
    private final KingViewer kingViewer;

    public ViewProvider(SpriteLoader spriteLoader) throws IOException {
        this.kingViewer = new KingViewer(spriteLoader);
    }

    public KingViewer getKingViewer() {
        return kingViewer;
    }
}
