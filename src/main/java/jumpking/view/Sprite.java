package jumpking.view;

import com.googlecode.lanterna.TextColor;
import jumpking.gui.GUI;
import jumpking.model.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Sprite {

    private final BufferedImage sprite;

    public Sprite(String filePath) throws IOException {
        InputStream resource = getClass().getClassLoader().getResourceAsStream(filePath);
        if (resource != null) {
            sprite = ImageIO.read(resource);
        } else {
            throw new IOException("Image not found");
        }
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void draw(GUI gui, Position position ) {
        if (sprite != null) {
            for (int i = 0; i < sprite.getWidth(); i++) {
                for (int j = 0; j < sprite.getHeight(); j++) {
                    int color = sprite.getRGB(i, j);
                    int alpha = (color >> 24) & 0xff;
                    if (alpha > 0) { // Only draw pixels that are not fully transparent
                        Position p = new Position(position.getX() + i, position.getY() + j -14);
                        gui.drawPixel(p, TextColor.Factory.fromString(String.format("#%06X", (0xFFFFFF & color))));
                    }
                }
            }
        }
    }

    public void drawFlipped(GUI gui, Position position) {
        if (sprite != null) {
            int width = sprite.getWidth();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < sprite.getHeight(); j++) {
                    int color = sprite.getRGB(i, j);
                    int alpha = (color >> 24) & 0xff;
                    if (alpha > 0) { // Only draw pixels that are not fully transparent
                        Position p = new Position(position.getX() + (width - 1 - i), position.getY() + j - 14);
                        gui.drawPixel(p, TextColor.Factory.fromString(String.format("#%06X", (0xFFFFFF & color))));
                    }
                }
            }
        }
    }

}
