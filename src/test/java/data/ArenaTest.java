package data;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;

public class ArenaTest {
    Snake snakeMock;
    ArenaModel arenaMock;

    @Before
    public void setUp(){
        snakeMock= Mockito.mock(Snake.class);
        arenaMock= Mockito.mock(ArenaModel.class);
    }

    @Test
    public void getWidthTest(){
        ArenaModel arena = new ArenaModel(5, 9);
        int val = arena.getWidth();
        assertEquals(5, val);
    }
    @Test
    public void getHeightTest(){
        ArenaModel arena = new ArenaModel(5, 9);
        int val = arena.getHeight();
        assertEquals(9, val);
    }
    @Test
    public void getSnakeTest(){
        /*ArenaModel arena = new ArenaModel(5, 9);
        arena.setSnake
        assertEquals(9, val);*/
    }



}
