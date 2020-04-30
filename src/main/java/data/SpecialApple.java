package data;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class SpecialApple implements AppleInterface{
    Position position;

    public SpecialApple(int x, int y) {
        this.position = new Position(x, y);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
    }

    @Override
    public void draw(Screen screen) {
        screen.setCharacter(this.getPosition().getX(), this.getPosition().getY(), new TextCharacter('S'));
    }

}
