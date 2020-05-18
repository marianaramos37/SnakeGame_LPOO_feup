package gui;

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
import data.*;

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
        if(!snake.isShrink) {
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

            int x = 27;
            for(Character c: menuModel.getTitle().toCharArray()){
                TextCharacter c1 = new TextCharacter(c);
                screen.setCharacter(x,10, c1);
                x++;
            }
            int k=32;
            for(Character c: menuModel.getEsqTitle().toCharArray()){
                TextCharacter c1 = new TextCharacter(c);
                screen.setCharacter(k,32, c1);
                k++;
            }
            int x1=10, x2=5;
            for(int i=0; i<17; i++){
                Character c = menuModel.getSingleTitle().toCharArray()[i];
                if(i<4){
                    TextCharacter c1 = new TextCharacter(c);
                    screen.setCharacter(x1,20, c1);
                    x1++;
                }
                else{
                    TextCharacter c1 = new TextCharacter(c);
                    screen.setCharacter(x2,21, c1);
                    x2++;
                }
            }
            int x3=27, x4=22;
            for(int i=0; i<16; i++){
                Character c = menuModel.getMultyTitle().toCharArray()[i];
                if(i<4){
                    TextCharacter c1 = new TextCharacter(c);
                    screen.setCharacter(x3,20, c1);
                    x3++;
                }
                else{
                    TextCharacter c1 = new TextCharacter(c);
                    screen.setCharacter(x4,21, c1);
                    x4++;
                }
            }
            int x5=40, x6=39;
            for(int i=0; i<10; i++){
                Character c = menuModel.getContrucaoTitle().toCharArray()[i];
                if(i<5){
                    TextCharacter c1 = new TextCharacter(c);
                    screen.setCharacter(x5,20, c1);
                    x5++;
                }
                else{
                    TextCharacter c1 = new TextCharacter(c);
                    screen.setCharacter(x6,21, c1);
                    x6++;
                }
            }
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawSelecting(MainMenuModel menuModel,int x, int y) {
        try {
            TextCharacter c1 = new TextCharacter(menuModel.getSelection());
            screen.setCharacter(x,y, c1);
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void drawGameOver(ArenaModel arena) {
        try {
            screen.clear();
            TextGraphics graphics = screen.newTextGraphics();
            graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
            graphics.fillRectangle(
                    new TerminalPosition(0, 0),
                    new TerminalSize(arena.getWidth(), arena.getHeight()),
                    ' '
            );
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
