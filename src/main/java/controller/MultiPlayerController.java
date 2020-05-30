package controller;

import commands.Command;
import model.Apple;
import model.AppleInterface;
import model.ArenaModel;
import model.PoisonedApple;
import view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class MultiPlayerController extends StateControllers {
    private ArenaModel arena;
    private View gui;

    public MultiPlayerController(View v) throws IOException {
        super(v);
        gui = v;
        arena = new ArenaModel(60, 30,"src/main/java/files/mapHard.txt");
    }

    @Override
    public StateControllers run() {
        try {
            arena.buildWalls();
            List<AppleInterface> newapples = new ArrayList<>();
            newapples.add(new Apple(8,6));
            newapples.add(new Apple(30,15));
            newapples.add(new Apple(50,23));
            newapples.add(new PoisonedApple(12,17));
            arena.setApples(newapples);

            int result_snake1=0;
            int result_snake2=0;


            while (result_snake1!=3 && result_snake2!=3) {

                sleep(arena.getSnake2().getVelocidade());
                Command command = gui.getCommand();
                command.executeArena(arena);
                arena.getSnake().walkSnake();
                arena.getSnake2().walkSnake();

                if (!arena.getGameOver()) {
                    arena.checkCollisions(arena.getSnake());
                    arena.checkCollisions(arena.getSnake2());
                    arena.checkAttack();
                    view.drawArena(arena,2);

                }
                else{
                    if(arena.getSnake().getLoser() && !arena.getSnake2().getLoser()) result_snake2++;
                    else if(arena.getSnake2().getLoser() && !arena.getSnake().getLoser()) result_snake1++;
                    view.drawResults(result_snake1,result_snake2);
                    arena.restartGame();
                    arena.getSnake().setLoser(false);
                    arena.getSnake2().setLoser(false);
                    sleep(3000);
                }
            }
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new GameOverController(view);
    }
}
