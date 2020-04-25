package gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import data.ArenaModel;

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
            for(int j=0; j<arena.getWalls().size();j++){
                screen.setCharacter(arena.getWalls().get(j).getPosition().getX(),arena.getWalls().get(j).getPosition().getY(), new TextCharacter('#'));
            }
            screen.setCharacter(arena.getApplePosition().getX(),arena.getApplePosition().getY(),new TextCharacter('O'));
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
