package jumpking.model.game.elements;

import jumpking.model.Position;

public class Princess extends Element {

    private static final int width = 15;
    private static final int height = 19;

    private final Position bottomRight;
    private final Position topLeft;

    public Princess(int x, int y) {
        super(x, y);
        this.bottomRight = new Position(x + width, y);
        this.topLeft = new Position(x, y - height);
    }

    public Position getBottomRight() {
        return bottomRight;
    }

    public Position getTopLeft() {
        return topLeft;
    }

}
