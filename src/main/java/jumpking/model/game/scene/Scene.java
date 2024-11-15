package jumpking.model.game.scene;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jumpking.model.Position;
import jumpking.model.game.FileReader;
import jumpking.model.game.ScreenRefresher;
import jumpking.model.game.elements.Block;
import jumpking.model.game.elements.king.King;

public class Scene {
    private int width;
    private int height;
    private King king;
    private List<Block> blocks;
    private Instant keyPressStartTime;
    private boolean upKeyPressed = false;
    private final int MIN_JUMP_HEIGHT = 1;
    private final int MAX_JUMP_HEIGHT = 30;
    private ScreenRefresher screenRefresher;

    public Scene(int width, int height, ScreenRefresher screenRefresher) throws IOException {
        this.width = width;
        this.height = height;
        this.king = new King(width / 2, height - 2); //spawn hero at the bottom of the screen
        this.blocks = new ArrayList<>();
        this.screenRefresher = screenRefresher;
        loadArenaFromFile("src/main/resources/scenes/screen1.txt");
    }

    private void loadArenaFromFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader();
        List<String> lines = fileReader.readFile(filePath);

        this.height = lines.size();
        this.width = lines.get(0).length();

        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            for (int x = 0; x < width; x++) {
                char ch = line.charAt(x);
                if (ch == '#') {
                    blocks.add(new Block(x, y));
                } else if (ch == 'H') {
                    king.setPosition(new Position(x, y));
                }
            }
        }
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(com.googlecode.lanterna.TextColor.ANSI.BLUE);
        graphics.fillRectangle(new com.googlecode.lanterna.TerminalPosition(0, 0), new com.googlecode.lanterna.TerminalSize(width, height), ' ');

        for (Block block : blocks) {
            block.draw(graphics);
        }

        king.draw(graphics);
    }

    public void moveHeroUp(int steps) throws IOException {
        for (int i = 0; i < steps; i++) {
            Position newPosition = king.moveUp();
            if (canHeroMove(newPosition)) {
                king.setPosition(newPosition);
                screenRefresher.drawAndRefresh();
                try {
                    Thread.sleep(10); // Adjust the speed of jumping as needed
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void moveHeroDown() {
        Position newPosition = king.moveDown();
        if (canHeroMove(newPosition)) {
            king.setPosition(newPosition);
        }
    }

    public void moveHeroRight() {
        Position newPosition = king.moveRight();
        if (canHeroMove(newPosition)) {
            king.setPosition(newPosition);
        }
    }

    public void moveHeroLeft() {
        Position newPosition = king.moveLeft();
        if (canHeroMove(newPosition)) {
            king.setPosition(newPosition);
        }
    }

    public void moveHero(int jumpHeight, int direction) throws IOException {
        int maxX;
        if (jumpHeight <= 2) {
            maxX = 1;
        } else if (jumpHeight <= 6) {
            maxX = 2;
        } else {
            maxX = 6;
        }

        List<Position> trajectory = king.projectileMotion(jumpHeight, direction, maxX);
        for (Position position : trajectory) {
            if (canHeroMove(position)) {
                king.setPosition(position);
                screenRefresher.drawAndRefresh();
                try {
                    Thread.sleep(10); // Adjust the speed of jumping as needed
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                break;
            }
        }
    }

    public void processKey(KeyStroke key) throws IOException {
        int jumpHeight;
        if (key.getKeyType() == KeyType.ArrowUp) {
            if (!upKeyPressed) { // If key was not previously pressed
                upKeyPressed = true;
                keyPressStartTime = Instant.now();
                king.setColor("#000000");
            } else { // If key was previously pressed
                Duration keyPressDuration = Duration.between(keyPressStartTime, Instant.now());
                jumpHeight = (int) keyPressDuration.toMillis() / 100; // Adjust divisor for jump sensitivity
                jumpHeight = Math.max(MIN_JUMP_HEIGHT, Math.min(jumpHeight, MAX_JUMP_HEIGHT)); // Apply limits
                king.setColor("#FFFFFF");
                moveHeroUp(jumpHeight);

                // Reset the flag
                upKeyPressed = false;
            }
        } else {
            if (upKeyPressed) { // Key was released (when any other key is pressed)
                Duration keyPressDuration = Duration.between(keyPressStartTime, Instant.now());
                jumpHeight = (int) keyPressDuration.toMillis() / 100; // Adjust divisor for jump sensitivity
                jumpHeight = Math.max(MIN_JUMP_HEIGHT, Math.min(jumpHeight, MAX_JUMP_HEIGHT)); // Apply limits
                king.setColor("#FFFFFF");
                if (key.getKeyType() == KeyType.ArrowLeft) {
                    moveHero(jumpHeight, -1);
                } else if (key.getKeyType() == KeyType.ArrowRight) {
                    moveHero(jumpHeight, 1);
                }


                // Reset the flag
                upKeyPressed = false;
            } else {
                if (key.getKeyType() == KeyType.ArrowLeft) {
                    moveHeroLeft();
                } else if (key.getKeyType() == KeyType.ArrowRight) {
                    moveHeroRight();
                }
            }
        }

        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            throw new IOException("Exit the loop");
        } else if (key.getKeyType() == KeyType.EOF) {
            throw new IOException("Exit the loop");
        }
    }

    private boolean canHeroMove(Position position) {
        for (Block block : blocks) {
            if (block.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

    public boolean isHeroFalling() {
        Position heroPosition = king.getPosition();
        Position positionBelowHero = new Position(heroPosition.getX(), heroPosition.getY() + 1);

        for (Block block : blocks) {
            if (block.getPosition().equals(positionBelowHero)) {
                return false;
            }
        }
        return true;
    }

}
