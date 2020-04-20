package data;

public class ArenaModel {
    private int width;
    private int height;
    private Snake snake;

    public ArenaModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake(new Position(width / 2, height / 2));
    }

    public Position getSnakePosition() {
        return snake.getPosition();
    }

    public void setSnakePosition(Position position) {
        snake.setPosition(position);
    }
}