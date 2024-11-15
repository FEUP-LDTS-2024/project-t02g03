package jumpking.model.menu;

import java.util.List;

public abstract class Menu {

    private final List<Item> items;
    private int currentItem = 0;

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
        currentItem = currentItem % getNumberItems();
    }

    public void moveUp() {
        currentItem += getNumberItems() - 1;
        currentItem = currentItem % getNumberItems();
    }

    public Item getCurrentEntry() {
        return items.get(currentItem);
    }

}
