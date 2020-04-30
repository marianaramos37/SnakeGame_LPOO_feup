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
import data.AppleInterface;
import data.ArenaModel;
import data.Wall;

import java.io.IOException;

public class ArenaView {
    public Screen screen;

    public enum COMMAND {UP, RIGHT, DOWN, LEFT, ESC}


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
                w.draw(screen);

            for(AppleInterface a:arena.getApples())
                a.draw(screen);

            int x = 5;
            for(TextCharacter c:arena.getScore().getPrintableScore()){
                screen.setCharacter(x,33, c);
                x++;
            }

            arena.getSnake().draw(screen);
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
            else return null;
        }
        else return null;
    }
}
