package jumpking.model.menu;

import java.util.List;

public abstract class Menu {

    private final List<Item> items;
    private int currentItem = 1;

    public Menu() {
        this.items = createItems();
    }

    protected abstract List<Item> createItems();

    public List<Item> getItems() {
        return items;
    }

    public int getNumberItems() {
        return this.items.size();
    }

    public void moveDown() {
        currentItem++;
        currentItem = getNumberItems() % currentItem;
    }

    public void moveUp() {
        currentItem += getNumberItems() - 1;
        currentItem = currentItem % getNumberItems();
        //if (currentItem==1) currentItem=2;
    }
//    public void select() {
//        items.get(currentItem).executeAction();
//    }

    public Item getCurrentItem() {
        return items.get(currentItem);
    }

}