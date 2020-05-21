package model;

import filereaders.MapReader;

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
        PoisonedApple appleP= new PoisonedApple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        this.apples.add(apple);
        this.apples.add(appleS);
        this.apples.add(appleP);
        this.game_over=false;

    }


    public ArenaModel(int width, int height,String filename) throws IOException {
        this.width = width;
        this.height = height;
        this.snake = new Snake(new Position(width / 2, height / 2));
        Apple apple = new Apple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        SpecialApple appleS = new SpecialApple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        PoisonedApple appleP= new PoisonedApple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        this.apples.add(apple);
        this.apples.add(appleS);
        this.apples.add(appleP);
        try {
            MapReader c=new MapReader(filename);
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
        return snake.getPosition();
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
        snake.setPosition(position);
    }



    public boolean getGameOver(){
        return game_over;
    }

    public void endGame() throws IOException {
        this.game_over=true;
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


    public void randomWalls(){
        Wall w;

        w = new Wall (ThreadLocalRandom.current().nextInt(1, getWidth() - 1), ThreadLocalRandom.current().nextInt(1, getHeight() - 1));
        while(getCollidingElement(w.getPosition()) != null || getCollidingBody(w.getPosition()) || getCollidingApples(w.getPosition()) != null) {
            w = new Wall (ThreadLocalRandom.current().nextInt(1, getWidth() - 1), ThreadLocalRandom.current().nextInt(1, getHeight() - 1));
        }

        List<Wall> newWalls = getWalls();
        newWalls.add(w);
        setWalls(newWalls);


    }

    public AppleInterface getCollidingApples(Position position) {
        for (AppleInterface apple : this.getApples())
            if (apple.getPosition().equals(position))
                return apple;
        return null;
    }

    public Element getCollidingElement(Position position) {
        for (Element element : this.getWalls())
            if (element.getPosition().equals(position))
                return element;
        return null;
    }

    public boolean getCollidingBody(Position position){
        if(!getSnake().getShrink()) {
            for (int i = 1; i < this.getSnake().getPos().size(); i++)
                if (this.getSnake().getPos().get(i).equals(position))
                    return true;
        }
        return false;
    }


    public void eatenApple(AppleInterface a){
        int index=0;
        for(AppleInterface apple: getApples()){
            if(apple.getPosition().equals(a.getPosition())) {
                getApples().get(index).setPosition(new Position(ThreadLocalRandom.current().nextInt(1, getWidth() - 1), ThreadLocalRandom.current().nextInt(1, getHeight() - 1)));
                while(getCollidingElement(getApples().get(index).getPosition()) != null || getCollidingBody(getApples().get(index).getPosition())) {
                    getApples().get(index).setPosition(new Position(ThreadLocalRandom.current().nextInt(1, getWidth() - 1), ThreadLocalRandom.current().nextInt(1, getHeight() - 1)));
                }
                break;
            }
            index++;
        }
        if(a instanceof SpecialApple){
            System.out.println("SPECIAL");
            this.getSnake().setVelocidade(this.snake.getVelocidade()/2);
            this.getSnake().shrink();
        }
        else if(a instanceof PoisonedApple){
            this.getSnake().poison();
            if(getScore().getScore() <= 5){
                setScore(new SinglePlayerScore());
            }else{
                setScore(new SinglePlayerScore(getScore().getScore()-5));
            }
            score.updatePrintable(score);
            return;
        }

        score.incrementScore(score);
        if (score.getScore() > topScore.getScore()) {
            topScore.incrementScore(topScore);
        }
        if(!getSnake().getShrink()){
            this.updateVelocidadeSnake();
        }

    }

    public void checkCollisions(Position position) throws IOException {
        AppleInterface eaten = getCollidingApples(position);
        Wall hit = (Wall) getCollidingElement(position);
        Boolean ownBody= getCollidingBody(position);

        if (eaten != null && !(eaten instanceof PoisonedApple)) {
            this.growSnake();
            eatenApple(eaten);
        }
        if (eaten != null && eaten instanceof PoisonedApple){
            eatenApple(eaten);
        }
        if(hit != null){
            this.endGame();
        }
        if(ownBody){
            this.endGame();
        }
    }

    public void updateVelocidadeSnake(){
        if(this.getScore().getScore() % 5 == 0 && this.getScore().getScore() != 0 && this.snake.getVelocidade() > 90){
            this.snake.setVelocidade(this.snake.getVelocidade()-5);
        }
    }

    public void growSnake(){
        List<Character> snakebody = this.snake.getSnakeBody();
        List<Position> pos = this.snake.getPos();
        int length=this.snake.getLength();

        snake.setLength(length+1);

        if(snakebody.get(snake.getLength() - 1) == '-'){
            snakebody.add(snake.getLength() ,'-');

            if (pos.get(snake.getLength() - 2).getX() + 1 == pos.get(snake.getLength() - 1).getX()) {
                pos.add(snake.getLength(), new Position(pos.get(snake.getLength() - 1).getX() + 1, pos.get(snake.getLength() - 1).getY()));
            }
            if (pos.get(snake.getLength() - 2).getX() - 1 == pos.get(snake.getLength() - 1).getX()) {
                pos.add(snake.getLength(), new Position(pos.get(snake.getLength() - 1).getX() - 1, pos.get(snake.getLength() - 1).getY()));
            }


        }else{
            snakebody.add(snake.getLength() ,'|');

            if(pos.get(snake.getLength() -2).getY()+1 == pos.get(snake.getLength() -1).getY()){
                pos.add(snake.getLength() ,new Position(pos.get(snake.getLength() -1).getX(),pos.get(snake.getLength() -1).getY()+1));
            }
            if(pos.get(snake.getLength() -2).getY()-1 == pos.get(snake.getLength() -1).getY()){
                pos.add(snake.getLength() ,new Position(pos.get(snake.getLength() -1).getX(),pos.get(snake.getLength() -1).getY()-1));
            }
        }

        snake.setSnakeBody(snakebody);
        snake.setPos(pos);
    }

    public void walkSnake(Position nextPosition, Character headOrientation, Snake snake){
        List<Character> snakebody = this.snake.getSnakeBody();
        List<Position> pos = this.snake.getPos();

        Position previous;
        int index=0;
        for(Position p:pos){
            previous=p;
            pos.set(index,nextPosition);
            nextPosition=previous;
            index++;
        }
        index=0;
        Character previousOrientation;
        for(Character c:snakebody){
            previousOrientation=c;
            snakebody.set(index,headOrientation);
            headOrientation=previousOrientation;
            index++;
        }

        this.snake.setSnakeBody(snakebody);
        this.snake.setPos(pos);
    }

}