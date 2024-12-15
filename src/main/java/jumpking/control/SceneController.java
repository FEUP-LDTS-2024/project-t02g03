package jumpking.control;

import jumpking.Application;
import jumpking.gui.GUI;
import jumpking.model.credits.Credits;
import jumpking.model.game.scene.Scene;
import jumpking.states.CreditsState;

import java.io.IOException;

public class SceneController extends Controller<Scene> {

    private final KingController kingController;

    public SceneController(Scene scene, KingController kingController) throws IOException {
        super(scene);
        this.kingController = kingController;
    }

    @Override
    public void step(Application app, GUI.Act act, long time) throws IOException {
        kingController.step(app, act, time);
        Scene scene = (Scene) getModel();
        try {
            if (kingController.getJumpPositions().isEmpty()) {
                handleFalling(app.getGui());
            } else {
                app.getGui().draw(); // Ensure the GUI is updated to show all jump positions
            }
            if (scene.isKingOnPrincess()) {
                app.setState(new CreditsState(new Credits(scene.getKing()),app.getSpriteLoader()));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void handleFalling(GUI gui) throws IOException, InterruptedException {
        int refreshRate = 15; // Adjust this value to control how often the screen is refreshed
        int step = 0;
        Scene scene = (Scene) getModel();
        while (scene.isKingFalling()) {
            scene.moveDown();
            if (step % refreshRate == 0) {
                gui.draw();
            }
            step++;
            try {
                Thread.sleep(1); // Adjust the speed of falling as needed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Ensure the final position is drawn
        gui.draw();
    }

}