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

    Timer timer1 = new Timer();
    public void start() throws IOException {
        int delay = 1000;   // delay de 2 seg.
        int interval = 100;  // intervalo de 1 seg.
        timer1.scheduleAtFixedRate(new TimerTask() {
            ArenaView.COMMAND prevcommand = null;
            ArenaView.COMMAND command = null;
            ArenaView.COMMAND lastmove=null;
            public void run() {

                try {
                    command=gui.getCommand();
                    if(command!=null){
                        prevcommand=command;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (command == ArenaView.COMMAND.UP && lastmove != ArenaView.COMMAND.DOWN) {
                    arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().up());
                    arena.getSnake().walkSnake(arena.getSnakeHeadPosition(),'|');
                    lastmove=command;
                }
                if (command == ArenaView.COMMAND.RIGHT && lastmove != ArenaView.COMMAND.LEFT) {
                    arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().right());
                    arena.getSnake().walkSnake(arena.getSnakeHeadPosition(), '-');
                    lastmove=command;
                }
                if (command == ArenaView.COMMAND.DOWN && lastmove != ArenaView.COMMAND.UP) {
                    arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().down());
                    arena.getSnake().walkSnake(arena.getSnakeHeadPosition(), '|');
                    lastmove=command;
                }
                if (command == ArenaView.COMMAND.LEFT && lastmove != ArenaView.COMMAND.RIGHT) {
                    arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().left());
                    arena.getSnake().walkSnake(arena.getSnakeHeadPosition(), '-');
                    lastmove=command;
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


                arena.checkCollisions(arena.getSnakeHeadPosition());
                gui.drawArena(arena);
            }
        }, delay, interval);
    }

}
