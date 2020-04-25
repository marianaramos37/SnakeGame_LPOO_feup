package data;

import java.util.ArrayList;
import java.util.List;

public class ArenaModel {
    private int width;
    private int height;
    private Snake snake;
    private Apple apple;
    private List<Wall> walls;

    public ArenaModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake(new Position(width / 2, height / 2));
        this.apple = new Apple(22,22);
        this.walls = new ArrayList<>();
        buildWalls();
    }

    public Position getSnakeHeadPosition() {
        return snake.getHeadPosition();
    }

    public Snake getSnake(){return snake;}

    public void setSnakeHeadPosition(Position position) {
        snake.setHeadPosition(position);
    }

    public Position getApplePosition() {
        return apple.getPosition();
    }

    public void setApplePosition(Position position) {
        apple.setPosition(position);
    }

    public void buildWalls(){
        for(int i=0; i<width; i++){
            this.walls.add(new Wall(i,0));
            this.walls.add(new Wall(i,this.height-1));
        }
        for(int i=0; i<height; i++){
            this.walls.add(new Wall(0,i));
            this.walls.add(new Wall(this.width-1,i));
        }

    }
    public List<Wall> getWalls(){
        return this.walls;
    }

    public void checkCollisions(Position position) {
        List<Element> apples=new ArrayList<>();
        apples.add(apple);
        Snake snake = (Snake) getCollidingElement(position, apples);
        if (snake != null) {
           snake.growSnake();
           System.out.println("Colidiu");
           //apaga maça;
        }
    }

    private Element getCollidingElement(Position position, List<? extends Element> elements) {
        for (Element element : elements)
            if (element.getPosition().equals(position))
                return element;

        return null;
    }
}