package data;
import model.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void getTest(){
        Position position = new Position(5, 5);
        assertEquals(5, position.getX());
        assertEquals(5, position.getY());
    }

    @Test
    public void setXTest(){
        Position position = new Position(5, 5);
        position.setX(0);
        assertEquals(0, position.getX());
        assertEquals(5, position.getY());
    }

    @Test
    public void setYTest(){
        Position position = new Position(5, 5);
        position.setY(0);
        assertEquals(5, position.getX());
        assertEquals(0, position.getY());
    }

    @Test
    public void upPositionTest(){
        Position position = new Position(5, 5);
        Position retPos = position.up();
        assertEquals(4, retPos.getY());
        assertEquals(5, retPos.getX());
    }

    @Test
    public void downPositionTest(){
        Position position = new Position(5, 5);
        Position retPos = position.down();
        assertEquals(6, retPos.getY());
        assertEquals(5, retPos.getX());
    }

    @Test
    public void rightPositionTest(){
        Position position = new Position(5, 5);
        Position retPos = position.right();
        assertEquals(5, retPos.getY());
        assertEquals(6, retPos.getX());
    }

    @Test
    public void leftPositionTest(){
        Position position = new Position(5, 5);
        Position retPos = position.left();
        assertEquals(5, retPos.getY());
        assertEquals(4, retPos.getX());
    }


    @Test
    public void equalsTest(){
        Position p1 = new Position(5, 5);
        Position p2 = new Position(5, 5);
        Position p3 = new Position(5, 0);

        assertTrue(p1.equals(p2));
        assertTrue(p2.equals(p1));
        assertFalse(p3.equals(p1));
        assertFalse(p3.equals(p2));
        assertFalse(p1.equals(p3));
        assertFalse(p2.equals(p3));
    }
}
