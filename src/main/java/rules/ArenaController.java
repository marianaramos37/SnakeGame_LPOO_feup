package rules;

import data.ArenaModel;
import gui.ArenaView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ArenaController {
    private ArenaView gui;
    private ArenaModel arena;

    public ArenaController(ArenaView gui, ArenaModel arena) {
        this.gui = gui;
        this.arena = arena;
    }


    /*public void start() throws IOException {
        gui.screen.setCharacter(arena.getSnakeHeadPosition().getX(), arena.getSnakeHeadPosition().getY(), new TextCharacter('-'));
        gui.screen.setCharacter(arena.getSnakeHeadPosition().getX()-1, arena.getSnakeHeadPosition().getY(), new TextCharacter('-'));

       while (true) {

            ArenaView.COMMAND command = gui.getCommand();

            if (command == ArenaView.COMMAND.UP) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().up());
                arena.getSnake().walkSnake(arena.getSnakeHeadPosition(),'|');
            }
            if (command == ArenaView.COMMAND.RIGHT) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().right());
                arena.getSnake().walkSnake(arena.getSnakeHeadPosition(), '-');
            }
            if (command == ArenaView.COMMAND.DOWN) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().down());
                arena.getSnake().walkSnake(arena.getSnakeHeadPosition(), '|');
            }
            if (command == ArenaView.COMMAND.LEFT) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().left());
                arena.getSnake().walkSnake(arena.getSnakeHeadPosition(), '-');
            }
            arena.getSnake().drawSnake(gui.screen);
            gui.drawArena(arena);

        }
    }*/

    Timer timer1 = new Timer();
    public void Tarefa1() throws IOException {
        int delay = 1000;   // delay de 2 seg.
        int interval = 100;  // intervalo de 1 seg.
        timer1.scheduleAtFixedRate(new TimerTask() {
            ArenaView.COMMAND prevcommand = null;
            ArenaView.COMMAND command = null;
            public void run() {
                try {
                    command=gui.getCommand();
                    if(command!=null){
                        prevcommand=command;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (command == ArenaView.COMMAND.UP) {
                    arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().up());
                    arena.getSnake().walkSnake(arena.getSnakeHeadPosition(),'|');
                }
                if (command == ArenaView.COMMAND.RIGHT) {
                    arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().right());
                    arena.getSnake().walkSnake(arena.getSnakeHeadPosition(), '-');
                }
                if (command == ArenaView.COMMAND.DOWN) {
                    arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().down());
                    arena.getSnake().walkSnake(arena.getSnakeHeadPosition(), '|');
                }
                if (command == ArenaView.COMMAND.LEFT) {
                    arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().left());
                    arena.getSnake().walkSnake(arena.getSnakeHeadPosition(), '-');
                }
                if(command==null){
                    if (prevcommand == ArenaView.COMMAND.UP) {
                        arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().up());
                        arena.getSnake().walkSnake(arena.getSnakeHeadPosition(),'|');
                    }
                    if (prevcommand == ArenaView.COMMAND.RIGHT) {
                        arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().right());
                        arena.getSnake().walkSnake(arena.getSnakeHeadPosition(), '-');
                    }
                    if (prevcommand == ArenaView.COMMAND.DOWN) {
                        arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().down());
                        arena.getSnake().walkSnake(arena.getSnakeHeadPosition(), '|');
                    }
                    if (prevcommand == ArenaView.COMMAND.LEFT) {
                        arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().left());
                        arena.getSnake().walkSnake(arena.getSnakeHeadPosition(), '-');
                    }
                }
                arena.checkCollisions(arena.getSnake().getPosition());
                gui.drawArena(arena);
            }
        }, delay, interval);
    }

}
