package jumpking.model.menu;

import jumpking.model.Position;

public class Item {

    public enum Type { START_GAME, EXIT,RESTART,TITLE,INFO} //Porque fazer assim?

    private final Position position;
    private final int width;
    private final int height;
    private final Type type;

    //Deviamos usar position e size
    public Item(int x, int y, Type type, int width, int height) {
        this.position = new Position(x, y);
        this.type = type;
        this.width = width;
        this.height = height;
    }

    public Position getPosition() {
        return position;
    }

    public Type getType() {
        return type;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
