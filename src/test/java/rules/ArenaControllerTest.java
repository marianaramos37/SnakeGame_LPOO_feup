package rules;

import data.*;
import gui.ArenaView;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

public class ArenaControllerTest {

    @Test
    public void eatAppleTest(){
//?????????????????????????????????
    }

    @Test
    public void checkCollisionsTest() throws IOException {
        //??????????????????????????????????????????????????
        Snake snake=new Snake(new Position(5,5));
        List<Position> pos=new ArrayList<>();
        pos.add(new Position(5,5));
        pos.add(new Position(6,5));
        pos.add(new Position(7,5));
        snake.setPos(pos);
        Wall wall = new Wall(10,11);
        Apple apple=new Apple(10,12);
        List<AppleInterface> apples=new ArrayList<>();
        List<Wall> walls=new ArrayList<>();
        apples.add(apple);
        walls.add(wall);
        SinglePlayerScore score = new SinglePlayerScore();
        score.setScore(3);

        ArenaModel arena= new ArenaModel(30,30);
        arena.setSnake(snake);
        arena.setWalls(walls);
        arena.setApples(apples);
        arena.setScore(score);

        ArenaController arenaController=Mockito.mock(ArenaController.class);
        Mockito.when(arenaController.getCollidingApples(new Position(10,12),apples)).thenReturn(apple);
        Mockito.when(arenaController.getCollidingApples(new Position(30,31),apples)).thenReturn(null);
        Mockito.when(arenaController.getCollidingElement(new Position(10,11),walls)).thenReturn(wall);
        Mockito.when(arenaController.getCollidingElement(new Position(30,31),walls)).thenReturn(null);
        Mockito.when(arenaController.getCollidingBody(new Position(6,5),pos)).thenReturn(true);
        Mockito.when(arenaController.getCollidingBody(new Position(30,31),pos)).thenReturn(false);

        arenaController.checkCollisions(new Position(10,12),arena);
        assertEquals(4,arena.getScore().getScore());
    }

    @Test
    public void getColidingElemsTest() throws IOException {
        Snake snakeMock=Mockito.mock(Snake.class);
        Mockito.when(snakeMock.getPosition()).thenReturn(new Position(5,5));
        Wall wall1 = new Wall(5,6);
        Wall wall2 = new Wall(2,3);

        List<Element> elems=new ArrayList<>();
        elems.add(wall1);
        elems.add(wall2);
        elems.add(snakeMock);

        ArenaController arenaController= new ArenaController(new ArenaView(20,20),new ArenaModel(20,20));

        assertEquals(wall2,arenaController.getCollidingElement(new Position(2,3),elems));
        assertEquals(snakeMock,arenaController.getCollidingElement(new Position(5,5),elems));
        assertEquals(wall1,arenaController.getCollidingElement(new Position(5,6),elems));
    }

    @Test
    public void getColidingApplesTest() throws IOException {
        Apple apple1=new Apple(5,5);
        Apple apple2=new Apple(6,5);
        SpecialApple apple3 = new SpecialApple(7,7);

        List<AppleInterface> apples=new ArrayList<>();
        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);

        ArenaController arenaController= new ArenaController(new ArenaView(20,20),new ArenaModel(20,20));

        assertNull( arenaController.getCollidingApples(new Position(2,3),apples));
        assertEquals(apple1,arenaController.getCollidingApples(new Position(5,5),apples));
        assertEquals(apple2, arenaController.getCollidingApples(new Position(6,5),apples));
        assertEquals(apple3,arenaController.getCollidingApples(new Position(7,7),apples));
    }

    @Test
    public void getCollidingBodyTest() throws IOException {

        List<Position> pos=new ArrayList<>();
        pos.add(new Position(5,5));
        pos.add(new Position(5,6));
        pos.add(new Position(5,7));

        ArenaController arenaController= new ArenaController(new ArenaView(20,20),new ArenaModel(20,20));

        assertFalse(arenaController.getCollidingBody(new Position(2, 3), pos));
        assertTrue(arenaController.getCollidingBody(new Position(5, 6), pos));
    }
}
