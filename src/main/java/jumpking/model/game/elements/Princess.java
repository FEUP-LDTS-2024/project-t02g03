package jumpking.model.game.elements;

import jumpking.model.Position;

public class Princess extends Element {

    private static final int width = 15;
    private static final int height = 19;

    private Position bottomRight;
    private Position topLeft;
    private Position topRight;

    public Princess(int x, int y) {
        super(x, y);
        this.bottomRight = new Position(x + width, y);
        this.topLeft = new Position(x, y - height);
        this.topRight = new Position(x + width, y - height);
    }

    public Position getBottomRight() {
        return bottomRight;
    }

    public Position getTopLeft() {
        return topLeft;
    }

    public Position getTopRight() {
        return topRight;
    }
}
