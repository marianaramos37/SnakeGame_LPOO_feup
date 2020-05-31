package com.g44.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class SnakeTest {
    Position headPosition;
    List<Character> snakebody = new ArrayList<>();
    List<Position> pos = new ArrayList<>();
    int length=0;
    @Before
    public void setUp(){
        headPosition=new Position(5,5);
        snakebody.add('-');snakebody.add('-');snakebody.add('-');
        pos.add(headPosition);pos.add(new Position(6,5));pos.add(new Position(7,5));
        length=3;
    }

    @Test
    public void testConstructor() {
        Snake snake = new Snake(headPosition);
        assertEquals(5, snake.getPosition().getX());
        assertEquals(5, snake.getPosition().getY());
    }

    @Test
    public void testSetHeadPosition() {
        Snake snake = new Snake(headPosition);

        snake.setPosition(new Position(15, 20));

        assertEquals(15, snake.getPosition().getX());
        assertEquals(20, snake.getPosition().getY());
    }

    @Test
    public void testSetPos() {
        Snake snake = new Snake(headPosition);

        List<Position> newpos = new ArrayList<>();
        newpos.add(new Position(10,10));
        newpos.add(new Position(11,10));
        newpos.add(new Position(12,13));

        snake.setPos(newpos);

        assertEquals(newpos, snake.getPos());
    }

    @Test
    public void testSetLength() {
        Snake snake = new Snake(headPosition);
        snake.setLength(3);
        assertEquals(3, snake.getLength());
    }

    @Test
    public void testSetBody() {
        Snake snake = new Snake(headPosition);

        List<Character> newbody = new ArrayList<>();
        newbody.add('|');
        newbody.add('|');
        newbody.add('|');

        snake.setSnakeBody(newbody);

        assertEquals(newbody, snake.getSnakeBody());
    }

    @Test
    public void shrinkSnakeTest() {
        Snake snake = new Snake(headPosition);
        snake.setSnakeBody(snakebody);
        snake.shrink();
        assertTrue(snake.isShrink());
    }

    @Test
    public void unshrinkSnakeTest() {
        Snake snake = new Snake(headPosition);
        snake.setSnakeBody(snakebody);
        snake.unshrink();
        assertFalse(snake.isShrink());
    }

}
