package jumpking.model.game.scene;

import com.googlecode.lanterna.graphics.BasicTextImage;
import jumpking.model.Position;
import jumpking.model.game.elements.Block;
import jumpking.model.game.elements.Princess;
import jumpking.model.game.elements.King;

import java.util.List;

public class Scene {

    private final int sceneCode;

    private King king;
    private Block[] blocks;;
    private Princess princess;
    private BasicTextImage backgroundImage;


    public Scene(int sceneCode) {
        this.sceneCode = sceneCode;
        this.blocks = new Block[0];
        this.king = new King(100, 100);
    }

    public int getSceneCode() {
        return sceneCode;
    }

    public King getKing() {
        return king;
    }

    public void setKing(King king) {
        this.king = king;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }

    public Princess getPrincess() {
        return princess;
    }

    public BasicTextImage getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(BasicTextImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setPrincess(Princess princess) {
        this.princess = princess;
    }

    public void setStartingPosition(Position position) {
        king.setPosition(position);
    }

    public boolean canKingMove(Position position) {
        Position bottomRight = new Position(position.getX() + king.getWidth(), position.getY());
        Position topLeft = new Position(position.getX(), position.getY() - king.getHeight());
        Position topRight = new Position(position.getX() + king.getWidth(), position.getY() - king.getHeight());

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

    public boolean isKingFalling() {
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

    public void moveUp(int steps) {
        for (int i = 0; i < steps; i++) {
            king.moveUp();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void moveDown() {
        Position newPosition = king.moveDown();
        if (canKingMove(newPosition)) {
            king.setPosition(newPosition);
        }
    }

    public void moveRight(int steps) throws InterruptedException {
        for (int i = 0; i < steps; i++) {
            Position newPosition = king.moveRight();
            if (canKingMove(newPosition)) {
                king.setPosition(newPosition);
                Thread.sleep(1);
            }
        }
    }

    public void moveLeft(int steps) throws InterruptedException {
        for (int i = 0; i < steps; i++) {
            Position newPosition = king.moveLeft();
            if (canKingMove(newPosition)) {
                king.setPosition(newPosition);
                Thread.sleep(1);
            }
        }
    }

    public void jump(int jumpHeight, int direction) {
        int maxX = (int) (230 * (jumpHeight / 100.0));
        List<Position> trajectory = king.projectileMotion(jumpHeight, direction, maxX);
        for (Position position : trajectory) {
            if (canKingMove(position)) {
                king.setPosition(position);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
    }



}
