package jumpking.model.game.elements.king;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import jumpking.model.Position;
import jumpking.model.game.elements.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class King extends Element {

    private String color = "#FFFFFF";
    private BufferedImage image;

    private Position bottomRight;
    private Position topLeft;
    private Position topRight;


    public King (int x, int y) {
        super(x, y);
        this.bottomRight = new Position(x + 14, y);
        this.topLeft = new Position(x, y - 15);
        this.topRight = new Position(x + 14, y - 15);
        loadImage();
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

    public String getColor() {
        return color;
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
        updatePositions();
    }

    private void updatePositions() {
        this.bottomRight = new Position(position.getX() + 13, position.getY());
        this.topLeft = new Position(position.getX(), position.getY() - 15);
        this.topRight = new Position(position.getX() + 13, position.getY() - 15);
    }

    public void setColor(String color) {
        this.color = color;
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

    private void loadImage() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("sprites/kingIdle.png")) {
            if (is != null) {
                image = ImageIO.read(is);
            } else {
                throw new IOException("Image not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(TextGraphics graphics) {
        if (image != null) {
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    int color = image.getRGB(i, j);
                    int alpha = (color >> 24) & 0xff;
                    if (alpha > 0) { // Only draw pixels that are not fully transparent
                        graphics.setBackgroundColor(TextColor.Factory.fromString(String.format("#%06X", (0xFFFFFF & color))));
                        graphics.fillRectangle(new TerminalPosition(position.getX() + i, position.getY() - image.getHeight() + j + 1), new TerminalSize(1, 1), ' ');
                    }
                }
            }
        }
        graphics.setBackgroundColor(TextColor.Factory.fromString("#00FF00"));
        graphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(1, 1), ' '); // Bottom-left
        graphics.fillRectangle(new TerminalPosition(bottomRight.getX(), bottomRight.getY()), new TerminalSize(1, 1), ' '); // Bottom-right
        graphics.fillRectangle(new TerminalPosition(topLeft.getX(), topLeft.getY()), new TerminalSize(1, 1), ' '); // Top-left
        graphics.fillRectangle(new TerminalPosition(topRight.getX(), topRight.getY()), new TerminalSize(1, 1), ' '); // Top-right
    }

}
