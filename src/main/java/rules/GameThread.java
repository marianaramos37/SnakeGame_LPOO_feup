package rules;

import data.ArenaModel;
import gui.ArenaView;

import java.io.IOException;

public class GameThread extends Thread{
    private ArenaController arenaController;
    private ArenaView gui;
    private ArenaModel arena;
    private SnakeModel snake;
    private ScoreController scoreController;
    public GameThread(ArenaController cntr){
        arenaController=cntr;
        gui=cntr.getArenaView();
        arena=cntr.getArenaModel();
        snake=cntr.getArenaModel().getSnakeModel();
        scoreController=cntr.getArenaModel().getScoreController();
    }

    @Override
    public void run() {
        //lê score logo de inicio
        try {
            arena.buildWalls();
            scoreController.fileReader(arena.getTopScore().getFilename(), arena.getTopScore());

            ArenaView.COMMAND prevcommand = null;
            ArenaView.COMMAND command = null;
            int counter = 0;
            int wallSpawn = 0;

            //comeca jogo
            while (true) {
                sleep(snake.getVelocidade());
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
                    arena.checkCollisions(arena.getSnake().getPosition(), arena);

                    //spawn de paredes dependendo do nível de dificuldade (if statement + estado da máquina de estados do menu)
                    //the way it is makes it so that only one wall spawns when score is multiple of 10
                    if (arena.getScore().getScore() % 10 == 0 && arena.getScore().getScore() != 0 && wallSpawn == 0) {
                        arena.randomWalls();
                        wallSpawn++;
                    }
                    if (arena.getScore().getScore() % 10 != 0) wallSpawn = 0;

                    if (arena.getSnake().getShrink()) {
                        counter++;
                        if (counter == 100) {
                            snake.setVelocidade(snake.getVelocidade() * 2);
                            snake.unshrink();
                            counter = 0;
                        }
                    }

                    arenaController.movement(command, prevcommand, arena, snake);

                    gui.drawArena(arena);
                } else {
                    //jogo acabou , atualiza topScore
                    scoreController.fileWriter(arena.getTopScore().getFilename(), arena.getTopScore());
                    break;

                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}