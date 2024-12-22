package jumpking.model.credits;

import jumpking.model.BackgroundImageLoader;
import jumpking.model.game.elements.King;

import com.googlecode.lanterna.graphics.BasicTextImage;
import java.io.IOException;

public class Credits {

    private int jumps;
    private final int minutes;
    private final int seconds;
    private BasicTextImage backgroundImage;

    public Credits(King king) {
        this.jumps = king.getJumps();
        long duration = System.currentTimeMillis() - king.getStartTime();
        this.seconds = (int) ((duration / 1000) % 60);
        this.minutes = (int) ((duration / 1000) / 60);
        loadBackground();
    }

    public int getJumps() {
        return jumps;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public BasicTextImage getBackgroundImage() {
        return backgroundImage;
    }

    private void loadBackground() {
        BackgroundImageLoader loader = new BackgroundImageLoader();
        try {
            this.backgroundImage = loader.loadBackgroundImage("backgrounds/credits.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
