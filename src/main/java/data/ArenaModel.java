package data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ArenaModel {
    private int width;
    private int height;
    private Snake snake;
    private List<Wall> walls= new ArrayList<>();
    private List<Apple> apples=new ArrayList<>();
    private SinglePlayerScore score = new SinglePlayerScore();
    private boolean game_over;

    public ArenaModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake(new Position(width / 2, height / 2));
        Apple apple = new Apple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        this.apples.add(apple);
        buildWalls();
        this.game_over=false;
    }
    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public SinglePlayerScore getScore() {
        return score;
    }

    public Position getSnakeHeadPosition() {
        return snake.getHeadPosition();
    }

    public Snake getSnake(){return snake;}

    public void setSnakeHeadPosition(Position position) {
        snake.setHeadPosition(position);
    }


    public boolean getGameOver(){
        return game_over;
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

    public List<Apple> getApples(){return this.apples;}

    public void eatenApple(Apple a){
        int index=0;
        for(Apple apple: apples){
            if(apple.getPosition().equals(a.getPosition())) {
                apples.get(index).setPosition(new Position(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1)));
                break;
            }
            index++;
        }
    }
    public void checkCollisions(Position position) {
        Apple eaten = (Apple) getCollidingElement(position, apples);
        Wall hit = (Wall) getCollidingElement(position, walls);

        if (eaten != null) {
           snake.growSnake();
           eatenApple(eaten);
           score.incrementScore();
        }
        if(hit != null){
            game_over=true;
        }
    }

    private Element getCollidingElement(Position position, List<? extends Element> elements) {
        for (Element element : elements)
            if (element.getPosition().equals(position))
                return element;
        return null;
    }
}