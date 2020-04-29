package data;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Wall extends Element {

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Screen screen) {
        screen.setCharacter(this.getPosition().getX(),this.getPosition().getY(), new TextCharacter('#'));
    }
}
