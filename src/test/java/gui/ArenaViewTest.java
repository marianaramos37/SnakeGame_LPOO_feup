package gui;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.TerminalScreen;
import data.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArenaViewTest {
    Snake snake;
    Snake snake2;

    List<AppleInterface> apples=new ArrayList<>();
    List<Wall> walls= new ArrayList<>();
    List<Character> snakebody = new ArrayList<>();
    List<Position> pos = new ArrayList<>();

    @Before
    public void setUp(){
        //ArenaStub with stub things
        snake = Mockito.mock(Snake.class);
        snake2= Mockito.mock(Snake.class);

        AppleInterface apple=Mockito.mock(AppleInterface.class);
        Wall wall = Mockito.mock(Wall.class);
        apples.add(apple);
        walls.add(wall);
        snakebody.add('-');
        snakebody.add('-');
        snakebody.add('-');
        pos.add(new Position(5,5));
        pos.add(new Position(5,6));
        pos.add(new Position(5,7));

        Mockito.when(snake.getSnakeBody()).thenReturn(snakebody);
        Mockito.when(snake.getShrink()).thenReturn(false);
        Mockito.when(snake.getPos()).thenReturn(pos);
        Mockito.when(apple.getPosition()).thenReturn(new Position(11, 20));
        Mockito.when(apple.getChar()).thenReturn('O');
        Mockito.when(wall.getPosition()).thenReturn(new Position(12, 20));

        Mockito.when(snake2.getShrink()).thenReturn(true);

    }

    @Test
    public void testDrawWalls() throws IOException {
        ArenaModel arena=Mockito.mock(ArenaModel.class);
        Mockito.when(arena.getSnake()).thenReturn((snake));
        Mockito.when(arena.getWalls()).thenReturn((walls));
        Mockito.when(arena.getApples()).thenReturn((apples));

        // A TerminalScreen Mock
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);

        // Execute
        ArenaView view = new ArenaView(30,30,screen);
        view.drawWalls(arena);

        // Verify
        Mockito.verify(screen, Mockito.times(1))
                .setCharacter(12, 20, new TextCharacter('#'));
    }

    @Test
    public void testDrawApples() throws IOException {
        ArenaModel arena=Mockito.mock(ArenaModel.class);
        Mockito.when(arena.getSnake()).thenReturn((snake));
        Mockito.when(arena.getWalls()).thenReturn((walls));
        Mockito.when(arena.getApples()).thenReturn((apples));

        // A TerminalScreen Mock
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);

        // Execute
        ArenaView view = new ArenaView(30,30,screen);
        view.drawApples(arena);

        // Verify
        Mockito.verify(screen, Mockito.times(1))
                .setCharacter(11, 20, new TextCharacter('O'));
    }

    @Test
    public void testDrawSnake() throws IOException {
        ArenaModel arena=Mockito.mock(ArenaModel.class);
        //ArenaModel arena2=Mockito.mock(ArenaModel.class);
        Mockito.when(arena.getSnake()).thenReturn((snake));
        //Mockito.when(arena2.getSnake()).thenReturn((snake2));
        Mockito.when(arena.getWalls()).thenReturn((walls));
        Mockito.when(arena.getApples()).thenReturn((apples));

        // A TerminalScreen Mock
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);

        // Execute
        ArenaView view = new ArenaView(30,30,screen);
        view.drawSnake(arena);

        // Verify
        Mockito.verify(screen, Mockito.times(1))
                .setCharacter(5, 5, new TextCharacter('-'));
        Mockito.verify(screen, Mockito.times(1))
                .setCharacter(5, 6, new TextCharacter('-'));
        Mockito.verify(screen, Mockito.times(1))
                .setCharacter(5, 7, new TextCharacter('-'));


    }

    @Test
    public void testDrawArena() throws IOException {
        ArenaModel arena=Mockito.mock(ArenaModel.class);
        Mockito.when(arena.getSnake()).thenReturn((snake));
        Mockito.when(arena.getWalls()).thenReturn((walls));
        Mockito.when(arena.getApples()).thenReturn((apples));

        // A TerminalScreen Mock
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);

        // Execute
        ArenaView view = new ArenaView(30,30,screen);
        view.drawArena(arena);

        // Verify
        Mockito.verify(screen, Mockito.times(1))
                .setCharacter(12, 20, new TextCharacter('#'));
        Mockito.verify(screen, Mockito.times(1))
                .setCharacter(11, 20, new TextCharacter('O'));
        Mockito.verify(screen, Mockito.times(1))
                .setCharacter(5, 5, new TextCharacter('-'));
        Mockito.verify(screen, Mockito.times(1))
                .setCharacter(5, 6, new TextCharacter('-'));
        Mockito.verify(screen, Mockito.times(1))
                .setCharacter(5, 7, new TextCharacter('-'));
    }
}
