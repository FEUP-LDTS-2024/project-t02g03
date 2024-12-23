package jumpking.model.menu;

import com.googlecode.lanterna.graphics.BasicTextImage;
import jumpking.model.BackgroundImageLoader;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

public abstract class Menu {

    private final List<Item> items;
    private int currentItem;
    private BasicTextImage backgroundImage;

    public Menu() {
        this.items = createItems();
        currentItem= 0;
        loadBackground();
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

    public Item getCurrentItem() {
        return items.get(currentItem);
    }

    public BasicTextImage getBackgroundImage() {
        return backgroundImage;
    }
    private void loadBackground() {
        BackgroundImageLoader loader = new BackgroundImageLoader();
        try {
            this.backgroundImage = loader.loadBackgroundImage("backgrounds/scene0.png");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}