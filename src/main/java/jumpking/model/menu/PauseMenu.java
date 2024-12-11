package jumpking.model.menu;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Arrays;
import java.util.List;

public class PauseMenu extends Menu {
    @Override
    protected List<Item> createItems() {
        Item start = new Item(160/ 2 - (5 / 2) * 3 - (5 / 2) - 2, 55, Item.Type.START_GAME); //Valores provisórios
        Item exit = new Item(160/ 2 - (4 / 2) * 3 - (4 / 2), 65, Item.Type.EXIT);
        Item restart = new Item(160/ 2 - (4 / 2) * 3 - (3 / 2)-4, 65, Item.Type.RESTART);
        return Arrays.asList(start, exit);
    }


//    public void draw(TextGraphics graphics){
//        graphics.setBackgroundColor(TextColor.Factory.fromString("#0000FF"));
//        for(Item item : getItems()){
//            if(item.getType()==Item.Type.START_GAME){
//                graphics.putString(item.getWidth(),item.getHeight(), "[ START GAME ]");
//            }else if(item.getType()==Item.Type.EXIT){
//                graphics.putString(item.getWidth(),item.getHeight(), "[ EXIT ]");
//            }
//        }
//    }
}
