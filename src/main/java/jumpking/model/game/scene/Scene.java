package jumpking.model.game.scene;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
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
    private final int refreshRate = 10;

    private int width;
    private int height;
    private King king;
    private List<Block> blocks;
    private Instant keyPressStartTime;
    private boolean upKeyPressed = false;
    private final int MIN_JUMP_HEIGHT = 10;
    private final int MAX_JUMP_HEIGHT = 230;
    private ScreenRefresher screenRefresher;

    private TextImage backgroundImage;

    public Scene(int width, int height, ScreenRefresher screenRefresher) throws IOException {
        this.width = width;
        this.height = height;
        this.king = new King(168, 228); //spawn hero at the bottom of the screen
        this.blocks = new ArrayList<>();
        this.screenRefresher = screenRefresher;
        loadArenaFromFile("src/main/resources/scenes/screen.txt");
        loadBackgroundImage();
    }
    private void loadBackgroundImage() throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("scenes/backgrounds/Bloco1.png")) {
            if (is != null) {
                BufferedImage bufferedImage = ImageIO.read(is);
                backgroundImage = new BasicTextImage(new TerminalSize(bufferedImage.getWidth(), bufferedImage.getHeight()));

                for (int x = 0; x < bufferedImage.getWidth(); x++) {
                    for (int y = 0; y < bufferedImage.getHeight(); y++) {
                        int rgb = bufferedImage.getRGB(x, y);
                        TextColor color = TextColor.Factory.fromString(String.format("#%06X", (0xFFFFFF & rgb)));
                        backgroundImage.setCharacterAt(x, y, new TextCharacter(' ', TextColor.ANSI.DEFAULT, color));
                    }
                }
            } else {
                throw new IOException("Background image not found");
            }
        }
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
            // Add extra width for the left wall
            blocks.add(new Block(-1, y));
            blocks.add(new Block(-2, y));
            // Add extra width for the right wall
            blocks.add(new Block(width, y));
            blocks.add(new Block(width + 1, y));
        }


    }

    public void draw(TextGraphics graphics) {
        if (backgroundImage != null) {
            graphics.drawImage(new TerminalPosition(0, 0), backgroundImage);
        }

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
                if (i % refreshRate == 0) {
                    screenRefresher.drawAndRefresh();
                }
                try {
                    Thread.sleep(1); // Adjust the speed of jumping as needed
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        // Ensure the final position is drawn
        screenRefresher.drawAndRefresh();
    }

    public void moveHeroDown() {
        Position newPosition = king.moveDown();
        if (canHeroMove(newPosition)) {
            king.setPosition(newPosition);
        }
    }

    public void moveHeroRight() throws IOException {
        int steps = 5;
        for (int i = 0; i < steps; i++) {
            Position newPosition = king.moveRight();
            if (canHeroMove(newPosition)) {
                king.setPosition(newPosition);
                if (i % refreshRate == 0) {
                    screenRefresher.drawAndRefresh();
                }
                try {
                    Thread.sleep(1); // Adjust the speed of movement as needed
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        // Ensure the final position is drawn
        screenRefresher.drawAndRefresh();
    }

    public void moveHeroLeft() throws IOException {
        int steps = 5;
        for (int i = 0; i < steps; i++) {
            Position newPosition = king.moveLeft();
            if (canHeroMove(newPosition)) {
                king.setPosition(newPosition);
                if (i % refreshRate == 0) {
                    screenRefresher.drawAndRefresh();
                }
                try {
                    Thread.sleep(1); // Adjust the speed of movement as needed
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        // Ensure the final position is drawn
        screenRefresher.drawAndRefresh();
    }

    public void moveHero(int jumpHeight, int direction) throws IOException {
        int maxX = (int) (230 * (jumpHeight / 100.0));

        List<Position> trajectory = king.projectileMotion(jumpHeight, direction, maxX);
        int step = 0;

        for (Position position : trajectory) {
            if (canHeroMove(position)) {
                king.setPosition(position);
                if (step % refreshRate == 0) {
                    screenRefresher.drawAndRefresh();
                }
                step++;
            } else {
                break;
            }
        }
        // Ensure the final position is drawn
        screenRefresher.drawAndRefresh();
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
                jumpHeight = (int) keyPressDuration.toMillis() / 20; // Adjust divisor for jump sensitivity
                jumpHeight = Math.max(MIN_JUMP_HEIGHT, Math.min(jumpHeight, MAX_JUMP_HEIGHT)); // Apply limits
                king.setColor("#FFFFFF");
                moveHeroUp(jumpHeight);

                // Reset the flag
                upKeyPressed = false;
            }
        } else {
            if (upKeyPressed) { // Key was released (when any other key is pressed)
                Duration keyPressDuration = Duration.between(keyPressStartTime, Instant.now());
                jumpHeight = (int) keyPressDuration.toMillis() / 20; // Adjust divisor for jump sensitivity
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
        Position bottomRight = new Position(position.getX() + 13, position.getY());
        Position topLeft = new Position(position.getX(), position.getY() - 15);
        Position topRight = new Position(position.getX() + 13, position.getY() - 15);

        for (Block block : blocks) {
            Position blockPosition = block.getPosition();
            if (blockPosition.equals(position) ||
                    blockPosition.equals(bottomRight) ||
                    blockPosition.equals(topLeft) ||
                    blockPosition.equals(topRight)) {
                return false;
            }
        }
        return true;
    }

    public boolean isHeroFalling() {
        Position heroPosition = king.getPosition();
        Position positionBelowHeroLeft = new Position(heroPosition.getX(), heroPosition.getY() + 1);
        Position postionBelowHeroRight = new Position(heroPosition.getX() + 13, heroPosition.getY() + 1);

        for (Block block : blocks) {
            if (block.getPosition().equals(positionBelowHeroLeft) || block.getPosition().equals(postionBelowHeroRight)) {
                return false;
            }
        }
        return true;
    }

}
