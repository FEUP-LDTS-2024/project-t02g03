package jumpking.model.game.elements.king;

import jumpking.model.Position;
import jumpking.model.game.elements.Element;

public class King extends Element{

    private static final int maxHeightJump = 100;
    private static final int maxLengthJump = 180;

    private boolean isJumping = false;
    private boolean isFalling = false;
    private boolean isRebounding = false;
    private boolean isRunning = false;
    //private Scene scene;
    private int jumps;
    private long startTime;

    public King(int x, int y /*, Scene scene*/) {
        super(x, y);
        //this.state = new IdleState(this);
        //this.scene = scene;
        this.jumps = 0;
        this.startTime = System.currentTimeMillis();
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

//    public Scene getScene() {
//        return scene;
//    }
//
//    public void setScene(Scene scene) {
//        this.scene = scene;
//    }

    public void increaseJumps() {
        jumps++;
    }

    public int getJumps() {
        return jumps;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long birthTime) {this.startTime = startTime; }

}
