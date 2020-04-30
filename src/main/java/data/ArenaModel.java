package data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ArenaModel {
    private int width;
    private int height;

    private Snake snake;
    private List<Wall> walls= new ArrayList<>();
    private List<AppleInterface> apples=new ArrayList<>();
    private SinglePlayerScore score = new SinglePlayerScore();

    private boolean game_over;

    public ArenaModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake(new Position(width / 2, height / 2));
        Apple apple = new Apple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        SpecialApple appleS = new SpecialApple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        this.apples.add(apple);
        this.apples.add(appleS);
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

    public void setSnake(Snake s){this.snake=s;}

    public void setSnakeHeadPosition(Position position) {
        snake.setHeadPosition(position);
    }

    public boolean getGameOver(){
        return game_over;
    }

    public void endGame(){this.game_over=true;}

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

    public List<AppleInterface> getApples(){return this.apples;}

}