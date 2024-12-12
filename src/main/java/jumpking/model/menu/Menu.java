package jumpking.model.menu;

import java.util.List;

public abstract class Menu {

    private final List<Item> items;
    private int currentItem;

    public Menu() {
        this.items = createItems();
        currentItem= 1;
    }

    protected abstract List<Item> createItems();

    public List<Item> getItems() {
        return items;
    }

    public int getNumberItems() {
        return this.items.size();
    }

    public void moveDown() {
        currentItem = currentItem + 1;
        if(currentItem>getNumberItems()-1){
            currentItem =0;
        }
    }

    public void moveUp() {
        currentItem = currentItem - 1 ;
        if(currentItem<0){
            currentItem =getNumberItems()-1;
        }
    }

    public Item getCurrentItem() {
        return items.get(currentItem);
    }

}