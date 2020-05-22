package view;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import model.*;

import java.io.IOException;
import java.util.List;

public class ArenaView extends View{


    public ArenaView(int width, int height, Screen screen) throws IOException {
        super(width, height, screen);
    }


    public void drawSnake(Snake snake) {
        int index=0;
        if(!snake.getShrink()) {
            for (Character c : snake.getSnakeBody()) {
                if (index >= snake.getPos().size()) {
                    snake.getPos().add(snake.getLength(), new Position(snake.getPos().get(snake.getLength() - 1).getX() + 1, snake.getPos().get(snake.getLength() - 1).getY()));
                }
                screen.setCharacter(snake.getPos().get(index).getX(), snake.getPos().get(index).getY(), new TextCharacter(snake.getSnakeBody().get(index)));
                index++;
            }
        }else{
            for (Character c : snake.getSnakeBody().subList(0,2)) {
                if (index >= snake.getPos().size()) {
                    snake.getPos().add(snake.getLength(), new Position(snake.getPos().get(snake.getLength() - 1).getX() + 1, snake.getPos().get(snake.getLength() - 1).getY()));
                }
                screen.setCharacter(snake.getPos().get(index).getX(), snake.getPos().get(index).getY(), new TextCharacter(snake.getSnakeBody().get(index)));
                index++;
            }
        }
    }


    public void drawWalls(ArenaModel arena) {
        List<Wall> walls=arena.getWalls();
        for(Wall wall:walls)
            screen.setCharacter(wall.getPosition().getX(),wall.getPosition().getY(), new TextCharacter('#'));
    }

    public void drawApples(ArenaModel arena) {
        List<AppleInterface> apples=arena.getApples();
        for(AppleInterface apple:apples)
            screen.setCharacter(apple.getPosition().getX(),apple.getPosition().getY(), new TextCharacter(apple.getChar()));
    }

    public void drawArena(ArenaModel arena, int n) {
        try {
            screen.clear();

            drawWalls(arena);

            drawApples(arena);

            int x = 5;
            for(TextCharacter c:arena.getScore().getPrintableScore()){
                screen.setCharacter(x,33, c);
                x++;
            }

            x=40;
            for(TextCharacter c:arena.getTopScore().getPrintableScore()){
                screen.setCharacter(x,33, c);
                x++;
            }

            if(n==1) drawSnake(arena.getSnake());
            else{
                drawSnake(arena.getSnake2());
            }
            screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
