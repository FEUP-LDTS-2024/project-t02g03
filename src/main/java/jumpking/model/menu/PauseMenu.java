package jumpking.model.menu;

import java.util.Arrays;
import java.util.List;

public class PauseMenu extends Menu {
    private int kingX;
    private int kingY;
    private int sceneCode;

    public PauseMenu(int kingX,int kingY,int sceneCode){
        this.kingX = kingX;
        this.kingY = kingY;
        this.sceneCode = sceneCode;
    }
    @Override
    protected List<Item> createItems() {
        int screenWidth = 333;
        int screenHeight = 250;

        Item start = new Item(screenWidth / 2 - 30, screenHeight / 2 - 10, Item.Type.RESUME); // Início no meio
        Item exit = new Item(screenWidth / 2 - 30, screenHeight / 2 +5 , Item.Type.QUIT); // Sair logo abaixo
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
}
