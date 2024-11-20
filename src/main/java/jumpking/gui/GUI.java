package jumpking.gui;

import com.googlecode.lanterna.TextColor;
import jumpking.model.Position;

import java.io.IOException;

public interface GUI {

    void drawPixel(Position position, TextColor color);
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;

}
