package jumpking.model.credits;

import jumpking.model.game.elements.King;

public class Credits {
    private int jumps;
    private final int minutes;
    private final int seconds;

    private String[] names;

    public Credits(King king) {
        this.jumps = king.getJumps();
        long duration = System.currentTimeMillis() - king.getStartTime();
        this.seconds = (int) ((duration / 1000) % 60);
        this.minutes = (int) ((duration / 1000) / 60);

        String[] names = new String[3];
        names[0] = "  André Cortim";
        names[1] = "  Hugo Azevedo";
        names[2] = "  Joana Carvalhal";
        this.names = names;
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
    }

    public int getJumps() {
        return jumps;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String[] getNames() {
        return names;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }
}
