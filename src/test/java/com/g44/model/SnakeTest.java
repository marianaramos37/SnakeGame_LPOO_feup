package com.g44.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
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
        assertEquals(false, snake.getLoser());
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
    public void walkSnakeTest() throws IOException {
        //right
        Snake snake= new Snake(new Position(7,5));

        snake.setDirection("right");

        snake.walkSnake();

        List<Character> snakebodyAfter = new ArrayList<>();
        List<Position> posAfter = new ArrayList<>();
        snakebodyAfter.add('-');
        snakebodyAfter.add('-');
        posAfter.add(new Position(8, 5));
        posAfter.add(new Position(7, 5));

        TestCase.assertEquals(snakebodyAfter,snake.getSnakeBody());
        TestCase.assertEquals(posAfter,snake.getPos());

        //left
        Snake snake2= new Snake(new Position(7,5));

        snake2.setDirection("left");

        snake2.walkSnake();

        List<Character> snakebodyAfter2 = new ArrayList<>();
        List<Position> posAfter2 = new ArrayList<>();
        snakebodyAfter2.add('-');
        snakebodyAfter2.add('-');
        posAfter2.add(new Position(6, 5));
        posAfter2.add(new Position(7, 5));

        TestCase.assertEquals(snakebodyAfter2,snake2.getSnakeBody());
        TestCase.assertEquals(posAfter2,snake2.getPos());

        //up
        Snake snake3= new Snake(new Position(7,5));

        snake3.setDirection("up");

        snake3.walkSnake();

        List<Character> snakebodyAfter3 = new ArrayList<>();
        List<Position> posAfter3 = new ArrayList<>();
        snakebodyAfter3.add('|');
        snakebodyAfter3.add('-');
        posAfter3.add(new Position(7, 4));
        posAfter3.add(new Position(7, 5));

        TestCase.assertEquals(snakebodyAfter3,snake3.getSnakeBody());
        TestCase.assertEquals(posAfter3,snake3.getPos());

        //down
        Snake snake4= new Snake(new Position(7,5));

        snake4.setDirection("down");

        snake4.walkSnake();

        List<Character> snakebodyAfter4 = new ArrayList<>();
        List<Position> posAfter4 = new ArrayList<>();
        snakebodyAfter4.add('|');
        snakebodyAfter4.add('-');
        posAfter4.add(new Position(7, 6));
        posAfter4.add(new Position(7, 5));

        TestCase.assertEquals(snakebodyAfter4,snake4.getSnakeBody());
        TestCase.assertEquals(posAfter4,snake4.getPos());
    }

    @Test
    public void growSnakeTest() throws IOException {
        //grow horizontally
        Snake snake= new Snake(new Position(7,5));

        Snake snakeAfter=new Snake(new Position(7,5));
        List<Character> snakebodyAfter = new ArrayList<>();
        List<Position> posAfter = new ArrayList<>();
        snakebodyAfter.add('-');
        snakebodyAfter.add('-');
        snakebodyAfter.add('-');
        posAfter.add(new Position(7, 5));
        posAfter.add(new Position(6, 5));
        posAfter.add(new Position(5, 5));
        snakeAfter.setSnakeBody(snakebodyAfter);
        snakeAfter.setPos(posAfter);

        snake.growSnake();

        TestCase.assertEquals(snakeAfter.getSnakeBody(),snake.getSnakeBody());
        TestCase.assertEquals(snakeAfter.getPos(),snake.getPos());

        //grow vertically
        Snake snake2= new Snake(new Position(7,5));

        List<Character> verticalSnake = new ArrayList<>();
        List<Position> vPos=new ArrayList<>();

        verticalSnake.add('|');
        verticalSnake.add('|');
        vPos.add(new Position(7,5));
        vPos.add(new Position(7,6));

        snake2.setSnakeBody(verticalSnake);
        snake2.setPos(vPos);

        Snake snakeAfter2=new Snake(new Position(7,5));
        List<Character> snakebodyAfter2 = new ArrayList<>();
        List<Position> posAfter2 = new ArrayList<>();
        snakebodyAfter2.add('|');
        snakebodyAfter2.add('|');
        snakebodyAfter2.add('|');
        posAfter2.add(new Position(7, 5));
        posAfter2.add(new Position(7, 6));
        posAfter2.add(new Position(7, 7));
        snakeAfter2.setSnakeBody(snakebodyAfter2);
        snakeAfter2.setPos(posAfter2);

        snake2.growSnake();

        TestCase.assertEquals(snakeAfter2.getSnakeBody(),snake2.getSnakeBody());
        TestCase.assertEquals(snakeAfter2.getPos(),snake2.getPos());
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

    @Test
    public void poisonTest(){
        Snake snake = new Snake(headPosition);
        snakebody.add('-');snakebody.add('-');snakebody.add('-');snakebody.add('-');
        pos.add(new Position(8,5));pos.add(new Position(9,5));pos.add(new Position(10,5));pos.add(new Position(11,5));
        snake.setLength(6);
        snake.setSnakeBody(snakebody);
        snake.setPos(pos);

        snake.poison();
        assertEquals(1,snake.getLength());
        assertEquals(2,snake.getSnakeBody().size());
        assertEquals(2,snake.getPos().size());

        //once poisoned again, nothing changes
        snake.poison();
        assertEquals(1,snake.getLength());
        assertEquals(2,snake.getSnakeBody().size());
        assertEquals(2,snake.getPos().size());
    }

    @Test
    public void backToNormalTest(){
        Snake snake = new Snake(headPosition);
        snake.setSnakeBody(snakebody);
        snake.setPos(pos);

        snake.shrink();
        snake.goBackToNormal();
        assertEquals(false,snake.isShrink());

        snake.setGhost(true);
        snake.goBackToNormal();
        assertEquals(false,snake.isGhost());
    }

}
