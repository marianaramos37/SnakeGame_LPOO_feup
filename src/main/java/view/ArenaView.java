package view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.*;

import java.io.IOException;
import java.util.List;

public class ArenaView {
    public Screen screen;

    public enum COMMAND {UP, RIGHT, DOWN, LEFT, ESC,ENTER}

    public ArenaView(int width, int height) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    public ArenaView(int width, int height,Screen screen) throws IOException {
        this.screen=screen;
        //Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        //screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }


    public void drawSnake(ArenaModel arena) {
        Snake snake=arena.getSnake();
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

    public void drawArena(ArenaModel arena) {
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

            drawSnake(arena);
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void drawMenu(MainMenuModel menuModel) {
        try {
            screen.clear();
            TextGraphics graphics = screen.newTextGraphics();
            graphics.putString(27,10,menuModel.getTitle());
            graphics.putString(32,32,menuModel.getEsqTitle());
            graphics.putString(10,20,menuModel.getSingleTitle().substring(0,4));
            graphics.putString(5,21,menuModel.getSingleTitle().substring(4,17));
            graphics.putString(27,20,menuModel.getMultyTitle().substring(0,4));
            graphics.putString(22,21,menuModel.getMultyTitle().substring(4,16));
            graphics.putString(40,20,menuModel.getContrucaoTitle().substring(0,5));
            graphics.putString(39,21,menuModel.getContrucaoTitle().substring(5,10));
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawMenuLevels(){
        try {
            screen.clear();
            TextGraphics graphics = screen.newTextGraphics();
            graphics.putString(10,20,"EASY");
            graphics.putString(10,21,"LEVEL");
            graphics.putString(25,20,"MEDIUM");
            graphics.putString(25,21,"LEVEL");
            graphics.putString(40,20,"HARD");
            graphics.putString(40,21,"LEVEL");

            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawSelecting(int x, int y) {
        try {
            TextCharacter c1 = new TextCharacter('o');
            screen.setCharacter(x,y, c1);
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawGameOver() {
        try {
            screen.clear();
            TextGraphics graphics = screen.newTextGraphics();
            graphics.setBackgroundColor(TextColor.Factory.fromString("#790000"));
            graphics.fillRectangle(
                    new TerminalPosition(0, 0),
                    new TerminalSize(60, 30),
                    ' '
            );
            graphics.putString(25,10,"GAME OVER");
            graphics.putString(10,20,"EXIT");
            graphics.putString(40,20,"REPLAY");
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public COMMAND getCommand() throws IOException {
        KeyStroke key = screen.pollInput();
        if(key!=null){
            if (key.getKeyType() == KeyType.ArrowUp) {
                System.out.println("cliquei");
                return COMMAND.UP;
            }
            if (key.getKeyType() == KeyType.ArrowRight) {
                return COMMAND.RIGHT;
            }
            if (key.getKeyType() == KeyType.ArrowDown){
                return COMMAND.DOWN;
            }
            if (key.getKeyType() == KeyType.ArrowLeft){
                return COMMAND.LEFT;
            }
            if (key.getKeyType() == KeyType.Escape ){
                return COMMAND.ESC;
            }
            if (key.getKeyType() == KeyType.Enter ){
                return COMMAND.ENTER;
            }
            else return null;
        }
        else return null;
    }
}
