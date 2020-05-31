package com.g44.model;

import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static junit.framework.TestCase.assertEquals;

public class SpecialAppleTest {

    @Test
    public void testSpecialApple(){
        SpecialApple specialApple = new SpecialApple(2,1);

        assertEquals(specialApple.getPosition().getX(),2);
        assertEquals(specialApple.getPosition().getY(),1);

        specialApple.setPosition(new Position(3,3));

        assertEquals(specialApple.getPosition().getX(),3);
        assertEquals(specialApple.getPosition().getY(),3);


        assertEquals((char)specialApple.getChar(),'S');

        Snake snake = Mockito.spy(new Snake(new Position(3,3)));
        specialApple.affect(snake);
        Mockito.verify(snake, Mockito.times(1))
                .shrink();
        assertEquals(75,snake.getVelocidade());

        Position previous = specialApple.getPosition();
        specialApple.changePosition();
        assertThat(previous,not(specialApple.getPosition()));
    }
}
