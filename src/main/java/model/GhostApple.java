package model;

public class GhostApple implements AppleInterface{

    Position position;

    public GhostApple(int x, int y) {
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
        return 'G';
    }

    @Override
    public void affect(Snake snake) {
        snake.growSnake();
        snake.setGhost(true);
    }
}
