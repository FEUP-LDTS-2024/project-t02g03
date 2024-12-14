package jumpking.view.screens;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import jumpking.gui.GUI;
import jumpking.model.Position;
import jumpking.model.credits.Credits;
import jumpking.view.ViewProvider;
import jumpking.view.images.jumpsText;
import jumpking.view.images.namesText;
import jumpking.view.images.timeText;
import jumpking.view.menu.LogoViewer;

import java.io.IOException;


public class CreditsViewer<T extends Credits> extends ScreenViewer<T> {
    private final LogoViewer logoViewer;

    public CreditsViewer(T model, ViewProvider viewProvider){
        super(model);
        this.logoViewer = viewProvider.getLogoViewer();
    }

    @Override
    public void draw(GUI gui, long time) throws IOException {
        gui.clear();
        drawBackground(gui);
        logoViewer.draw(gui, 70, 50);
        drawStatistics(gui);
        drawNames(gui);
        drawMessages(gui);
        gui.refresh();
    }
    //valores provisórios
    public void drawMessages(GUI gui){

    }

    public void drawNames(GUI gui){
        gui.drawTextImage(new Position(20,20), namesText.getnamesText(), TextColor.Factory.fromString("#FFFFFF"));
    }

    public void drawStatistics(GUI gui){
        gui.drawTextImage(new Position(0,0), jumpsText.getjumpsText(), TextColor.Factory.fromString("#FFFFFF"));
        gui.drawTextImage(new Position(10,10), timeText.gettimeText(), TextColor.Factory.fromString("#FFFFFF"));
    }
}
