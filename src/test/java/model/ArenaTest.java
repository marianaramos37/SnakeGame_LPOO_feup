package model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
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
        when(snakeMock.isShrink()).thenReturn(false);
    }

    @Test
    public void testConstructor() throws IOException {
        ArenaModel arena = new ArenaModel(5, 5);
        Snake snake = new Snake(new Position(5 / 2, 5 / 2));
        TestCase.assertEquals(snake.getPosition(),arena.getSnake().getPosition());
    }

    @Test
    public void setAndGetTopScoreTest() throws IOException {
        ArenaModel arena = new ArenaModel(30,30);

        SinglePlayerTopScore singlePlayerTopScore = Mockito.spy(new SinglePlayerTopScore());
        singlePlayerTopScore.setFilename("src/main/java/files/testTopScore.txt");
        singlePlayerTopScore.fileReader();

        arena.setTopScore(singlePlayerTopScore);

        assertSame(singlePlayerTopScore,arena.getTopScore());
        assertEquals(89,arena.getTopScore().getScore());

    }

    @Test
    public void testFileConstructor() throws IOException {
        ArenaModel arena = new ArenaModel(50, 50,"src/main/java/files/mapMedium.txt");
        assertEquals(50,arena.getSnake().getPosition().getX());
        assertEquals(17,arena.getSnake().getPosition().getY());
        assertThat(0,not(arena.getWalls().size()));
    }

    @Test
    public void testGetWidthAndHeight() throws IOException {
        ArenaModel arena = new ArenaModel(5, 5);
        assertEquals(5,arena.getHeight());
        assertEquals(5,arena.getWidth());
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
    public void testRestartGame() throws IOException {
        ArenaModel arena = new ArenaModel(50, 50,"src/main/java/files/mapMedium.txt");
        arena.restartGame();

        assertEquals(false,arena.getSnake().getLoser());
        assertEquals(false,arena.getSnake2().getLoser());
        assertEquals(false,arena.getGameOver());
    }

    @Test
    public void getCollidingTest() throws IOException {

        Wall wall1 = new Wall(2,3);
        AppleInterface apple = new Apple(10,10);
        List<Wall> elems=new ArrayList<>();
        elems.add(wall1);
        List<AppleInterface> apples=new ArrayList<>();
        apples.add(apple);

        ArenaModel arenaModel=new ArenaModel(30,30);
        arenaModel.setSnake(snakeMock);
        arenaModel.setWalls(elems);
        arenaModel.setApples(apples);


        TestCase.assertTrue(arenaModel.getCollidingBody(new Position(9, 7)));
        TestCase.assertEquals(wall1,arenaModel.getCollidingElement(new Position(2,3)));
        TestCase.assertNull(arenaModel.getCollidingElement(new Position(15, 15)));
        TestCase.assertEquals(apple,arenaModel.getCollidingApples(new Position(10,10)));
        TestCase.assertNull(arenaModel.getCollidingApples(new Position(16, 16)));
    }
    
    @Test
    public void eatAppleTest() throws IOException {
        SpecialApple appleS = new SpecialApple(7,7);
        PoisonedApple appleP = new PoisonedApple(8,7);
        GhostApple appleG = new GhostApple(4,9);
        Apple apple = new Apple(3,3);
        List<AppleInterface> apples=new ArrayList<>();
        apples.add(appleS);
        apples.add(appleP);
        apples.add(appleG);
        apples.add(apple);

        Snake snake= new Snake(new Position(5,5));

        ArenaModel arenaModel=new ArenaModel(30,30);
        arenaModel.setApples(apples);
        arenaModel.setSnake(snake);

        appleP.affect(snake);
        assertEquals(150,arenaModel.getSnake().getVelocidade());


        appleS.affect(snake);
        TestCase.assertTrue(arenaModel.getSnake().isShrink());


        appleG.affect(snake);
        TestCase.assertTrue(arenaModel.getSnake().isGhost());

        int previousLength = arenaModel.getSnake().getLength();
        apple.affect(snake);
        assertEquals(previousLength+1,arenaModel.getSnake().getLength());
    }

    @Test
    public void checkCollisionsTest() throws IOException {
        Snake snake=new Snake(new Position(7,5));

        Wall wall = new Wall(10,11);
        Apple apple=new Apple(7,5);
        List<AppleInterface> apples=new ArrayList<>();
        List<Wall> walls=new ArrayList<>();
        apples.add(apple);
        walls.add(wall);

        ArenaModel arena= new ArenaModel(30,30);
        arena.setSnake(snake);
        arena.setWalls(walls);
        arena.setApples(apples);
        arena.setScore(3);


        arena.checkCollisions( arena.getSnake());   //eats apple
        assertEquals(4,arena.getScore());
        assertEquals(2,arena.getSnake().getLength());

        arena.checkCollisions(arena.getSnake());  //hits nothing
        TestCase.assertFalse(arena.getGameOver());

        snake.setPosition(new Position(10,11));
        arena.checkCollisions(arena.getSnake()); //hits wall
        TestCase.assertTrue(arena.getGameOver());
    }

    @Test
    public void checkAttackTest() throws IOException {
        ArenaModel arena= new ArenaModel(30,30);

        Snake snake = Mockito.spy(new Snake(new Position(0,0)));
        Snake snake2 = Mockito.spy(new Snake(new Position(0,0)));

        arena.setSnake(snake);
        arena.setSnake2(snake2);

        arena.checkAttack();
        TestCase.assertTrue(arena.getSnake().getLoser());
        TestCase.assertTrue(arena.getSnake2().getLoser());


        Snake snake3 = Mockito.spy(new Snake(new Position(5,5)));
        Snake snake4 = Mockito.spy(new Snake(new Position(4,5)));

        arena.setSnake(snake3);
        arena.setSnake2(snake4);

        arena.checkAttack();
        TestCase.assertFalse(arena.getSnake().getLoser());
        TestCase.assertTrue(arena.getSnake2().getLoser());


        Snake snake5 = Mockito.spy(new Snake(new Position(3,5)));
        Snake snake6 = Mockito.spy(new Snake(new Position(4,5)));

        arena.setSnake(snake5);
        arena.setSnake2(snake6);

        arena.checkAttack();
        TestCase.assertTrue(arena.getSnake().getLoser());
        TestCase.assertFalse(arena.getSnake2().getLoser());
    }

    @Test
    public void randomWallsTest() throws IOException {
        Snake snake=new Snake(new Position(7,5));
        Wall wall = new Wall(10,11);
        Apple apple=new Apple(7,5);
        List<AppleInterface> apples=new ArrayList<>();
        List<Wall> walls=new ArrayList<>();
        apples.add(apple);
        walls.add(wall);

        ArenaModel arena= new ArenaModel(30,30);
        arena.setSnake(snake);
        arena.setWalls(walls);
        arena.setApples(apples);

        arena.randomWalls();

        assertEquals(3,arena.getObstaculos().size());
        assertThat(arena.getObstaculos().get(0).getPosition(), not(arena.getWalls().get(0).getPosition()));
        assertThat(arena.getObstaculos().get(0).getPosition(), not(arena.getApples().get(0).getPosition()));
        assertThat(arena.getObstaculos().get(0).getPosition(), not(arena.getSnake().getPos().get(0)));
        assertThat(arena.getObstaculos().get(0).getPosition(), not(arena.getSnake().getPos().get(1)));
    }

    @Test
    public void updateVelocidadeTest() throws IOException {
        ArenaModel arenaModel = new ArenaModel(30,30);
        Snake snake=Mockito.spy(new Snake(new Position( 9,9)));
        arenaModel.setSnake(snake);

        arenaModel.setScore(2);
        arenaModel.updateVelocidadeSnake();
        assertEquals(150,arenaModel.getSnake().getVelocidade());

        arenaModel.setScore(5);
        arenaModel.updateVelocidadeSnake();
        assertEquals(145, arenaModel.getSnake().getVelocidade());

    }
}
