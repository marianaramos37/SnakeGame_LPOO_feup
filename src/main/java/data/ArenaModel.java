package data;

import filereaders.CharacterReader;

import java.io.IOException;
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
    private SinglePlayerTopScore topScore = new SinglePlayerTopScore();

    private boolean game_over;

    public ArenaModel(int width, int height) throws IOException {
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


    public ArenaModel(int width, int height,String filename) throws IOException {
        this.width = width;
        this.height = height;
        this.snake = new Snake(new Position(width / 2, height / 2));
        Apple apple = new Apple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        SpecialApple appleS = new SpecialApple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        this.apples.add(apple);
        this.apples.add(appleS);

        try {
            CharacterReader c=new CharacterReader(filename);
            walls=c.getWallsRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public List<Wall> getWalls(){
        return this.walls;
    }

    public List<AppleInterface> getApples(){return this.apples;}

    public SinglePlayerTopScore getTopScore(){return this.topScore;}

    public void setSnake(Snake s){this.snake=s;}

    public void setWalls(List<Wall> l){this.walls=l;}

    public void setApples(List<AppleInterface> l){this.apples=l;}

    public void setScore(SinglePlayerScore s){this.score=s;}

    public void setSnakeHeadPosition(Position position) {
        snake.setHeadPosition(position);
    }

    public boolean getGameOver(){
        return game_over;
    }

    public void endGame() throws IOException {
        this.game_over=true;

       // topScore.controller.fileWriter(topScore.getFilename(),topScore);

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


}