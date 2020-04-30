package data;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
    public void testConstructor() {
        Snake snake = new Snake(headPosition);

        assertEquals(5, snake.getHeadPosition().getX());
        assertEquals(5, snake.getHeadPosition().getY());
    }

    @Test
    public void testSetPosition() {
        Snake snake = new Snake(headPosition);

        snake.setHeadPosition(new Position(15, 20));

        assertEquals(15, snake.getHeadPosition().getX());
        assertEquals(20, snake.getHeadPosition().getY());
    }

}
