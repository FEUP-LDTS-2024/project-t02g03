package jumpking.model.menu;

import jumpking.model.Position;

public class Item {

    public enum Type { START_GAME, EXIT,RESTART,TITLE}

    private final Position position;
    private final Type type;


    public Item(int x, int y, Type type) {
        this.position = new Position(x, y);
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public Type getType() {
        return type;
    }

}
