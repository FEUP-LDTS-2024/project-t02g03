package jumpking.view.screens;

import com.googlecode.lanterna.TextColor;
import jumpking.gui.GUI;
import jumpking.model.Position;
import jumpking.model.credits.Credits;
import jumpking.view.ViewProvider;
import jumpking.view.images.*;
import jumpking.view.menu.LogoViewer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
        drawStatistics(gui);
        drawNames(gui);
        drawMessages(gui);
        gui.refresh();
    }

    public void drawMessages(GUI gui){
        gui.drawTextImage(new Position(47,10), gameOverText.getgameOverText(), TextColor.Factory.fromString("#000000"),true);
    }

    public void drawNames(GUI gui){
        gui.drawTextImage(new Position(230,225), namesText.getnamesText(), TextColor.Factory.fromString("#000000"),true);
    }

    public void drawStatistics(GUI gui){
        gui.drawTextImage(new Position(256,117), jumpsText.getjumpsText(), TextColor.Factory.fromString("#000000"),true);
        gui.drawTextImage(new Position(262,148), timeText.gettimeText(), TextColor.Factory.fromString("#000000"),true);
        List<Integer>digitsJumps = getDigitsJumps();
        List<Integer>digitsTime = getDigitsTime();

        Position position = new Position(261,130);
        int space = 10;
        for(int digitjump : digitsJumps){
            drawDigit(digitjump,position,gui);
            position.setPosition(position.getX()+space, position.getY());
        }
        position.setPosition(258,161);
        for(int digitTime : digitsTime){
            drawDigit(digitTime,position,gui);
            if(digitTime != 10)
            position.setPosition(position.getX()+space, position.getY());
            else
            position.setPosition(position.getX()+6, position.getY());
        }
    }

    List<Integer> getDigitsJumps(){
        int jumps = getModel().getJumps();
        return Arrays.asList(jumps/1000, jumps/100%10, jumps/10%10, jumps%10);
    }

    List<Integer> getDigitsTime(){
        int minutes = getModel().getMinutes();
        int seconds = getModel().getSeconds();
        return Arrays.asList(minutes/10, minutes%10,10,seconds/10, seconds%10);
    }

    public void drawDigit(int digit,Position position,GUI gui){
        switch(digit){
            case 0:
                gui.drawTextImage(position, numberstext.getZero(), TextColor.Factory.fromString("#000000"),true);
                break;
            case 1:
                gui.drawTextImage(position, numberstext.getOne(), TextColor.Factory.fromString("#000000"),true);
                break;
            case 2:
                gui.drawTextImage(position, numberstext.getTwo(), TextColor.Factory.fromString("#000000"),true);
                break;
            case 3:
                gui.drawTextImage(position, numberstext.getThree(), TextColor.Factory.fromString("#000000"),true);
                break;
            case 4:
                gui.drawTextImage(position, numberstext.getFour(), TextColor.Factory.fromString("#000000"),true);
                break;
            case 5:
                gui.drawTextImage(position, numberstext.getFive(), TextColor.Factory.fromString("#000000"),true);
                break;
            case 6:
                gui.drawTextImage(position, numberstext.getSix(), TextColor.Factory.fromString("#000000"),true);
                break;
            case 7:
                gui.drawTextImage(position, numberstext.getSeven(), TextColor.Factory.fromString("#000000"),true);
                break;
            case 8:
                gui.drawTextImage(position, numberstext.getEight(), TextColor.Factory.fromString("#000000"),true);
                break;
            case 9:
                gui.drawTextImage(position, numberstext.getNine(), TextColor.Factory.fromString("#000000"),true);
                break;
            case 10:
                gui.drawTextImage(position, numberstext.getColon(), TextColor.Factory.fromString("#000000"),true);
                break;
        }
    }
}