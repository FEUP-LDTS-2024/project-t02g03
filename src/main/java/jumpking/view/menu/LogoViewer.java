package jumpking.view.menu;

import jumpking.gui.GUI;
import jumpking.model.Position;
import jumpking.view.Sprite;
import jumpking.view.SpriteLoader;

import java.io.IOException;
public class LogoViewer {
    private final Sprite sprite;

    public LogoViewer(SpriteLoader spriteLoader) throws IOException {
        this.sprite= spriteLoader.getSprite("sprites/logo.png");//testar
    }

    public void draw(GUI gui, int x, int y) {
        sprite.draw(gui, new Position(x, y));

    }
}
