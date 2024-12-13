package jumpking.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.screen.Screen;
import jumpking.model.Position;
import jumpking.view.screens.ScreenViewer;

import java.io.IOException;

public interface GUI {
    enum Act {LEFT, UP, RIGHT, DOWN, QUIT, SELECT, JUMP, PAUSE, NONE};
    Screen getScreen();
    void drawPixel(Position position, TextColor color);
    void drawImage(BasicTextImage image);
    void drawTextImage(Position position, String[] image, TextColor color);
    void drawLine(Position position, String imageline,TextColor color);
    void clear();
    Act getNextAction();
    void refresh() throws IOException;
    void close() throws IOException;
    void draw() throws IOException;

}
