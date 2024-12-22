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
import java.util.HashMap;
import java.util.List;

public class CreditsViewer<T extends Credits> extends ScreenViewer<T> {

    private final LogoViewer logoViewer;
    private HashMap<Integer,String[]> digitTexts;

    public CreditsViewer(T model, ViewProvider viewProvider){
        super(model);
        this.logoViewer = viewProvider.getLogoViewer();
        initializeDigitTexts();
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
        List<Integer>digitsjumps = getDigitsJumps();
        List<Integer>digitstime = getDigitsTime();

        Position position = new Position(261,130);
        int space = 10;
        for(int digitjump : digitsjumps){
            drawdigit(digitjump,position,gui);
            position.setPosition(position.getX()+space, position.getY());
        }
        position.setPosition(258,161);
        for(int digittime : digitstime){
            drawdigit(digittime,position,gui);
            if(digittime!=10)
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
    public void initializeDigitTexts() {
        digitTexts = new HashMap<Integer,String[]>();
        digitTexts.put(0, numberstext.getZero());
        digitTexts.put(1, numberstext.getOne());
        digitTexts.put(2, numberstext.getTwo());
        digitTexts.put(3, numberstext.getThree());
        digitTexts.put(4, numberstext.getFour());
        digitTexts.put(5, numberstext.getFive());
        digitTexts.put(6, numberstext.getSix());
        digitTexts.put(7, numberstext.getSeven());
        digitTexts.put(8, numberstext.getEight());
        digitTexts.put(9, numberstext.getNine());
        digitTexts.put(10, numberstext.getColon());
    }

    public void drawdigit(int digit, Position position, GUI gui) {
        String[] text = digitTexts.get(digit);
        if (text != null) {
            gui.drawTextImage(position, text, TextColor.Factory.fromString("#000000"), true);
        }
    }

}