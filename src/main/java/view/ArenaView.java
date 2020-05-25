package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
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
        TextGraphics graphics = screen.newTextGraphics();
        if(!snake.getShrink()) {
            for (Character c : snake.getSnakeBody()) {
                if (index >= snake.getPos().size()) {
                    snake.getPos().add(snake.getLength(), new Position(snake.getPos().get(snake.getLength() - 1).getX() + 1, snake.getPos().get(snake.getLength() - 1).getY()));
                }
                if(!snake.isGhost())
                    graphics.setCharacter(snake.getPos().get(index).getX(), snake.getPos().get(index).getY(), new TextCharacter(snake.getSnakeBody().get(index)));
                else graphics.putString(snake.getPos().get(index).getX(), snake.getPos().get(index).getY(), String.valueOf(snake.getSnakeBody().get(index)), SGR.BLINK);
                index++;
            }
        }else{
            for (Character c : snake.getSnakeBody().subList(0,2)) {
                if (index >= snake.getPos().size()) {
                    snake.getPos().add(snake.getLength(), new Position(snake.getPos().get(snake.getLength() - 1).getX() + 1, snake.getPos().get(snake.getLength() - 1).getY()));
                }
                if(!snake.isGhost())
                    graphics.setCharacter(snake.getPos().get(index).getX(), snake.getPos().get(index).getY(), new TextCharacter(snake.getSnakeBody().get(index)));
                else graphics.putString(snake.getPos().get(index).getX(), snake.getPos().get(index).getY(), String.valueOf(snake.getSnakeBody().get(index)), SGR.BLINK);
                index++;
            }
        }
    }

    public void drawWalls(ArenaModel arena) {
        List<Wall> walls=arena.getWalls();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#790000"));
        for(Wall wall:walls) {
            screen.setCharacter(wall.getPosition().getX(), wall.getPosition().getY(), new TextCharacter('#'));
            //graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(wall.getPosition().getX(), wall.getPosition().getY()), ' ');
        }
    }

    public void drawObstaculos(ArenaModel arena){
        List<Obstaculo> obstaculos=arena.getObstaculos();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#790000"));
        for(Obstaculo obstaculo:obstaculos) {
            for(Element elem: obstaculo.getObstaculo()){
                screen.setCharacter(elem.getPosition().getX(), elem.getPosition().getY(), new TextCharacter('#'));
            }
        }
    }

    public void drawApples(ArenaModel arena) {
        List<AppleInterface> apples=arena.getApples();
        for(AppleInterface apple:apples)
            screen.setCharacter(apple.getPosition().getX(),apple.getPosition().getY(), new TextCharacter(apple.getChar()));
    }

    public void drawArena(ArenaModel arena, int n) {
        try {
            TextGraphics graphics = screen.newTextGraphics();
            screen.clear();

            if(n==1){
                drawApples(arena);
                int x = 5;
                for(TextCharacter c:arena.getScore().getPrintableScore()){
                    screen.setCharacter(x,32, c);
                    x++;
                }
                drawSnake(arena.getSnake());
                x=40;
                for(TextCharacter c:arena.getTopScore().getPrintableScore()){
                    screen.setCharacter(x,32, c);
                    x++;
                }
            }
            else{
                drawApples(arena);
                graphics.putString(5,32,"Snake 2");
                graphics.putString(45,32,"Snake 1");
                drawSnake(arena.getSnake());
                drawSnake(arena.getSnake2());
            }

            drawWalls(arena);


            drawObstaculos(arena);

            screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
