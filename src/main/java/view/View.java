package view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public abstract class View {
    public Screen screen;

    public View(int width, int height) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    public View(int width, int height, Screen screen) throws IOException {
        this.screen=screen;
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    public enum COMMAND {UP, RIGHT, DOWN, LEFT, ESC,ENTER,UP2,RIGHT2,DOWN2,LEFT2}

    public ArenaView.COMMAND getCommand() throws IOException {
        KeyStroke key = screen.pollInput();
        if(key!=null){
            if (key.getKeyType() == KeyType.ArrowUp) {
                return ArenaView.COMMAND.UP;
            }
            if (key.getKeyType() == KeyType.ArrowRight) {
                return ArenaView.COMMAND.RIGHT;
            }
            if (key.getKeyType() == KeyType.ArrowDown){
                return ArenaView.COMMAND.DOWN;
            }
            if (key.getKeyType() == KeyType.ArrowLeft){
                return ArenaView.COMMAND.LEFT;
            }
            if (key.getKeyType() == KeyType.Escape ){
                return ArenaView.COMMAND.ESC;
            }
            if (key.getKeyType() == KeyType.Enter ){
                return ArenaView.COMMAND.ENTER;
            }
            if (key.getCharacter() == 'w') {
                return ArenaView.COMMAND.UP2;
            }
            if (key.getCharacter()=='d') {
                return ArenaView.COMMAND.RIGHT2;
            }
            if (key.getCharacter()=='s'){
                return ArenaView.COMMAND.DOWN2;
            }
            if (key.getCharacter()=='a'){
                return ArenaView.COMMAND.LEFT2;
            }
            else return null;
        }
        else return null;
    }
}
