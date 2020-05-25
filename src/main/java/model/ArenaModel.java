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
    private Snake snake2;
    private List<Wall> walls= new ArrayList<>();
    private List<Obstaculo> obstaculos= new ArrayList<>();
    private List<AppleInterface> apples=new ArrayList<>();

    private SinglePlayerScore score = new SinglePlayerScore();
    private SinglePlayerTopScore topScore = new SinglePlayerTopScore();

    private boolean game_over;

    public ArenaModel(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.snake = new Snake(new Position(width / 2, height / 2));
        this.snake2=new Snake(new Position(3,3));
        Apple apple = new Apple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        SpecialApple appleS = new SpecialApple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        PoisonedApple appleP= new PoisonedApple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        GhostApple appleG= new GhostApple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        this.apples.add(apple);
        this.apples.add(appleS);
        this.apples.add(appleP);
        this.apples.add(appleG);
        Obstaculo obstaculo1= new Obstaculo(new Position(20,20),1);
        Obstaculo obstaculo2= new Obstaculo(new Position(20,20),2);
        obstaculos.add(obstaculo1);
        obstaculos.add(obstaculo2);
        this.game_over=false;

    }

    public ArenaModel(int width, int height,String filename) throws IOException {
        this.width = width;
        this.height = height;
        this.snake = new Snake(new Position(50, 17));
        this.snake2=new Snake(new Position(7,17));
        Apple apple = new Apple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        SpecialApple appleS = new SpecialApple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        PoisonedApple appleP= new PoisonedApple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        GhostApple appleG=new GhostApple(ThreadLocalRandom.current().nextInt(1, width-1), ThreadLocalRandom.current().nextInt(1, height-1));
        this.apples.add(apple);
        this.apples.add(appleS);
        this.apples.add(appleP);
        this.apples.add(appleG);
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
    public Snake getSnake(){return snake;}
    public List<Wall> getWalls(){
        return this.walls;
    }
    public List<Obstaculo> getObstaculos(){
        return this.obstaculos;
    }
    public List<AppleInterface> getApples(){return this.apples;}
    public SinglePlayerTopScore getTopScore(){return this.topScore;}
    public Snake getSnake2() {
        return snake2;
    }
    public Position getSnakeHeadPosition() {
        return snake.getPosition();
    }
    public Position getSnakeHead2Position() {
        return snake2.getPosition();
    }
    public void setSnake2(Snake snake2) {
        this.snake2 = snake2;
    }
    public void setSnake(Snake s){this.snake=s;}
    public void setWalls(List<Wall> l){this.walls=l;}
    public void setApples(List<AppleInterface> l){this.apples=l;}
    public void setScore(SinglePlayerScore s){this.score=s;}
    public void setSnakeHeadPosition(Position position) {
        snake.setPosition(position);
    }
    public void setSnakeHead2Position(Position position) {
        snake2.setPosition(position);
    }

    public boolean getGameOver(){
        return game_over;
    }

    public void restartGame(){
        this.game_over=false;
        this.snake = new Snake(new Position(50, 17));
        this.snake2 = new Snake(new Position(7,17));
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

        Obstaculo obstaculo;
        obstaculo=new Obstaculo(new Position(0,0),ThreadLocalRandom.current().nextInt(1, 3));
        Position randomPosition = new Position(ThreadLocalRandom.current().nextInt(1, getWidth() - 1), ThreadLocalRandom.current().nextInt(1, getHeight() - 1));
        while(getCollidingElement(randomPosition) != null || getCollidingBody(randomPosition) || getCollidingApples(randomPosition) != null) {
            randomPosition = new Position(ThreadLocalRandom.current().nextInt(1, getWidth() - 1), ThreadLocalRandom.current().nextInt(1, getHeight() - 1));
        }
        obstaculo.setPosition(randomPosition);

        obstaculos.add(obstaculo);

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
        for (Obstaculo obstaculo : this.getObstaculos())
            for (Element element : obstaculo.getObstaculo())
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

    public void checkAttack() throws IOException {

        //if the two heads collide everyone loses
        if(snake.getPosition().equals(snake2.getPosition())){
            snake.setLoser(true);
            snake2.setLoser(true);
            this.endGame();
            return;
        }

        //check attack for Snake 2
        for(int i = 1; i<snake.getPos().size();i++){
            if(snake2.getPosition().equals(snake.getPos().get(i))){
                snake2.setLoser(true);
                this.endGame();
                return;
            }
        }

        //check attack for Snake 1
        for(int i = 1; i<snake2.getPos().size();i++){
            if(snake.getPosition().equals(snake2.getPos().get(i))){
                snake.setLoser(true);
                this.endGame();
                return;
            }
        }


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
        else if(a instanceof GhostApple){
            this.getSnake().setGhost(true);
        }

        score.incrementScore(score);
        if (score.getScore() > topScore.getScore()) {
            topScore.incrementScore(topScore);
        }
        if(!getSnake().getShrink()){
            this.updateVelocidadeSnake();
        }
    }

    public void checkCollisions(Position position,Snake s) throws IOException {
        AppleInterface eaten = getCollidingApples(position);
        Wall hit = (Wall) getCollidingElement(position);
        Boolean ownBody= getCollidingBody(position);

        if(!s.isGhost()){
            if (eaten != null && !(eaten instanceof PoisonedApple)) {
                this.growSnake(s);
                eatenApple(eaten);
            }
            if (eaten instanceof PoisonedApple){
                eatenApple(eaten);
            }
            if(hit != null){
                if(s==this.getSnake()) this.getSnake().setLoser(true);
                else if(s==this.getSnake2()) this.getSnake2().setLoser(true);
                this.endGame();
            }
            if(ownBody){
                this.endGame();
            }
        }
    }

    public void updateVelocidadeSnake(){
        if(this.getScore().getScore() % 5 == 0 && this.getScore().getScore() != 0 && this.snake.getVelocidade() > 90){
            this.snake.setVelocidade(this.snake.getVelocidade()-5);
        }
    }

    public void growSnake(Snake s){
        List<Character> snakebody = s.getSnakeBody();
        List<Position> pos = s.getPos();
        int length=s.getLength();

        s.setLength(length+1);

        if(snakebody.get(s.getLength() - 1) == '-'){
            snakebody.add(s.getLength() ,'-');

            if (pos.get(s.getLength() - 2).getX() + 1 == pos.get(s.getLength() - 1).getX()) {
                pos.add(s.getLength(), new Position(pos.get(s.getLength() - 1).getX() + 1, pos.get(s.getLength() - 1).getY()));
            }
            if (pos.get(s.getLength() - 2).getX() - 1 == pos.get(s.getLength() - 1).getX()) {
                pos.add(s.getLength(), new Position(pos.get(s.getLength() - 1).getX() - 1, pos.get(s.getLength() - 1).getY()));
            }


        }else{
            snakebody.add(s.getLength() ,'|');

            if(pos.get(s.getLength() -2).getY()+1 == pos.get(s.getLength() -1).getY()){
                pos.add(s.getLength() ,new Position(pos.get(s.getLength() -1).getX(),pos.get(s.getLength() -1).getY()+1));
            }
            if(pos.get(s.getLength() -2).getY()-1 == pos.get(s.getLength() -1).getY()){
                pos.add(s.getLength() ,new Position(pos.get(s.getLength() -1).getX(),pos.get(s.getLength() -1).getY()-1));
            }
        }

        s.setSnakeBody(snakebody);
        s.setPos(pos);
    }

    public void walkSnake(Position nextPosition, Character headOrientation, Snake s){
        List<Character> snakebody = s.getSnakeBody();
        List<Position> pos = s.getPos();

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

        s.setSnakeBody(snakebody);
        s.setPos(pos);
    }
}