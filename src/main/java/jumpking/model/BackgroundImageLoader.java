package jumpking.model;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.TextCharacter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class BackgroundImageLoader {

    public BasicTextImage loadBackgroundImage(String imagePath) throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(imagePath)) {
            if (is != null) {
                BufferedImage bufferedImage = ImageIO.read(is);
                BasicTextImage backgroundImage = new BasicTextImage(new TerminalSize(bufferedImage.getWidth(), bufferedImage.getHeight()));

                for (int x = 0; x < bufferedImage.getWidth(); x++) {
                    for (int y = 0; y < bufferedImage.getHeight(); y++) {
                        int rgb = bufferedImage.getRGB(x, y);
                        TextColor color = TextColor.Factory.fromString(String.format("#%06X", (0xFFFFFF & rgb)));
                        backgroundImage.setCharacterAt(x, y, new TextCharacter(' ', TextColor.ANSI.DEFAULT, color));
                    }
                }
                return backgroundImage;
            } else {
                throw new IOException("Background image not found");
            }
        }
    }
}