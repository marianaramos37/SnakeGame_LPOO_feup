package commands;

import model.ArenaModel;
import model.Snake;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CommandTest {

    ArenaModel arenaModel = Mockito.mock(ArenaModel.class);

    @Before
    public void setUp(){
        Snake snake = Mockito.mock(Snake.class);
        Mockito.when(arenaModel.getSnake()).thenReturn(snake);
        Mockito.when(arenaModel.getSnake2()).thenReturn(snake);
        Mockito.when(snake.getDirection()).thenReturn("left");
    }

    @Test
    public void testAKey(){

        AKey akey=new AKey();

        akey.executeArena(arenaModel);
        

    }
}
