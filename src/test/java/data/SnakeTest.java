package data;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class SnakeTest {
    Position headPosition;
    List<Character> snake = new ArrayList<>();
    List<Position> pos = new ArrayList<>();
    int length=0;
    @Before
    public void setUp(){
        headPosition=new Position(5,5);
        snake.add('-');snake.add('-');snake.add('-');
        pos.add(headPosition);pos.add(new Position(6,5));pos.add(new Position(7,5));
        length=3;
    }

    @Test
    public void growSnakeTest(){
        Position position= new Position(5,5);
        Snake cobra= new Snake(position);
        cobra.growSnake();
        assertEquals(headPosition,cobra.getHeadPosition());
        assertEquals(snake,cobra.getSnake());
        assertEquals(length,cobra.getLength());
        assertEquals(pos,cobra.getPos());
    }
}
