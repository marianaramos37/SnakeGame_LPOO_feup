package data;

import model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.when;

public class ArenaTest {

    Snake snakeMock;
    List<Character> snakebody = new ArrayList<>();
    List<Position> pos = new ArrayList<>();

    @Before
    public void setUp() {
        snakeMock = Mockito.mock(Snake.class);
        snakebody.add('-');
        snakebody.add('-');
        snakebody.add('-');
        pos.add(new Position(7, 7));
        pos.add(new Position(8, 7));
        pos.add(new Position(9, 7));
        when(snakeMock.getSnakeBody()).thenReturn(snakebody);
        when(snakeMock.getPos()).thenReturn(pos);
        when(snakeMock.getLength()).thenReturn(3);
        when(snakeMock.getShrink()).thenReturn(false);
    }

    @Test
    public void testConstructor() throws IOException {
        ArenaModel arena = new ArenaModel(5, 5);
        Snake snake = new Snake(new Position(5 / 2, 5 / 2));
        assertEquals(snake.getPosition(),arena.getSnake().getPosition());
    }

    @Test
    public void testBuildWalls() throws IOException {
        ArenaModel arena = new ArenaModel(5, 5);
        arena.buildWalls();
        List<Wall> walls = new ArrayList<>();
        for(int i=0; i<5; i++){
            walls.add(new Wall(i,0));
            walls.add(new Wall(i,4));
        }
        for(int i=0; i<5; i++){
            walls.add(new Wall(0,i));
            walls.add(new Wall(4,i));
        }
        assertEquals(walls.size(),arena.getWalls().size());
    }

    @Test
    public void walkSnakeTest() throws IOException {
        ArenaModel arena= new ArenaModel(30,30);
        Snake snake= new Snake(new Position(7,5));

        arena.setSnake(snake);

        Position npos=new Position(8,5);
        arena.walkSnake(npos, '-',snake);

        //System.out.println(snake.getPos().get(0).getX());

        List<Character> snakebodyAfter = new ArrayList<>();
        List<Position> posAfter = new ArrayList<>();
        snakebodyAfter.add('-');
        snakebodyAfter.add('-');
        posAfter.add(new Position(8, 5));
        posAfter.add(new Position(7, 5));

        assertEquals(snakebodyAfter,snake.getSnakeBody());
        assertEquals(posAfter,snake.getPos());
    }

    @Test
    public void growSnakeTest() throws IOException {
        ArenaModel arena=new ArenaModel(30,30);
        Snake snake= new Snake(new Position(7,5));

        arena.setSnake(snake);

        Snake snakeAfter=new Snake(new Position(7,5));
        List<Character> snakebodyAfter = new ArrayList<>();
        List<Position> posAfter = new ArrayList<>();
        snakebodyAfter.add('-');
        snakebodyAfter.add('-');
        snakebodyAfter.add('-');
        posAfter.add(new Position(7, 5));
        posAfter.add(new Position(6, 5));
        posAfter.add(new Position(5, 5));
        snakeAfter.setSnakeBody(snakebodyAfter);
        snakeAfter.setPos(posAfter);

        arena.growSnake(snake);

        assertEquals(snakeAfter.getSnakeBody(),arena.getSnake().getSnakeBody());
        assertEquals(snakeAfter.getPos(),arena.getSnake().getPos());
    }

    @Test
    public void getColidingTest() throws IOException {

        Wall wall1 = new Wall(2,3);
        AppleInterface apple = new Apple(10,10);
        List<Wall> elems=new ArrayList<>();
        elems.add(wall1);
        List<AppleInterface> apples=new ArrayList<>();
        apples.add(apple);

        ArenaModel arenaModel=Mockito.mock(ArenaModel.class);
        when(arenaModel.getSnake()).thenReturn(snakeMock);
        when(arenaModel.getWalls()).thenReturn(elems);
        when(arenaModel.getApples()).thenReturn(apples);
        when(arenaModel.getCollidingElement(new Position(5,5))).thenCallRealMethod();
        when(arenaModel.getCollidingElement(new Position(2,3))).thenCallRealMethod();
        when(arenaModel.getCollidingApples(new Position(10,10))).thenCallRealMethod();
        when(arenaModel.getCollidingBody(new Position(9,7))).thenCallRealMethod();

        assertTrue(arenaModel.getCollidingBody(new Position(9, 7)));
        assertEquals(wall1,arenaModel.getCollidingElement(new Position(2,3)));
        assertNull(arenaModel.getCollidingElement(new Position(15, 15)));
        assertEquals(apple,arenaModel.getCollidingApples(new Position(10,10)));
        assertNull(arenaModel.getCollidingApples(new Position(16, 16)));
    }

    @Test
    public void eatAppleTest() throws IOException {
        /*SpecialApple appleS = new SpecialApple(7,7);
        PoisonedApple appleP = new PoisonedApple(8,7);
        List<AppleInterface> apples=new ArrayList<>();
        apples.add(appleS);
        apples.add(appleP);

        Snake snake= new Snake(new Position(5,5));

        ArenaModel arenaModel=Mockito.mock(ArenaModel.class);
        when(arenaModel.getApples()).thenReturn(apples);
        arenaModel.setSnake(snake);
        when(arenaModel.getSnake()).thenCallRealMethod();

        arenaModel.eatenApple(appleP);
        assertEquals(75,arenaModel.getSnake().getVelocidade());
        assertTrue(arenaModel.getSnake().isShrink);

        ArenaController arenaController2= new ArenaController(gui,arena2);
        arenaController2.eatenApple(apple2,arena2);
        assertEquals(150,arenaController2.getSnakeController().getVelocidade());
        assertFalse(arena2.getSnake().isShrink);*/
    }

    @Test
    public void checkCollisionsTest() throws IOException {
        Snake snake=new Snake(new Position(7,5));

        Wall wall = new Wall(10,11);
        Apple apple=new Apple(10,12);
        List<AppleInterface> apples=new ArrayList<>();
        List<Wall> walls=new ArrayList<>();
        apples.add(apple);
        walls.add(wall);
        int score=3;

        ArenaModel arena= new ArenaModel(30,30);
        arena.setSnake(snake);
        arena.setWalls(walls);
        arena.setApples(apples);
        arena.setScore(score);


        arena.checkCollisions(new Position(10,12),snake); //primeiro

        assertEquals(4,arena.getScore());
        assertEquals(2,arena.getSnake().getLength());

        arena.checkCollisions(new Position(3,3),snake); //segundo
        assertFalse(arena.getGameOver());

        arena.checkCollisions(new Position(10,11),snake); //terceiro
        assertTrue(arena.getGameOver());
    }


}
