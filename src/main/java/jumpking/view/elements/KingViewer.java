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
    private int runningFrameCounter = 0;

    public KingViewer(SpriteLoader spriteLoader) throws IOException {
        spritesMap = new HashMap<>();
        spritesMap.put("idle", new Sprite[]{
                spriteLoader.getSprite("sprites/king-idle.png")
        });
        spritesMap.put("fallen", new Sprite[]{
                spriteLoader.getSprite("sprites/king-fallen.png")
        });
        spritesMap.put("crouching", new Sprite[]{
                spriteLoader.getSprite("sprites/king-crouching.png")
        });
        spritesMap.put("jumping", new Sprite[]{
                spriteLoader.getSprite("sprites/king-jumping.png")
        });
        spritesMap.put("falling", new Sprite[]{
                spriteLoader.getSprite("sprites/king-falling.png")
        });
        spritesMap.put("running", new Sprite[]{
                spriteLoader.getSprite("sprites/king-running-1.png"),
                spriteLoader.getSprite("sprites/king-running-2.png"),
                spriteLoader.getSprite("sprites/king-running-3.png"),
                spriteLoader.getSprite("sprites/king-running-2.png")
        });
        spritesMap.put("rebound", new Sprite[]{
                spriteLoader.getSprite("sprites/king-rebound.png")
        });
    }

    @Override
    public void draw(King king, GUI gui, long time) {
        System.out.println(king.getPosition().getX() + " " + king.getPosition().getY());
        String state = king.getState();
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
