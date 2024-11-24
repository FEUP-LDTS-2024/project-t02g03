package jumpking.model.game.elements.king;

import jumpking.model.Position;
import jumpking.model.game.elements.Element;
import jumpking.model.game.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class King extends Element{

    private static final int maxHeightJump = 100;
    private static final int maxLengthJump = 180;
    private static final int width = 14;
    private static final int height = 15;

    private Position bottomRight;
    private Position topLeft;
    private Position topRight;

    private boolean isJumping = false;
    private boolean isFalling = false;
    private boolean isRebounding = false;
    private boolean isRunning = false;
    private boolean isFallen = false;
    private boolean facingRight = true;
    private Scene scene;
    private int jumps;
    private long startTime;

    public King(int x, int y) {
        super(x, y);
        this.bottomRight = new Position(x + width, y);
        this.topLeft = new Position(x, y - height);
        this.topRight = new Position(x + width, y - height);
        this.jumps = 0;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
        this.bottomRight = new Position(position.getX() + width, position.getY());
        this.topLeft = new Position(position.getX(), position.getY() - height);
        this.topRight = new Position(position.getX() + width, position.getY() - height);
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setIsFalling(boolean isFalling) {
        this.isFalling = isFalling;
    }

    public boolean isFalling() {
        return isFalling;
    }

    public void setIsRebounding(boolean isRebounding) {
        this.isRebounding = isRebounding;
    }

    public boolean isRebounding() {
        return isRebounding;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setIsFallen(boolean isFallen) {
        this.isFallen = isFallen;
    }

    public boolean isFallen() {
        return isFallen;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }

    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void increaseJumps() {
        jumps++;
    }

    public int getJumps() {
        return jumps;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long birthTime) {
        this.startTime = startTime;
    }

    public String getState() {
        if (isJumping) {
            return "jumping";
        } else if (isFalling) {
            return "falling";
        } else if (isRebounding) {
            return "rebound";
        } else if (isRunning) {
            return "running";
        } else if (isFallen) {
            return "fallen";
        } else {
            return "idle";
        }
    }

    public List<Position> projectileMotion(double height, int direction, int maxX) {
        List<Position> points = new ArrayList<>();
        Position lastPosition = null;

        // Vertex of the parabola is at (maxX/2, height)
        double h = maxX / 2.0;
        double k = height;

        // Calculate "a" for the parabola equation y = a * (x - h)^2 + k
        double a = -4.0 * height / (maxX * maxX);

        // Generate points along the arc
        for (double x = 0; x <= maxX; x += 0.1) {
            double y = a * Math.pow(x - h, 2) + k;
            Position newPosition = new Position(position.getX() + (int) Math.round(x * direction), position.getY() - (int) Math.round(y));

            if (lastPosition == null || Math.abs(newPosition.getX() - lastPosition.getX()) >= 2 || Math.abs(newPosition.getY() - lastPosition.getY()) >= 2) {
                points.add(newPosition);
                lastPosition = newPosition;
            }
        }

        return points;
    }

}
