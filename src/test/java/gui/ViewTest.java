package gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import commands.*;
import model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ViewTest {
    Snake snake;
    Snake snake2;

    List<AppleInterface> apples=new ArrayList<>();
    List<Wall> walls= new ArrayList<>();
    List<Obstaculo> obs = new ArrayList<>();
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
        Obstaculo o = Mockito.mock(Obstaculo.class);
        apples.add(apple);
        walls.add(wall);
        obs.add(o);
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


        //draw shrunk & ghost snake
        Mockito.when(snake2.getShrink()).thenReturn(true);
        view.drawSnake(arena.getSnake2());
        Mockito.verify(graphics, Mockito.times(2)).putString(10, 5, "-", SGR.BLINK);
        Mockito.verify(graphics, Mockito.times(2)).putString(11, 5, "-", SGR.BLINK);

    }

    @Test
    public void testDrawArenaSinglePlayer() throws IOException, NullPointerException {
        ArenaModel arena=Mockito.mock(ArenaModel.class);
        Mockito.when(arena.getScore()).thenReturn(0);
        Mockito.when(arena.getTopScore()).thenReturn((new SinglePlayerTopScore()));
        Mockito.when(arena.getSnake()).thenReturn((snake));
        Mockito.when(arena.getWalls()).thenReturn((walls));
        Mockito.when(arena.getApples()).thenReturn((apples));
        Mockito.when(arena.getObstaculos()).thenReturn(obs);

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

    @Test
    public void testDrawMenu() throws IOException {
        // A TerminalScreen Mock
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        // Execute
        View view = new View(60,60,screen);

        view.drawMenu();

        Mockito.verify(graphics, Mockito.times(1))
                .putString(27,10,"SNAKE", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1))
                .putString(28,30,"Press Q to leave",SGR.ITALIC);
        Mockito.verify(graphics, Mockito.times(1))
                .putString(28,32,"Press I to see the instructions",SGR.ITALIC);
        Mockito.verify(graphics, Mockito.times(1))
                .putString(12,19,"PLAY");
        Mockito.verify(graphics, Mockito.times(1))
                .putString(8,21,"SINGLEPLAYER");
        Mockito.verify(graphics, Mockito.times(1))
                .putString(42,19,"PLAY");
        Mockito.verify(graphics, Mockito.times(1))
                .putString(38,21,"MULTIPLAYER");
    }

    @Test
    public void testDrawMenuLevels() throws IOException {
        // A TerminalScreen Mock
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        // Execute
        View view = new View(60,60,screen);

        view.drawMenuLevels();

        Mockito.verify(graphics, Mockito.times(1))
                .putString(5,20,"EASY");
        Mockito.verify(graphics, Mockito.times(1))
                .putString(5,21,"LEVEL");
        Mockito.verify(graphics, Mockito.times(1))
                .putString(19,20,"MEDIUM");
        Mockito.verify(graphics, Mockito.times(1))
                .putString(19,21,"LEVEL");
        Mockito.verify(graphics, Mockito.times(1))
                .putString(34,20,"HARD");
        Mockito.verify(graphics, Mockito.times(1))
                .putString(34,21,"LEVEL");
        Mockito.verify(graphics, Mockito.times(1))
                .putString(46,20,"ULTRA-HARD");
        Mockito.verify(graphics, Mockito.times(1))
                .putString(49,21,"LEVEL");

    }

    @Test
    public void testDrawSelecting() throws IOException {
        // A TerminalScreen Mock
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        MenuModel menuModel = Mockito.mock(MenuModel.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        Mockito.when(menuModel.getCursorPosition()).thenReturn(new Position(5,5));

        // Execute
        View view = new View(60,60,screen);
        view.drawSelecting(menuModel);

        Mockito.verify(graphics, Mockito.times(1))
                .putString(5,5,"o",SGR.BOLD);
    }

    @Test
    public void testDrawGameOver() throws IOException {
        // A TerminalScreen Mock
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        // Execute
        View view = new View(60,60,screen);

        view.drawGameOver();

        Mockito.verify(graphics, Mockito.times(1))
                .putString(25,10,"GAME OVER",SGR.BLINK,SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1))
                .putString(10,20,"EXIT");
        Mockito.verify(graphics, Mockito.times(1))
                .putString(40,20,"REPLAY");

    }

    @Test
    public void testDrawResults() throws IOException {
        // A TerminalScreen Mock
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        // Execute
        View view = new View(60,60,screen);
        view.drawResults(5,6);

        Mockito.verify(graphics, Mockito.times(1))
                .putString(15,20, "5");
        Mockito.verify(graphics, Mockito.times(1))
                .putString(40,20, "6");
        Mockito.verify(graphics, Mockito.times(1))
                .putString(12,18,"SNAKE 1");
        Mockito.verify(graphics, Mockito.times(1))
                .putString(37,18,"SNAKE 2");

    }

    @Test
    public void testDrawInstructions() throws IOException {
        // A TerminalScreen Mock
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        // Execute
        View view = new View(60,60,screen);
        view.drawInstructions();


        Mockito.verify(graphics, Mockito.times(1)).putString(1,2,"Single Player",SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(2,4,"Use the keys UP/RIGHT/LEFT/DOWN to move the snake around");
        Mockito.verify(graphics, Mockito.times(1)).putString(2,6,"the arena, but be careful, never touch the wallsss!");
        Mockito.verify(graphics, Mockito.times(1)).putString(2,8,"You can eat the apples to gain points and grow your body: ");
        Mockito.verify(graphics, Mockito.times(1)).putString(4,10,"-> o are the regular apples ");
        Mockito.verify(graphics, Mockito.times(1)).putString(4,12,"-> S are the SSSSSpecial ones, your body shrinkssss ");
        Mockito.verify(graphics, Mockito.times(1)).putString(4,14,"and you get fasssster");
        Mockito.verify(graphics, Mockito.times(1)).putString(4,16,"-> ~ are the rotten ones, you lose points but they can ");
        Mockito.verify(graphics, Mockito.times(1)).putString(4,18,"be really useful once you want to sssslow down!");
        Mockito.verify(graphics, Mockito.times(1)).putString(2,20,"Final tip: the walls move");

        Mockito.verify(graphics, Mockito.times(1)).putString(1,22,"Multi Player",SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(2,24,"Grab a friend and come fight them in a multiple round ");
        Mockito.verify(graphics, Mockito.times(1)).putString(2,26,"snake game, they can use the keys W/A/D/S to play. Try to");
        Mockito.verify(graphics, Mockito.times(1)).putString(2,28,"kill their snake by putting your body in front of them!");
        Mockito.verify(graphics, Mockito.times(1)).putString(2,30,"Win 1 point per round! First to get to 3 wins the game!");
        Mockito.verify(graphics, Mockito.times(1)).putString(12,32,"GOOD LUCK, Press ENTER to go back",SGR.BOLD);
    }

    @Test
    public void testGetCommand() throws IOException {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        KeyStroke keyStroke = Mockito.mock(KeyStroke.class);
        Mockito.when(screen.pollInput()).thenReturn(keyStroke);

        // Execute
        View view = new View(60,60,screen);

        Mockito.when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowUp);
        assertEquals(view.getCommand().getClass(), ArrowUp.class);

        Mockito.when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowDown);
        assertEquals(view.getCommand().getClass(), ArrowDown.class);

        Mockito.when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowLeft);
        assertEquals(view.getCommand().getClass(), ArrowLeft.class);

        Mockito.when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowRight);
        assertEquals(view.getCommand().getClass(), ArrowRight.class);

        Mockito.when(keyStroke.getKeyType()).thenReturn(KeyType.Enter);
        assertEquals(view.getCommand().getClass(), EnterKey.class);

        Mockito.when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowUp);
        assertEquals(view.getCommand().getClass(), ArrowUp.class);

        Mockito.when(keyStroke.getKeyType()).thenReturn(null);

        Mockito.when(keyStroke.getCharacter()).thenReturn('w');
        assertEquals(view.getCommand().getClass(), WKey.class);

        Mockito.when(keyStroke.getCharacter()).thenReturn('s');
        assertEquals(view.getCommand().getClass(), SKey.class);

        Mockito.when(keyStroke.getCharacter()).thenReturn('d');
        assertEquals(view.getCommand().getClass(), DKey.class);

        Mockito.when(keyStroke.getCharacter()).thenReturn('a');
        assertEquals(view.getCommand().getClass(), AKey.class);

        Mockito.when(keyStroke.getCharacter()).thenReturn('i');
        assertEquals(view.getCommand().getClass(), IKey.class);

        Mockito.when(keyStroke.getCharacter()).thenReturn('q');
        assertEquals(view.getCommand().getClass(), EscKey.class);

        //test invalid input
        Mockito.when(keyStroke.getCharacter()).thenReturn('p');
        assertEquals(view.getCommand().getClass(), NullCommand.class);

        //null command
        Mockito.when(screen.pollInput()).thenReturn(null);
        assertEquals(view.getCommand().getClass(), NullCommand.class);
    }
}
