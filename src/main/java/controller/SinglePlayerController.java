package controller;

import commands.Command;
import model.ArenaModel;
import view.View;

import java.io.IOException;

public class SinglePlayerController extends StateControllers {
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
    }

    @Override
    public StateControllers run() {
        try {
            arena.buildWalls();
            arena.getTopScore().fileReader();


            int counter = 0;
            int wallSpawn = 0;

            //comeca jogo
            while (true) {
                //movimentar a snake à sua velocidade:
                Thread.sleep(arena.getSnake().getVelocidade());
                Command command = gui.getCommand();
                command.executeArena(arena);
                arena.getSnake().walkSnake();

                if (!arena.getGameOver()) {
                    arena.checkCollisions(arena.getSnake()); //verificar colisoes

                    if (arena.getScore() % 2 == 0 && arena.getScore() != 0 && wallSpawn==0) { //colocar obstaculos
                        arena.randomWalls();
                        wallSpawn++;
                        if (arena.getScore() % 2 != 0) wallSpawn = 0;
                    }

                    if (arena.getSnake().isShrink() || arena.getSnake().isGhost()) { // voltar a colocar snake normal depois de tempo = 85
                        counter++;
                        if (counter == 85) {
                            arena.getSnake().goBackToNormal();
                            counter = 0;
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