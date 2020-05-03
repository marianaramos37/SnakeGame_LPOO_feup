package rules;

import data.*;
import gui.ArenaView;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

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

    public void eatenApple(AppleInterface a, ArenaModel arena){
        int index=0;
        for(AppleInterface apple: arena.getApples()){
            if(apple.getPosition().equals(a.getPosition())) {
                arena.getApples().get(index).setPosition(new Position(ThreadLocalRandom.current().nextInt(1, arena.getWidth() - 1), ThreadLocalRandom.current().nextInt(1, arena.getHeight() - 1)));
                if(getCollidingElement(arena.getApples().get(index).getPosition(),arena.getWalls()) != null && getCollidingBody(arena.getApples().get(index).getPosition(),arena.getSnake().getPos())) {
                    arena.getApples().get(index).setPosition(new Position(ThreadLocalRandom.current().nextInt(1, arena.getWidth() - 1), ThreadLocalRandom.current().nextInt(1, arena.getHeight() - 1)));
                }
                else{
                    System.out.println("thats ok");
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
        else if (a instanceof Apple){

        }
    }

    public void checkCollisions(Position position,ArenaModel a) throws IOException {
        AppleInterface eaten = getCollidingApples(position, a.getApples());
        Wall hit = (Wall) getCollidingElement(position, a.getWalls());
        Boolean ownBody= getCollidingBody(position,a.getSnake().getPos());

        if (eaten != null) {
            snake.growSnake();
            eatenApple(eaten,a);
            scoreController.incrementScore(a.getScore());
            if(a.getScore().getScore() > a.getTopScore().getScore()){
               scoreController.incrementScore(a.getTopScore());
            }
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

    public void movement(ArenaView.COMMAND command,ArenaView.COMMAND prevcommand) throws IOException {
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

    public void starting(ArenaController c) throws NullPointerException{
        new Thread(new Runnable(){
            ArenaView.COMMAND prevcommand = null;
            ArenaView.COMMAND command = null;
            int counter;
            @Override
            public void run() {
                //lê score logo de inicio
                try {
                    scoreController.fileReader(arena.getTopScore().getFilename(),arena.getTopScore());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //comeca jogo
                while(true){
                    //System.out.println("OLAAAA");
                    try {
                        sleep(snake.getVelocidade());
                        command=gui.getCommand();
                        if(command!=null){
                            if(command==ArenaView.COMMAND.UP && prevcommand ==ArenaView.COMMAND.DOWN){command=ArenaView.COMMAND.DOWN;}
                            if(command==ArenaView.COMMAND.DOWN && prevcommand ==ArenaView.COMMAND.UP){command=ArenaView.COMMAND.UP;}
                            if(command==ArenaView.COMMAND.RIGHT && prevcommand ==ArenaView.COMMAND.LEFT){command=ArenaView.COMMAND.LEFT;}
                            if(command==ArenaView.COMMAND.LEFT && prevcommand ==ArenaView.COMMAND.RIGHT){command=ArenaView.COMMAND.RIGHT;}
                            prevcommand =command;
                        }
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                    if(!arena.getGameOver()){
                        try {
                            c.checkCollisions(arena.getSnake().getPosition(),arena);
                            if(snake.getVelocidade() != 150){
                                counter++;
                                if(counter == 100){
                                    snake.setVelocidade(150);
                                    snake.unshrink();
                                    counter=0;
                                }
                            }
                            c.movement(command, prevcommand);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        gui.drawArena(arena);
                    }
                    else{
                        gui.drawGameOver(arena);
                        try {
                            //jogo acabou , atualiza topScore
                            scoreController.fileWriter(arena.getTopScore().getFilename(),arena.getTopScore());
                            sleep(1000);
                            gui.screen.stopScreen();
                            break;
                        } catch (InterruptedException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }


   /* Timer timer1 = new Timer();
    public void start() throws IOException {
        int delay = 1000;   // delay de 2 seg.
        int interval = 100;  // intervalo de 1 seg.
        timer1.scheduleAtFixedRate(new TimerTask() {
            ArenaView.COMMAND prevcommand = null;
            ArenaView.COMMAND command = null;
            public void run() {
                try {
                    command=gui.getCommand();
                    if(command!=null){
                        if(command==ArenaView.COMMAND.UP && prevcommand==ArenaView.COMMAND.DOWN){command=ArenaView.COMMAND.DOWN;}
                        if(command==ArenaView.COMMAND.DOWN && prevcommand==ArenaView.COMMAND.UP){command=ArenaView.COMMAND.UP;}
                        if(command==ArenaView.COMMAND.RIGHT && prevcommand==ArenaView.COMMAND.LEFT){command=ArenaView.COMMAND.LEFT;}
                        if(command==ArenaView.COMMAND.LEFT && prevcommand==ArenaView.COMMAND.RIGHT){command=ArenaView.COMMAND.RIGHT;}
                        prevcommand=command;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(!arena.getGameOver()){
                    ArenaController c=new ArenaController(gui,arena);
                    c.movement(command,prevcommand);
                    arena.checkCollisions(arena.getSnake().getHeadPosition());
                    gui.drawArena(arena);
                }
                else{
                    gui.drawGameOver(arena);
                }
            }
        }, delay, interval);
    }*/
}
