package jumpking.model.menu;

import java.util.Arrays;
import java.util.List;

public class PauseMenu extends Menu {
    private final int kingX;
    private final int kingY;
    private final int sceneCode;
    private final int kingJumps;
    private final long kingStartTime;

    public PauseMenu(int kingX, int kingY,int sceneCode, int kingJumps, long kingStartTime) {
        this.kingX = kingX;
        this.kingY = kingY;
        this.sceneCode = sceneCode;
        this.kingJumps = kingJumps;
        this.kingStartTime = kingStartTime;
    }
    @Override
    protected List<Item> createItems() {
        int screenWidth = 333;
        int screenHeight = 250;

        Item start = new Item(screenWidth / 2 - 30, screenHeight / 2 - 10, Item.Type.RESUME);
        Item exit = new Item(screenWidth / 2 - 30, screenHeight / 2 +5 , Item.Type.QUIT);
        return Arrays.asList(start, exit);
    }

    public int getKingX() {
        return kingX;
    }

    public int getKingY() {
        return kingY;
    }

    public int getSceneCode() {
        return sceneCode;
    }

    public int getKingJumps() {
        return kingJumps;
    }

    public long getKingStartTime() {
        return kingStartTime;
    }
}
