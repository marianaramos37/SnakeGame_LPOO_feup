package data;

public class Apple implements AppleInterface{
    Position position;

    public Apple(int x, int y) {
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
    public Character getChar() {
        return 'O';
    }

}
