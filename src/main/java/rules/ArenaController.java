package rules;

import data.*;
import gui.ArenaView;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ArenaController {
    private ArenaView gui;
    private ArenaModel arena;
    private SnakeController snake;
    private ScoreController scoreController;

    public ArenaController(ArenaView gui, ArenaModel arena) {
        this.gui = gui;
        this.arena = arena;
        this.snake=new SnakeController(arena,150);
        this.scoreController=new ScoreController();
    }

    public SnakeController getSnakeController(){
        return snake;
    }
    public ArenaView getArenaView(){
        return gui;
    }
    public ArenaModel getArenaModel(){
        return arena;
    }
    public ScoreController getScoreController(){
        return scoreController;
    }

    public void setSnakeController(SnakeController s){
        this.snake=s;
    }
    public void setArenaView(ArenaView v){
        this.gui=v;
    }
    public void setArenaModel(ArenaModel m){
        this.arena=m;
    }
    public void setScoreController(ScoreController s){
        this.scoreController=s;
    }


    public void randomWalls(){
        Wall w;
        if(arena.getScore().getScore() % 10 == 0 && arena.getScore().getScore() != 0){
            w = new Wall (ThreadLocalRandom.current().nextInt(1, arena.getWidth() - 1), ThreadLocalRandom.current().nextInt(1, arena.getHeight() - 1));
            while(getCollidingElement(w.getPosition(),arena.getWalls()) != null || getCollidingBody(w.getPosition(),arena.getSnake().getPos()) || getCollidingApples(w.getPosition(),arena.getApples()) != null) {
                w = new Wall (ThreadLocalRandom.current().nextInt(1, arena.getWidth() - 1), ThreadLocalRandom.current().nextInt(1, arena.getHeight() - 1));
            }

            List<Wall> newWalls = arena.getWalls();
            newWalls.add(w);
            arena.setWalls(newWalls);
        }

    }

    public void eatenApple(AppleInterface a, ArenaModel arena){
        int index=0;
        for(AppleInterface apple: arena.getApples()){
            if(apple.getPosition().equals(a.getPosition())) {
                arena.getApples().get(index).setPosition(new Position(ThreadLocalRandom.current().nextInt(1, arena.getWidth() - 1), ThreadLocalRandom.current().nextInt(1, arena.getHeight() - 1)));
                while(getCollidingElement(arena.getApples().get(index).getPosition(),arena.getWalls()) != null || getCollidingBody(arena.getApples().get(index).getPosition(),arena.getSnake().getPos())) {
                    arena.getApples().get(index).setPosition(new Position(ThreadLocalRandom.current().nextInt(1, arena.getWidth() - 1), ThreadLocalRandom.current().nextInt(1, arena.getHeight() - 1)));
                }
                break;
            }
            index++;
        }
        if(a instanceof SpecialApple){
            System.out.println("SPECIAL");
            this.snake.setVelocidade(this.snake.getVelocidade()/2);
            this.snake.shrink();
        }
        else if(a instanceof PoisonedApple){
            this.snake.poison();
            if(arena.getScore().getScore() <= 5){
                arena.setScore(new SinglePlayerScore());
            }else{
                arena.getScore().setScore(arena.getScore().getScore()-5);
            }
            scoreController.updatePrintable(arena.getScore());
            return;
        }

        scoreController.incrementScore(arena.getScore());
        if (arena.getScore().getScore() > arena.getTopScore().getScore()) {
            scoreController.incrementScore(arena.getTopScore());
        }
        if(!arena.getSnake().getShrink()){
            snake.updateVelocidade();
        }

    }

    public void checkCollisions(Position position, ArenaModel a) throws IOException {
        AppleInterface eaten = getCollidingApples(position, a.getApples());
        Wall hit = (Wall) getCollidingElement(position, a.getWalls());
        Boolean ownBody= getCollidingBody(position,a.getSnake().getPos());

        if (eaten != null && !(eaten instanceof PoisonedApple)) {
            snake.growSnake();
            eatenApple(eaten,a);
        }
        if (eaten != null && eaten instanceof PoisonedApple){
            eatenApple(eaten,a);
        }
        if(hit != null){
            a.endGame();
        }
        if(ownBody){
            a.endGame();
        }
    }

    public AppleInterface getCollidingApples(Position position, List<AppleInterface> apples) {
        for (AppleInterface apple : apples)
            if (apple.getPosition().equals(position))
                return apple;
        return null;
    }

    public Element getCollidingElement(Position position, List<? extends Element> elements) {
        for (Element element : elements)
            if (element.getPosition().equals(position))
                return element;
        return null;
    }

    public boolean getCollidingBody(Position position, List<Position> body){
        if(!arena.getSnake().isShrink) {
            for (int i = 1; i < body.size(); i++)
                if (body.get(i).equals(position))
                    return true;
        }
        return false;
    }

    public void movement(ArenaView.COMMAND command,ArenaView.COMMAND prevcommand, ArenaModel arena, SnakeController snake) throws IOException {
        if (command == ArenaView.COMMAND.UP) {
            arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().up());
            snake.walkSnake(arena.getSnakeHeadPosition(),'|',arena.getSnake());
        }
        if (command == ArenaView.COMMAND.RIGHT) {
            arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().right());
            snake.walkSnake(arena.getSnakeHeadPosition(), '-',arena.getSnake());
        }
        if (command == ArenaView.COMMAND.DOWN) {
            arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().down());
            snake.walkSnake(arena.getSnakeHeadPosition(), '|',arena.getSnake());
        }
        if (command == ArenaView.COMMAND.LEFT) {
            arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().left());
            snake.walkSnake(arena.getSnakeHeadPosition(), '-',arena.getSnake());
        }
        if(command==ArenaView.COMMAND.ESC){
            arena.endGame();
        }
        if(command==null){
            if (prevcommand == ArenaView.COMMAND.UP) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().up());
                snake.walkSnake(arena.getSnakeHeadPosition(),'|',arena.getSnake());
            }
            if (prevcommand == ArenaView.COMMAND.RIGHT) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().right());
                snake.walkSnake(arena.getSnakeHeadPosition(), '-',arena.getSnake());
            }
            if (prevcommand == ArenaView.COMMAND.DOWN) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().down());
                snake.walkSnake(arena.getSnakeHeadPosition(), '|',arena.getSnake());
            }
            if (prevcommand == ArenaView.COMMAND.LEFT) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().left());
                snake.walkSnake(arena.getSnakeHeadPosition(), '-',arena.getSnake());
            }
            if(prevcommand==ArenaView.COMMAND.ESC){
                arena.endGame();
            }
        }
    }

}
