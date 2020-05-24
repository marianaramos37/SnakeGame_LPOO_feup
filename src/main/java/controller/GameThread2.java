package controller;

import model.ArenaModel;
import view.ArenaView;

import java.io.IOException;

public class GameThread2 extends Thread {
    private ArenaController arenaController;
    private ArenaView gui;
    private ArenaModel arena;

    public GameThread2(ArenaController cntr){
        arenaController=cntr;
        gui=cntr.getArenaView();
        arena=cntr.getArenaModel();
    }

    @Override
    public void run() {
        try {
            arena.buildWalls();

            ArenaView.COMMAND prevcommand1 = null;
            ArenaView.COMMAND prevcommand2=null;
            ArenaView.COMMAND command = null;

            while (true) {
                Thread.sleep(arena.getSnake2().getVelocidade());

                command=gui.getCommand();

                if (command != null) {
                    if (command == ArenaView.COMMAND.UP && prevcommand1 == ArenaView.COMMAND.DOWN) {
                        command = ArenaView.COMMAND.DOWN;
                    }
                    if (command == ArenaView.COMMAND.DOWN && prevcommand1 == ArenaView.COMMAND.UP) {
                        command = ArenaView.COMMAND.UP;
                    }
                    if (command == ArenaView.COMMAND.RIGHT && prevcommand1 == ArenaView.COMMAND.LEFT) {
                        command = ArenaView.COMMAND.LEFT;
                    }
                    if (command == ArenaView.COMMAND.LEFT && prevcommand1 == ArenaView.COMMAND.RIGHT) {
                        command = ArenaView.COMMAND.RIGHT;
                    }
                    if (command == ArenaView.COMMAND.UP2 && prevcommand2 == ArenaView.COMMAND.DOWN2) {
                        command = ArenaView.COMMAND.DOWN2;
                    }
                    if (command == ArenaView.COMMAND.DOWN2 && prevcommand2 == ArenaView.COMMAND.UP2) {
                        command = ArenaView.COMMAND.UP2;
                    }
                    if (command == ArenaView.COMMAND.RIGHT2 && prevcommand2 == ArenaView.COMMAND.LEFT2) {
                        command = ArenaView.COMMAND.LEFT2;
                    }
                    if (command == ArenaView.COMMAND.LEFT2 && prevcommand2 == ArenaView.COMMAND.RIGHT2) {
                        command = ArenaView.COMMAND.RIGHT2;
                    }
                    if(command==ArenaView.COMMAND.UP||command==ArenaView.COMMAND.DOWN||command== ArenaView.COMMAND.RIGHT||command== ArenaView.COMMAND.LEFT)
                        prevcommand1 = command;
                    else
                        prevcommand2=command;
                }
                if (!arena.getGameOver()) {
                    arena.checkCollisions(arena.getSnake().getPosition());
                    arena.checkCollisions(arena.getSnake2().getPosition());

                    arenaController.movement(command,prevcommand1,arena);
                    arenaController.movement2(command, prevcommand2, arena);

                    gui.drawArena(arena,2);

                } else {
                    arena.getTopScore().fileWriter(arena.getTopScore().getFilename(), arena.getTopScore());
                    break;

                }
            }
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
