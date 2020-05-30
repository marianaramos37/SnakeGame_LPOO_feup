package model;

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
    public Character getChar() {
        return 'S';
    }

    @Override
    public void affect(Snake snake) {
        snake.setVelocidade(snake.getVelocidade()/2);
        snake.shrink();
    }


}
