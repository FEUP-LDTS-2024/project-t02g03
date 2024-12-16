package jumpking.view.elements;

import jumpking.gui.GUI;
import jumpking.model.game.elements.Princess;
import jumpking.view.Sprite;
import jumpking.view.SpriteLoader;

import java.io.IOException;

public class PrincessViewer implements ElementViewer<Princess>{
    Sprite sprite;

    public PrincessViewer(SpriteLoader spriteLoader) throws IOException {
        this.sprite = spriteLoader.getSprite("sprites/princess.png");
    }

    @Override
    public void draw(Princess princess, GUI gui, long time) {
        if (princess == null) {
            return; // Do not attempt to draw if princess is null
        }
        if (sprite != null) {
            sprite.draw(gui, princess.getPosition());
        }
    }
}
