package jumpking.model.credits;

import jumpking.model.game.elements.King;

public class Credits {
    private int jumps;
    private final int minutes;
    private final int seconds;


    public Credits(King king) {
        this.jumps = king.getJumps();
        long duration = System.currentTimeMillis() - king.getStartTime();
        this.seconds = (int) ((duration / 1000) % 60);
        this.minutes = (int) ((duration / 1000) / 60);
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
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
}
