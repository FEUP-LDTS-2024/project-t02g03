package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.Position;
import jumpking.model.game.scene.Scene;
import jumpking.model.credits.Credits;
import jumpking.model.game.elements.King;
import jumpking.model.menu.PauseMenu;
import jumpking.states.CreditsState;
import jumpking.states.PauseState;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class KingController extends Controller {

    private boolean upKeyPressed = false;
    private Instant keyPressStartTime;
    public static final int MIN_JUMP_HEIGHT = 10;
    public static final int MAX_JUMP_HEIGHT = 230;
    private static final int refreshRate = 5;
    private int noneCount = 0;
    private Queue<Position> jumpPositions = new LinkedList<>();

    public KingController(Scene scene) {
        super(scene);
    }

    @Override
    public void step(Application app, GUI.Act act, long time) throws IOException {
        Scene scene = (Scene) getModel();
        King king = scene.getKing();

        if (!scene.updateKingPosition(jumpPositions)) {
            jumpPositions.clear(); // Clear the queue if the king can't move to the new position
        }

        if (!jumpPositions.isEmpty()) {
            Position currentPosition = king.getPosition();
            Position nextPosition = jumpPositions.peek();
            if (nextPosition.getY() < currentPosition.getY()) {
                king.setState(King.PlayerState.JUMPING);
            } else {
                king.setState(King.PlayerState.FALLING);
            }
            return;
        }

        try {
            switch (act) {
                case UP:
                    noneCount = 0;
                    king.setState(King.PlayerState.IDLE);
                    if (!upKeyPressed) {
                        upKeyPressed = true;
                        keyPressStartTime = Instant.now();
                        king.setState(King.PlayerState.CROUCHING);
                        king.increaseJumps();
                    } else {
                        Duration keyPressDuration = Duration.between(keyPressStartTime, Instant.now());
                        int jumpHeight = (int) keyPressDuration.toMillis() / 20;
                        jumpHeight = Math.max(MIN_JUMP_HEIGHT, Math.min(jumpHeight, MAX_JUMP_HEIGHT));
                        jumpPositions.addAll(scene.jump(jumpHeight, 0));
                        upKeyPressed = false;
                        king.setState(King.PlayerState.JUMPING);
                    }
                    break;
                case LEFT:
                    noneCount = 0;
                    if (upKeyPressed) {
                        Duration keyPressDuration = Duration.between(keyPressStartTime, Instant.now());
                        int jumpHeight = (int) keyPressDuration.toMillis() / 20;
                        jumpHeight = Math.max(MIN_JUMP_HEIGHT, Math.min(jumpHeight, MAX_JUMP_HEIGHT));
                        jumpPositions.addAll(scene.jump(jumpHeight, -1));
                        upKeyPressed = false;
                        king.setFacingRight(false);
                        king.setState(King.PlayerState.IDLE);
                    } else {
                        scene.moveLeft(5);
                        king.setFacingRight(false);
                        king.setState(King.PlayerState.RUNNING);
                    }
                    break;
                case RIGHT:
                    noneCount = 0;
                    if (upKeyPressed) {
                        Duration keyPressDuration = Duration.between(keyPressStartTime, Instant.now());
                        int jumpHeight = (int) keyPressDuration.toMillis() / 20;
                        jumpHeight = Math.max(MIN_JUMP_HEIGHT, Math.min(jumpHeight, MAX_JUMP_HEIGHT));
                        jumpPositions.addAll(scene.jump(jumpHeight, 1));
                        upKeyPressed = false;
                        king.setFacingRight(true);
                        king.setState(King.PlayerState.IDLE);
                    } else {
                        scene.moveRight(5);
                        king.setFacingRight(true);
                        king.setState(King.PlayerState.RUNNING);
                    }
                    break;
                case PAUSE:
                    app.setState(new PauseState(new PauseMenu(king.getX(), king.getY(), scene.getSceneCode()), app.getSpriteLoader()));
                    break;
                case QUIT:
                    app.setState(new CreditsState(new Credits(king), app.getSpriteLoader()));
                    break;
                case NONE:
                    if (noneCount == 1) {
                        noneCount = 0;
                        if (king.getState() == King.PlayerState.RUNNING || king.getState() == King.PlayerState.FALLING) {
                            king.setState(King.PlayerState.IDLE);
                        }
                    }
                    noneCount++;
                    break;
                default:
                    break;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Queue<Position> getJumpPositions() {
        return jumpPositions;
    }

    public void handleFalling(GUI gui) throws IOException, InterruptedException {
        int refreshRate = 15; // Adjust this value to control how often the screen is refreshed
        Scene scene = (Scene) getModel();
        if (scene.isKingFalling()) {
            scene.getKing().setState(King.PlayerState.FALLING);
            scene.moveDown();
        }
        // Ensure the final position is drawn
        gui.draw();
    }
}
