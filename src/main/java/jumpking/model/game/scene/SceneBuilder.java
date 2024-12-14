package jumpking.model.game.scene;

import com.googlecode.lanterna.graphics.BasicTextImage;
import jumpking.model.game.elements.Block;
import jumpking.model.game.elements.Princess;
import jumpking.model.game.elements.King;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SceneBuilder {

    private final int sceneCode;
    private final List<String> lines;
    private BasicTextImage backgroundImage;

    public SceneBuilder(int sceneCode) throws IOException {
        this.sceneCode = sceneCode;
        InputStream resource = getClass().getClassLoader().getResourceAsStream("levels/scene4.txt");
        if (resource == null){
            throw new FileNotFoundException("Level file not found!");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
        lines = readLines(reader);
        loadBackgroundImage();
    }

    private void loadBackgroundImage() throws IOException {
        BackgroundImageLoader loader = new BackgroundImageLoader();
        this.backgroundImage = loader.loadBackgroundImage("backgrounds/scene4.png");
    }

    private List<String> readLines(BufferedReader bufferedReader) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = bufferedReader.readLine()) != null; )
            lines.add(line);
        return lines;
    }

    public Scene buildScene(King king) {
        Scene scene = new Scene(sceneCode);
        scene.setKing(createKing(scene, king));
        scene.setBlocks(buildBlocks());
        scene.setPrincess(createPrincess());
        scene.setStartingPosition(scene.getKing().getPosition());
        scene.setBackgroundImage(backgroundImage);
        return scene;
    }

    private Block[] buildBlocks() {
        List<Block> blocks = new ArrayList<>();
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                char ch = line.charAt(x);
                if (ch == '#' || ch == '-') {
                    blocks.add(new Block(x, y, ch));
                }
            }
            // Add extra width for the left wall
            blocks.add(new Block(-1, y, '#'));
            blocks.add(new Block(-2, y, '#'));
            // Add extra width for the right wall
            blocks.add(new Block(line.length(), y, '#'));
            blocks.add(new Block(line.length() + 1, y, '#'));
        }
        return blocks.toArray(new Block[0]);
    }

    private King createKing(Scene scene, King king) {
        return king;
    }

    private Princess createPrincess() {
        return new Princess(0, 0);
    }



}
