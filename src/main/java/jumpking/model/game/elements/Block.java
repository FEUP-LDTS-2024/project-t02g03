package jumpking.model.game.elements;

public class Block extends Element {
    private final char symbol;

    public Block(int x, int y, char symbol) {
        super(x, y);
        this.symbol = symbol;
    }

}
