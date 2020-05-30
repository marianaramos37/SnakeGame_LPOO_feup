package controller;

import commands.Command;
import model.ArenaModel;
import view.View;

import java.io.IOException;

public class SinglePlayerController extends StateControllers {
    private ArenaController arenaController;
    private View gui;
    private ArenaModel arena;

    public SinglePlayerController(View v, int level) throws IOException {
        super(v);
        gui = v;
        if(level==1){
            arena = new ArenaModel(60, 30);
        }
        else if(level==2) {
            arena= new ArenaModel(60,30,"src/main/java/files/mapMedium.txt");
        }
        else if(level==3) {
            arena= new ArenaModel(60,30,"src/main/java/files/mapHard.txt");
        }
        else if(level==4) {
            arena= new ArenaModel(60,30,"src/main/java/files/mapUltraHard.txt");
        }

        arenaController = new ArenaController(arena);
    }

    @Override
    public StateControllers run() {
        try {
            arena.buildWalls();
            arena.getTopScore().fileReader();


            int counter = 0;
            int counterGhost = 0;
            int wallSpawn = 0;

            //comeca jogo
            while (true) {
                Thread.sleep(arena.getSnake().getVelocidade());

                Command command = gui.getCommand();
                command.executeArena(arena);

                arenaController.movement();

                if (!arena.getGameOver()) {
                    arena.checkCollisions(arena.getSnake().getPosition(), arena.getSnake());

                    if (arena.getScore() % 2 == 0 && arena.getScore() != 0 && wallSpawn == 0) {
                        arena.randomWalls();
                        wallSpawn++;
                    }
                    if (arena.getScore() % 2 != 0) wallSpawn = 0;

                    if (arena.getSnake().getShrink()) {
                        counter++;
                        if (counter == 100) {
                            arena.getSnake().setVelocidade(arena.getSnake().getVelocidade() * 2);
                            arena.getSnake().unshrink();
                            counter = 0;
                        }
                    }

                    if (arena.getSnake().isGhost()) {
                        counterGhost++;
                        if (counterGhost == 85) {
                            counter = 0;
                            arena.getSnake().setGhost(false);
                        }
                    }

                    gui.drawArena(arena, 1);

                } else {
                    //jogo acabou , atualiza topScore
                    arena.getTopScore().fileWriter();
                    return new GameOverController(view);
                }
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return new GameOverController(view);
    }

}