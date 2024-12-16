package jumpking.model.game.elements;

import jumpking.model.Position;
import jumpking.model.game.scene.Scene;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class King extends Element{

    public enum PlayerState {
        IDLE,
        CROUCHING,
        JUMPING,
        FALLING,
        REBOUNDING,
        RUNNING,
        FALLEN
    }

    private Queue<Position> jumpPositions = new LinkedList<>();

    public Queue<Position> getJumpPositions() {
        return jumpPositions;
    }

    public void updateJumpPositions(int yOffset) {
        Queue<Position> updatedPositions = new LinkedList<>();
        for (Position pos : jumpPositions) {
            updatedPositions.add(new Position(pos.getX(), pos.getY() + yOffset));
        }
        jumpPositions = updatedPositions;
    }

    private static final int width = 14;
    private static final int height = 15;

    private Position bottomRight;
    private Position topLeft;
    private Position topRight;

    private boolean facingRight = true;
    private Scene scene;
    private int jumps;
    private long startTime;

    private PlayerState state;

    public King(int x, int y) {
        super(x, y);
        this.bottomRight = new Position(x + width, y);
        this.topLeft = new Position(x, y - height);
        this.topRight = new Position(x + width, y - height);
        this.jumps = 0;
        this.startTime = System.currentTimeMillis();
        this.state = PlayerState.IDLE;
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

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public List<Position> projectileMotion(double height, int direction, int maxX) {
        List<Position> points = new ArrayList<>();
        Position lastPosition = null;

        // Vertex of the parabola is at (maxX/2, height)
        double h = maxX / 2.0;
        double k = height;

        // Calculate "a" for the parabola equation y = a * (x - h)^2 + k
        double a = -4.0 * height / (maxX * maxX);

        if (direction == 0) {
            // Handle straight up jump
            for (double y = 0; y <= height; y += 2) {
                Position newPosition = new Position(position.getX(), position.getY() - (int) Math.round(y));
                if (lastPosition == null || Math.abs(newPosition.getY() - lastPosition.getY()) >= 3) {
                    points.add(newPosition);
                    lastPosition = newPosition;
                }
            }
        } else {
            // Generate points along the arc
            for (double x = 0; x <= maxX; x +=3) {
                double y = a * Math.pow(x - h, 2) + k;
                Position newPosition = new Position(position.getX() + (int) Math.round(x * direction), position.getY() - (int) Math.round(y));
                if (lastPosition == null || Math.abs(newPosition.getX() - lastPosition.getX()) >= 3 || Math.abs(newPosition.getY() - lastPosition.getY()) >= 3) {
                    points.add(newPosition);
                    lastPosition = newPosition;
                }
            }
        }

        return points;
    }

}
