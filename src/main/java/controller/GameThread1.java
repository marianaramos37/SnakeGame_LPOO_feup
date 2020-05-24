package controller;

import model.ArenaModel;
import view.ArenaView;

import java.io.IOException;

public class GameThread1 extends Thread{
    private ArenaController arenaController;
    private ArenaView gui;
    private ArenaModel arena;

    public GameThread1(ArenaController cntr){
        arenaController=cntr;
        gui=cntr.getArenaView();
        arena=cntr.getArenaModel();
    }

    @Override
    public void run() {
        //lê score logo de inicio
        try {
            arena.buildWalls();

            ArenaView.COMMAND prevcommand = null;
            ArenaView.COMMAND command = null;
            int counter = 0;
            int wallSpawn = 0;

            //comeca jogo
            while (true) {
                sleep(arena.getSnake().getVelocidade());

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
                    arena.checkCollisions(arena.getSnake().getPosition());
                    //spawn de acordo com nivel de dificuldade
                    if (arena.getScore().getScore() % 10 == 0 && arena.getScore().getScore() != 0 && wallSpawn == 0) {
                        arena.randomWalls();
                        wallSpawn++;
                    }
                    if (arena.getScore().getScore() % 10 != 0) wallSpawn = 0;

                    if (arena.getSnake().getShrink()) {
                        counter++;
                        if (counter == 100) {
                            arena.getSnake().setVelocidade(arena.getSnake().getVelocidade() * 2);
                            arena.getSnake().unshrink();
                            counter = 0;
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

