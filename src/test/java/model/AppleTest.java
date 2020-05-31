package model;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;

public class AppleTest {

    @Test
    public void testApple(){
        Apple apple = new Apple(1,1);

        assertEquals(apple.getPosition().getX(),1);
        assertEquals(apple.getPosition().getY(),1);

        apple.setPosition(new Position(3,2));

        assertEquals(apple.getPosition().getX(),3);
        assertEquals(apple.getPosition().getY(),2);


        assertEquals((char)apple.getChar(),'O');

        Snake snake = Mockito.mock(Snake.class);
        apple.affect(snake);
        Mockito.verify(snake, Mockito.times(1))
                .growSnake();

        Position previous = apple.getPosition();
        apple.changePosition();
        assertNotSame(previous,apple.getPosition());
    }

}
