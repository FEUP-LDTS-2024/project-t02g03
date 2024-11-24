package jumpking.view;

import java.io.IOException;

public interface SpriteLoader {
    Sprite getSprite(String filePath) throws IOException;
}
