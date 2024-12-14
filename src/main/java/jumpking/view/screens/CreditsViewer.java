package jumpking.view.screens;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import jumpking.gui.GUI;
import jumpking.model.Position;
import jumpking.model.credits.Credits;
import jumpking.view.ViewProvider;
import jumpking.view.images.*;
import jumpking.view.menu.LogoViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        logoViewer.draw(gui, 70, 50);
        drawStatistics(gui);
        drawNames(gui);
        drawMessages(gui);
        gui.refresh();
    }

    //valores provisórios
    public void drawMessages(GUI gui){
        gui.drawTextImage(new Position(20,40), gameOverText.getgameOverText(), TextColor.Factory.fromString("#FFFFFF"));
    }

    public void drawNames(GUI gui){
        gui.drawTextImage(new Position(20,20), namesText.getnamesText(), TextColor.Factory.fromString("#FFFFFF"));
    }

    public void drawStatistics(GUI gui){
        gui.drawTextImage(new Position(0,0), jumpsText.getjumpsText(), TextColor.Factory.fromString("#FFFFFF"));
        gui.drawTextImage(new Position(10,10), timeText.gettimeText(), TextColor.Factory.fromString("#FFFFFF"));

        List<Integer>digitsjumps = getDigitsJumps();
        List<Integer>digitstime = getDigitsTime();
        Position position = new Position(30,30);
        for(int digitjump : digitsjumps){
            drawdigit(digitjump,position,gui);
            position.setPosition(31,30);
        }
        position.setPosition(40,40);
        for(int digittime : digitstime){
            drawdigit(digittime,position,gui);
            position.setPosition(41,40);
        }
    }

    List<Integer> getDigitsJumps(){
        int jumps = getModel().getJumps();
        return Arrays.asList(jumps/1000, jumps/100%10, jumps/10%10, jumps%10);
    }

    List<Integer> getDigitsTime(){
        int minutes = getModel().getMinutes();
        int seconds = getModel().getSeconds();
        return Arrays.asList(minutes/10, minutes%10, seconds/10, seconds%10);
    }

    public void drawdigit(int digit,Position position,GUI gui){
        switch(digit){
            case 0:
                gui.drawTextImage(position, numberstext.getZero(), TextColor.Factory.fromString("#FFFFFF"));
                break;
            case 1:
                gui.drawTextImage(position, numberstext.getOne(), TextColor.Factory.fromString("#FFFFFF"));
                break;
            case 2:
                gui.drawTextImage(position, numberstext.getTwo(), TextColor.Factory.fromString("#FFFFFF"));
                break;
            case 3:
                gui.drawTextImage(position, numberstext.getThree(), TextColor.Factory.fromString("#FFFFFF"));
                break;
            case 4:
                gui.drawTextImage(position, numberstext.getFour(), TextColor.Factory.fromString("#FFFFFF"));
                break;
            case 5:
                gui.drawTextImage(position, numberstext.getFive(), TextColor.Factory.fromString("#FFFFFF"));
                break;
            case 6:
                gui.drawTextImage(position, numberstext.getSix(), TextColor.Factory.fromString("#FFFFFF"));
                break;
            case 7:
                gui.drawTextImage(position, numberstext.getSeven(), TextColor.Factory.fromString("#FFFFFF"));
                break;
            case 8:
                gui.drawTextImage(position, numberstext.getEight(), TextColor.Factory.fromString("#FFFFFF"));
                break;
            case 9:
                gui.drawTextImage(position, numberstext.getNine(), TextColor.Factory.fromString("#FFFFFF"));
                break;
        }
    }
}