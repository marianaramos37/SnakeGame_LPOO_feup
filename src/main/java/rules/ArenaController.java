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

    public void movement(ArenaView.COMMAND command,ArenaView.COMMAND prevcommand){
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
    }

    Timer timer1 = new Timer();
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
                        if(command==ArenaView.COMMAND.UP&&prevcommand==ArenaView.COMMAND.DOWN){command=ArenaView.COMMAND.DOWN;}
                        if(command==ArenaView.COMMAND.DOWN&&prevcommand==ArenaView.COMMAND.UP){command=ArenaView.COMMAND.UP;}
                        if(command==ArenaView.COMMAND.RIGHT&&prevcommand==ArenaView.COMMAND.LEFT){command=ArenaView.COMMAND.LEFT;}
                        if(command==ArenaView.COMMAND.LEFT&&prevcommand==ArenaView.COMMAND.RIGHT){command=ArenaView.COMMAND.RIGHT;}
                        prevcommand=command;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ArenaController c=new ArenaController(gui,arena); //DUVIDA!!!!!!
                c.movement(command,prevcommand);
                arena.checkCollisions(arena.getSnake().getHeadPosition());
                gui.drawArena(arena);
            }
        }, delay, interval);
    }
}
