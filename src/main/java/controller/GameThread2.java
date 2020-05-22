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
            ArenaView.COMMAND command1 = null;

            while (true) {
                sleep(arena.getSnake2().getVelocidade());

                command1=gui.getCommand();
                if (command1 != null) {
                    if (command1 == ArenaView.COMMAND.UP2 && prevcommand1 == ArenaView.COMMAND.DOWN2) {
                        command1 = ArenaView.COMMAND.DOWN2;
                    }
                    else if (command1 == ArenaView.COMMAND.DOWN2 && prevcommand1 == ArenaView.COMMAND.UP2) {
                        command1 = ArenaView.COMMAND.UP2;
                    }
                    else if (command1 == ArenaView.COMMAND.RIGHT2 && prevcommand1 == ArenaView.COMMAND.LEFT2) {
                        command1 = ArenaView.COMMAND.LEFT2;
                    }
                    else if (command1 == ArenaView.COMMAND.LEFT2 && prevcommand1 == ArenaView.COMMAND.RIGHT2) {
                        command1 = ArenaView.COMMAND.RIGHT2;
                    }
                    prevcommand1 = command1;
                }
                if (!arena.getGameOver()) {
                    arena.checkCollisions(arena.getSnake2().getPosition());

                    arenaController.movement2(command1, prevcommand1, arena);

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
