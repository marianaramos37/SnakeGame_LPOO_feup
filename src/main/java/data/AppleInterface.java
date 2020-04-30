package data;

import com.googlecode.lanterna.screen.Screen;

public interface AppleInterface {
    public Position getPosition();
    public void setPosition(Position position);
    public abstract void draw(Screen screen);

}
