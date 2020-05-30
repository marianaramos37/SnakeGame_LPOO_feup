package gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewTest {
    Snake snake;
    Snake snake2;

    List<AppleInterface> apples=new ArrayList<>();
    List<Wall> walls= new ArrayList<>();
    List<Character> snakebody = new ArrayList<>();
    List<Character> snakebody2 = new ArrayList<>();
    List<Position> pos = new ArrayList<>();
    List<Position> pos2 = new ArrayList<>();

    @Before
    public void setUp(){
        //ArenaStub with stub things
        snake = Mockito.mock(Snake.class);
        snake2= Mockito.mock(Snake.class);

        AppleInterface apple=Mockito.mock(AppleInterface.class);
        Wall wall = Mockito.mock(Wall.class);
        apples.add(apple);
        walls.add(wall);
        snakebody.add('|');
        snakebody.add('|');
        snakebody.add('|');
        pos.add(new Position(5,5));
        pos.add(new Position(5,6));
        pos.add(new Position(5,7));

        snakebody2.add('-');
        snakebody2.add('-');
        snakebody2.add('-');
        pos2.add(new Position(10,5));
        pos2.add(new Position(11,5));
        pos2.add(new Position(12,5));

        Mockito.when(snake.getSnakeBody()).thenReturn(snakebody);
        Mockito.when(snake.getShrink()).thenReturn(false);
        Mockito.when(snake.isGhost()).thenReturn(false);
        Mockito.when(snake.getPos()).thenReturn(pos);
        Mockito.when(snake.getPosition()).thenReturn(new Position(5,5));

        Mockito.when(snake2.getSnakeBody()).thenReturn(snakebody2);
        Mockito.when(snake2.getShrink()).thenReturn(false);
        Mockito.when(snake2.isGhost()).thenReturn(true);
        Mockito.when(snake2.getPos()).thenReturn(pos2);
        Mockito.when(snake2.getPosition()).thenReturn(new Position(10,5));

        Mockito.when(apple.getPosition()).thenReturn(new Position(11, 20));
        Mockito.when(apple.getChar()).thenReturn('O');

        Mockito.when(wall.getPosition()).thenReturn(new Position(12, 20));



    }

    @Test
    public void testDrawWalls() throws IOException {
        ArenaModel arena = Mockito.mock(ArenaModel.class);
        Mockito.when(arena.getSnake()).thenReturn((snake));
        Mockito.when(arena.getWalls()).thenReturn((walls));
        Mockito.when(arena.getApples()).thenReturn((apples));


        // A TerminalScreen Mock
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        // Execute
        View view = new View(30,30,screen);

        List<Element> elements = new ArrayList<>();
        elements.addAll(walls);
        view.drawElements(elements);

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
        View view = new View(30,30,screen);
        view.drawApples(arena);

        // Verify
        Mockito.verify(screen, Mockito.times(1))
                .setCharacter(11, 20, new TextCharacter('O'));
    }

    @Test
    public void testDrawSnake() throws IOException {
        ArenaModel arena=Mockito.mock(ArenaModel.class);
        Mockito.when(arena.getSnake()).thenReturn((snake));
        Mockito.when(arena.getSnake2()).thenReturn((snake2));
        Mockito.when(arena.getWalls()).thenReturn((walls));
        Mockito.when(arena.getApples()).thenReturn((apples));

        // A TerminalScreen Mock
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        // Execute
        View view = new View(30,30,screen);
        view.drawSnake(arena.getSnake());

        // Verify
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(5, 5, new TextCharacter('|'));
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(5, 6, new TextCharacter('|'));
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(5, 7, new TextCharacter('|'));


        //draw ghost snake
        view.drawSnake(arena.getSnake2());

        Mockito.verify(graphics, Mockito.times(1)).putString(10, 5, "-", SGR.BLINK);
        Mockito.verify(graphics, Mockito.times(1)).putString(11, 5, "-", SGR.BLINK);
        Mockito.verify(graphics, Mockito.times(1)).putString(12, 5, "-", SGR.BLINK);


        //draw shrunk snake
        Mockito.when(snake.getShrink()).thenReturn(true);

        view.drawSnake(arena.getSnake());
        //checks for 2 invocations because method has already been called before once
        Mockito.verify(graphics, Mockito.times(2)).setCharacter(5, 5, new TextCharacter('|'));
        Mockito.verify(graphics, Mockito.times(2)).setCharacter(5, 6, new TextCharacter('|'));

    }

    @Test
    public void testDrawArenaSinglePlayer() throws IOException, NullPointerException {
        ArenaModel arena=Mockito.mock(ArenaModel.class);
        Mockito.when(arena.getScore()).thenReturn(0);
        Mockito.when(arena.getTopScore()).thenReturn((new SinglePlayerTopScore()));
        Mockito.when(arena.getSnake()).thenReturn((snake));
        Mockito.when(arena.getWalls()).thenReturn((walls));
        Mockito.when(arena.getApples()).thenReturn((apples));

        // A TerminalScreen Mock
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        // Execute
        View view = new View(30,30,screen);
        view.drawArena(arena,1);

        // Verify
        Mockito.verify(screen, Mockito.times(1))
                .setCharacter(12, 20, new TextCharacter('#'));
        Mockito.verify(screen, Mockito.times(1))
                .setCharacter(11, 20, new TextCharacter('O'));
        Mockito.verify(graphics, Mockito.times(1))
                .setCharacter(5, 5, new TextCharacter('|'));
        Mockito.verify(graphics, Mockito.times(1))
                .setCharacter(5, 6, new TextCharacter('|'));
        Mockito.verify(graphics, Mockito.times(1))
                .setCharacter(5, 7, new TextCharacter('|'));
    }


    @Test
    public void testDrawArenaMultiPlayer() throws IOException {
        ArenaModel arena=Mockito.mock(ArenaModel.class);
        Mockito.when(arena.getScore()).thenReturn(0);
        Mockito.when(arena.getTopScore()).thenReturn((new SinglePlayerTopScore()));
        Mockito.when(arena.getSnake()).thenReturn((snake));
        Mockito.when(arena.getSnake2()).thenReturn((snake2));
        Mockito.when(arena.getWalls()).thenReturn((walls));
        Mockito.when(arena.getApples()).thenReturn((apples));

        Mockito.when(snake2.isGhost()).thenReturn(false);

        // A TerminalScreen Mock
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        // Execute
        View view = new View(30,30,screen);
        view.drawArena(arena,2);

        // Verify
        Mockito.verify(screen, Mockito.times(1))
                .setCharacter(12, 20, new TextCharacter('#'));
        Mockito.verify(screen, Mockito.times(1))
                .setCharacter(11, 20, new TextCharacter('O'));
        Mockito.verify(graphics, Mockito.times(1))
                .setCharacter(5, 5, new TextCharacter('|'));
        Mockito.verify(graphics, Mockito.times(1))
                .setCharacter(5, 6, new TextCharacter('|'));
        Mockito.verify(graphics, Mockito.times(1))
                .setCharacter(5, 7, new TextCharacter('|'));

        Mockito.verify(graphics, Mockito.times(1))
                .setCharacter(10, 5, new TextCharacter('-'));
        Mockito.verify(graphics, Mockito.times(1))
                .setCharacter(11, 5, new TextCharacter('-'));
        Mockito.verify(graphics, Mockito.times(1))
                .setCharacter(12, 5, new TextCharacter('-'));

    }


}
