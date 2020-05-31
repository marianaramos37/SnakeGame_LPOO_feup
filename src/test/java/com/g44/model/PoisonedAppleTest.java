package com.g44.model;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;

public class PoisonedAppleTest {

    @Test
    public void testPoisonedApple(){
        PoisonedApple poisonedApple = new PoisonedApple(6,1);

        assertEquals(poisonedApple.getPosition().getX(),6);
        assertEquals(poisonedApple.getPosition().getY(),1);

        poisonedApple.setPosition(new Position(3,3));

        assertEquals(poisonedApple.getPosition().getX(),3);
        assertEquals(poisonedApple.getPosition().getY(),3);


            assertEquals((char)poisonedApple.getChar(),'~');

        Snake snake = Mockito.spy(new Snake(new Position(3,3)));
        poisonedApple.affect(snake);
        Mockito.verify(snake, Mockito.times(1))
                .poison();


        Position previous = poisonedApple.getPosition();
        poisonedApple.changePosition();
        assertNotSame(previous,poisonedApple.getPosition());
    }
}
