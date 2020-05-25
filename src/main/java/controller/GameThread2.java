package controller;

import model.*;
import view.ArenaView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameThread2 extends Thread {
    private ArenaController arenaController;
    private ArenaModel arena;

    public GameThread2(ArenaController cntr){
        arenaController=cntr;
        arena=cntr.getArenaModel();
    }

    @Override
    public void run() {
        try {
            arena.buildWalls();
            List<AppleInterface> newapples = new ArrayList<>();
            newapples.add(new Apple(8,6));
            newapples.add(new Apple(30,15));
            newapples.add(new Apple(50,23));
            newapples.add(new PoisonedApple(12,17));
            arena.setApples(newapples);

            ArenaView.COMMAND prevcommand1 = null;
            ArenaView.COMMAND prevcommand2=null;
            ArenaView.COMMAND command = null;

            int result_snake1=0;
            int result_snake2=0;



            while (result_snake1!=3&&result_snake2!=3) {
                Thread.sleep(arena.getSnake2().getVelocidade());
                command=arenaController.getArenaView().getCommand();

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
                    arena.checkCollisions(arena.getSnakeHeadPosition(),arena.getSnake());
                    arena.checkCollisions(arena.getSnakeHead2Position(),arena.getSnake2());

                    arena.checkAttack();

                    arenaController.movement(command,prevcommand1,arena);
                    arenaController.movement2(command, prevcommand2, arena);

                    arenaController.getArenaView().drawArena(arena,2);

                }
                else{

                    if(arena.getSnake().getLoser() && !arena.getSnake2().getLoser()) result_snake2++;
                    else if(arena.getSnake2().getLoser() && !arena.getSnake().getLoser()) result_snake1++;
                    arenaController.getMenusViews().drawResults(result_snake1,result_snake2);
                    arena.restartGame();
                    arena.getSnake().setLoser(false);
                    arena.getSnake2().setLoser(false);
                    sleep(3000);
                }
            }
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
