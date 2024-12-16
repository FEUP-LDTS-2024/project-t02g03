package jumpking.view.menu;

import com.googlecode.lanterna.TextColor;
import jumpking.gui.GUI;
import jumpking.model.menu.Item;
import jumpking.view.images.quitText;
import jumpking.view.images.resumeText;
import jumpking.view.images.startText;

public class DrawViewer {

    public void draw(Item item, GUI gui, Item currentItem) {
        TextColor color;
        if (item.equals(currentItem)) {
            color = TextColor.Factory.fromString("#008000");
        } else {
            color = TextColor.Factory.fromString("#FFFFFF");
        }
        if (item.getType() == Item.Type.START_GAME) {
            gui.drawTextImage(item.getPosition(), startText.getStartText(), color,false);
        } else if (item.getType() == Item.Type.QUIT) {
            gui.drawTextImage(item.getPosition(), quitText.getExitText(), color,false);
        } else if (item.getType() == Item.Type.RESUME) {
            gui.drawTextImage(item.getPosition(), resumeText.getResumeText(), color,false);
        }
    }
}