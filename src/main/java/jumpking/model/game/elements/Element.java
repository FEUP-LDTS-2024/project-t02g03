package jumpking.model.game.elements;

import jumpking.model.Position;

public abstract class Element {

    protected Position position;

    public Element(int x, int y) {
        this.position = new Position(x, y);
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
