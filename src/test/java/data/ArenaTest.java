package data;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ArenaTest {

    @Test
    public void getWidthTest() throws IOException {
        ArenaModel arena = new ArenaModel(5, 9);
        int val = arena.getWidth();
        assertEquals(5, val);
    }
    @Test
    public void getHeightTest() throws IOException {
        ArenaModel arena = new ArenaModel(5, 9);
        int val = arena.getHeight();
        assertEquals(9, val);
    }

    @Test
    public void testConstructor() throws IOException {
        ArenaModel arena = new ArenaModel(5, 5);
        Snake snake = new Snake(new Position(5 / 2, 5 / 2));
        assertEquals(snake.getPosition(),arena.getSnake().getPosition());
    }

    @Test
    public void testBuildWalls() throws IOException {
        ArenaModel arena = new ArenaModel(5, 5);
        arena.buildWalls();
        List<Wall> walls = new ArrayList<>();
        for(int i=0; i<5; i++){
            walls.add(new Wall(i,0));
            walls.add(new Wall(i,5-1));
        }
        for(int i=0; i<5; i++){
            walls.add(new Wall(0,i));
            walls.add(new Wall(5-1,i));
        }
    }


}
