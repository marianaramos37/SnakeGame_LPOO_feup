package com.g44.model;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;

public class GhostAppleTest {

    @Test
    public void testGhostApple(){
        GhostApple ghostApple = new GhostApple(5,1);

        assertEquals(ghostApple.getPosition().getX(),5);
        assertEquals(ghostApple.getPosition().getY(),1);

        ghostApple.setPosition(new Position(3,3));

        assertEquals(ghostApple.getPosition().getX(),3);
        assertEquals(ghostApple.getPosition().getY(),3);


        assertEquals((char)ghostApple.getChar(),'G');

        Snake snake = Mockito.spy(new Snake(new Position(3,3)));
        ghostApple.affect(snake);
        Mockito.verify(snake, Mockito.times(1))
                .setGhost(true);
        assertEquals(true,snake.isGhost());

        Position previous = ghostApple.getPosition();
        ghostApple.changePosition();
        assertNotSame(previous,ghostApple.getPosition());
    }
}
