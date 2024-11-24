package jumpking.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IngameSpriteLoader implements SpriteLoader {

    final Map<String, Sprite> spritesMap;

    public IngameSpriteLoader() {
        spritesMap = new HashMap<>();
    }

    @Override
    public Sprite getSprite(String filePath) throws IOException {
        if (!spritesMap.containsKey(filePath)) {
            Sprite sprite = new Sprite(filePath);
            spritesMap.put(filePath, sprite);
        }
        return spritesMap.get(filePath);
    }
}
