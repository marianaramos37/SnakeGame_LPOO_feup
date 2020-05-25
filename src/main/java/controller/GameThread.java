package controller;

import model.ArenaModel;
import view.ArenaView;

import java.io.IOException;

public class GameThread extends Thread{
    private ArenaController arenaController;
    private ArenaView gui;
    private ArenaModel arena;

    public GameThread(ArenaController cntr){
        arenaController=cntr;
        gui=cntr.getArenaView();
        arena=cntr.getArenaModel();
    }

    @Override
    public void run() {
        try {
            arena.buildWalls();
            arena.getTopScore().fileReader(arena.getTopScore().getFilename(), arena.getTopScore());

            ArenaView.COMMAND prevcommand = null;
            ArenaView.COMMAND command = null;
            int counter = 0;
            int counterGhost=0;
            int wallSpawn = 0;

            //comeca jogo
            while (true) {
                Thread.sleep(arena.getSnake().getVelocidade());

                command = gui.getCommand();
                if (command != null) {
                    if (command == ArenaView.COMMAND.UP && prevcommand == ArenaView.COMMAND.DOWN) {
                        command = ArenaView.COMMAND.DOWN;
                    }
                    if (command == ArenaView.COMMAND.DOWN && prevcommand == ArenaView.COMMAND.UP) {
                        command = ArenaView.COMMAND.UP;
                    }
                    if (command == ArenaView.COMMAND.RIGHT && prevcommand == ArenaView.COMMAND.LEFT) {
                        command = ArenaView.COMMAND.LEFT;
                    }
                    if (command == ArenaView.COMMAND.LEFT && prevcommand == ArenaView.COMMAND.RIGHT) {
                        command = ArenaView.COMMAND.RIGHT;
                    }
                    prevcommand = command;
                }
                if (!arena.getGameOver()) {
                    arena.checkCollisions(arena.getSnake().getPosition(),arena.getSnake());

                    if (arena.getScore().getScore() % 2 == 0 && arena.getScore().getScore() != 0 && wallSpawn == 0) {
                        arena.randomWalls();
                        wallSpawn++;
                    }
                    if (arena.getScore().getScore() % 2 != 0) wallSpawn = 0;

                    if (arena.getSnake().getShrink()) {
                        counter++;
                        if (counter == 100) {
                            arena.getSnake().setVelocidade(arena.getSnake().getVelocidade() * 2);
                            arena.getSnake().unshrink();
                            counter = 0;
                        }
                    }

                    if(arena.getSnake().isGhost()){
                        counterGhost++;
                        if(counterGhost==100){
                            counter=0;
                            arena.getSnake().setGhost(false);
                        }
                    }
                    arenaController.movement(command, prevcommand, arena);

                    gui.drawArena(arena,1);

                } else {
                    //jogo acabou , atualiza topScore
                    arena.getTopScore().fileWriter(arena.getTopScore().getFilename(), arena.getTopScore());
                    break;

                }
            }
        }catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}