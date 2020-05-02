package rules;

import data.ArenaModel;
import data.Position;
import data.Snake;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

public class SnakeControllerTest {
    Snake snakeMock;
    List<Character> snakebody = new ArrayList<>();
    List<Position> pos = new ArrayList<>();
    int vel=100;

    @Before
    public void setUp() {
        //ArenaStub with stub things
        snakeMock = Mockito.mock(Snake.class);
        snakebody.add('-');
        snakebody.add('-');
        snakebody.add('-');
        pos.add(new Position(5, 5));
        pos.add(new Position(6, 5));
        pos.add(new Position(7, 5));
        Mockito.when(snakeMock.getSnakeBody()).thenReturn(snakebody);
        Mockito.when(snakeMock.getPos()).thenReturn(pos);
        Mockito.when(snakeMock.getLength()).thenReturn(3);
    }

    @Test
    public void walkSnakeTest() {
        ArenaModel arena=Mockito.mock(ArenaModel.class);
        Mockito.when(arena.getSnake()).thenReturn((snakeMock));

        SnakeController snakeCtrl = new SnakeController(arena,vel);
        Position npos=new Position(8,5);
        snakeCtrl.walkSnake(npos, '-');

        Snake snakeMockAfter = Mockito.mock(Snake.class);
        List<Character> snakebodyAfter = new ArrayList<>();
        List<Position> posAfter = new ArrayList<>();
        snakebodyAfter.add('-');
        snakebodyAfter.add('-');
        snakebodyAfter.add('-');
        posAfter.add(new Position(6, 5));
        posAfter.add(new Position(7, 5));
        posAfter.add(new Position(8, 5));
        Mockito.when(snakeMockAfter.getSnakeBody()).thenReturn(snakebodyAfter);
        Mockito.when(snakeMockAfter.getPos()).thenReturn(posAfter);

        assertEquals(snakeMockAfter.getSnakeBody(),arena.getSnake().getSnakeBody());
        assertEquals(snakeMockAfter.getPos(),arena.getSnake().getPos());
    }

    @Test
    public void growSnakeTest() {
        ArenaModel arena=Mockito.mock(ArenaModel.class);
        Mockito.when(arena.getSnake()).thenReturn((snakeMock));

        SnakeController snakeCtrl = new SnakeController(arena,vel);
        snakeCtrl.growSnake();

        Snake snakeAfter=new Snake(new Position(5,5));
        List<Character> snakebodyAfter = new ArrayList<>();
        List<Position> posAfter = new ArrayList<>();
        snakebodyAfter.add('-');
        snakebodyAfter.add('-');
        snakebodyAfter.add('-');
        snakebodyAfter.add('-');
        posAfter.add(new Position(5, 5));
        posAfter.add(new Position(6, 5));
        posAfter.add(new Position(7, 5));
        posAfter.add(new Position(8, 5));
        snakeAfter.setSnakeBody(snakebodyAfter);
        snakeAfter.setPos(posAfter);

        assertEquals(snakeAfter.getSnakeBody(),arena.getSnake().getSnakeBody());
        assertEquals(snakeAfter.getPos(),arena.getSnake().getPos());
    }

    @Test
    public void shrinkSnakeTest() {
        ArenaModel arena=Mockito.mock(ArenaModel.class);
        Mockito.when(arena.getSnake()).thenReturn((snakeMock));

        SnakeController snakeCtrl = new SnakeController(arena,vel);
        snakeCtrl.shrink();

        assertTrue(snakeMock.isShrink);
    }

    @Test
    public void unshrinkSnakeTest() {
        ArenaModel arena=Mockito.mock(ArenaModel.class);
        Mockito.when(arena.getSnake()).thenReturn((snakeMock));

        SnakeController snakeCtrl = new SnakeController(arena,vel);
        snakeCtrl.unshrink();

        assertFalse(snakeMock.isShrink);
    }

}
