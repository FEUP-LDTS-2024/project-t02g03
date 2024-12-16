package jumpking.view.elements;

import jumpking.gui.GUI;
import jumpking.model.game.elements.King;
import jumpking.view.Sprite;
import jumpking.view.SpriteLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KingViewer implements ElementViewer<King> {
    private final Map<String, Sprite[]> spritesMap;
    private int animationFrame = 0;

    public KingViewer(SpriteLoader spriteLoader) throws IOException {
        spritesMap = new HashMap<>();
        spritesMap.put("IDLE", new Sprite[]{
                spriteLoader.getSprite("sprites/king-idle.png")
        });
        spritesMap.put("FALLEN", new Sprite[]{
                spriteLoader.getSprite("sprites/king-fallen.png")
        });
        spritesMap.put("CROUCHING", new Sprite[]{
                spriteLoader.getSprite("sprites/king-crouching.png")
        });
        spritesMap.put("JUMPING", new Sprite[]{
                spriteLoader.getSprite("sprites/king-jumping.png")
        });
        spritesMap.put("FALLING", new Sprite[]{
                spriteLoader.getSprite("sprites/king-falling.png")
        });
        spritesMap.put("RUNNING", new Sprite[]{
                spriteLoader.getSprite("sprites/king-running-1.png"),
                spriteLoader.getSprite("sprites/king-running-2.png"),
                spriteLoader.getSprite("sprites/king-running-3.png"),
                spriteLoader.getSprite("sprites/king-running-2.png")
        });
        spritesMap.put("REBOUND", new Sprite[]{
                spriteLoader.getSprite("sprites/king-rebound.png")
        });
    }

    @Override
    public void draw(King king, GUI gui, long time) {
        String state = king.getState().name();
        Sprite[] sprites = spritesMap.get(state);
        if (sprites != null) {
            Sprite sprite = getCurrentSprite(sprites);
            if (king.isFacingRight()) {
                sprite.draw(gui, king.getPosition());
            } else {
                sprite.drawFlipped(gui, king.getPosition());
            }
        }
    }

    private Sprite getCurrentSprite(Sprite[] sprites) {
        Sprite sprite = sprites[animationFrame % sprites.length];
        animationFrame++;
        return sprite;
    }

}
