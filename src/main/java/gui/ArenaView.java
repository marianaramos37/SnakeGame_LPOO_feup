package gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import data.Apple;
import data.ArenaModel;
import data.Wall;

import java.io.IOException;

public class ArenaView {
    public Screen screen;

    public enum COMMAND {UP, RIGHT, DOWN, LEFT}


    public ArenaView(int width, int height) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    public void drawArena(ArenaModel arena) {
        try {
            screen.clear();
            for(Wall w:arena.getWalls())
                screen.setCharacter(w.getPosition().getX(),w.getPosition().getY(), new TextCharacter('#'));

            for(Apple a:arena.getApples())
                screen.setCharacter(a.getPosition().getX(),a.getPosition().getY(),new TextCharacter('O'));

            int x = 5;
            for(TextCharacter c:arena.getScore().getPrintableScore()){
                screen.setCharacter(x,33, c);
                x++;
            }

            arena.getSnake().drawSnake(screen);
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public COMMAND getCommand() throws IOException {
        KeyStroke key = screen.pollInput();
        if(key!=null){
            if (key.getKeyType() == KeyType.ArrowUp) {
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
            else return null;
        }
        else return null;
    }
}
