package commands;

import model.ArenaModel;
import model.MenuModel;
import model.Position;
import model.Snake;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;

public class CommandTest {

    ArenaModel arenaModel = Mockito.mock(ArenaModel.class);
    MenuModel menuModel = Mockito.mock(MenuModel.class);
    Snake snake = Mockito.spy(new Snake(new Position(1,1)));

    @Before
    public void setUp(){
        Mockito.when(arenaModel.getSnake()).thenReturn(snake);
        Mockito.when(arenaModel.getSnake2()).thenReturn(snake);
    }
    

    @Test
    public void testAKey(){

        AKey akey=new AKey();

        snake.setDirection("right");
        akey.executeArena(arenaModel);
        //when right, direction doesn't change
        assertEquals("right",snake.getDirection());

        snake.setDirection("up");
        akey.executeArena(arenaModel);
        //changes to left
        assertEquals("left",snake.getDirection());
    }

    @Test
    public void testArrowDown(){

        ArrowDown arrowDown=new ArrowDown();

        snake.setDirection("up");
        arrowDown.executeArena(arenaModel);
        //when up, direction doesn't change
        assertEquals("up",snake.getDirection());

        snake.setDirection("right");
        arrowDown.executeArena(arenaModel);
        //changes to down
        assertEquals("down",snake.getDirection());
    }

    @Test
    public void testArrowLeft(){

        ArrowLeft arrowLeft=new ArrowLeft();

        snake.setDirection("right");
        arrowLeft.executeArena(arenaModel);
        //when right, direction doesn't change
        assertEquals("right",snake.getDirection());

        snake.setDirection("down");
        arrowLeft.executeArena(arenaModel);
        //changes to left
        assertEquals("left",snake.getDirection());


        arrowLeft.executeMenu(menuModel);
        Mockito.verify(menuModel, Mockito.times(1))
                .changeOption("left");

    }

    @Test
    public void testArrowRight(){

        ArrowRight arrowRight=new ArrowRight();

        snake.setDirection("left");
        arrowRight.executeArena(arenaModel);
        //when left, direction doesn't change
        assertEquals("left",snake.getDirection());

        snake.setDirection("down");
        arrowRight.executeArena(arenaModel);
        //changes to right
        assertEquals("right",snake.getDirection());

        arrowRight.executeMenu(menuModel);
        Mockito.verify(menuModel, Mockito.times(1))
                .changeOption("right");
    }

    @Test
    public void testArrowUp(){

        ArrowUp arrowUp=new ArrowUp();

        snake.setDirection("down");
        arrowUp.executeArena(arenaModel);
        //when down, direction doesn't change
        assertEquals("down",snake.getDirection());

        snake.setDirection("right");
        arrowUp.executeArena(arenaModel);
        //changes to up
        assertEquals("up",snake.getDirection());
    }

    @Test
    public void testDKey(){

        DKey dKey=new DKey();

        snake.setDirection("left");
        dKey.executeArena(arenaModel);
        //when right, direction doesn't change
        assertEquals("left",snake.getDirection());

        snake.setDirection("down");
        dKey.executeArena(arenaModel);
        //changes to right
        assertEquals("right",snake.getDirection());
    }

    @Test
    public void testEnterKey(){

        EnterKey enterKey=new EnterKey();

        enterKey.executeMenu(menuModel);
        Mockito.verify(menuModel, Mockito.times(1))
                .setSelected(true);
    }

    @Test
    public void testEscKey(){

        EscKey escKey=new EscKey();

        escKey.executeMenu(menuModel);
        Mockito.verify(menuModel, Mockito.times(1))
                .setOption(4);
    }

    @Test
    public void testIKey(){

        IKey iKey=new IKey();

        iKey.executeMenu(menuModel);
        Mockito.verify(menuModel, Mockito.times(1))
                .setOption(3);
    }

    @Test
    public void testSKey(){

        SKey sKey=new SKey();

        snake.setDirection("up");
        sKey.executeArena(arenaModel);
        //when up, direction doesn't change
        assertEquals("up",snake.getDirection());

        snake.setDirection("right");
        sKey.executeArena(arenaModel);
        //changes to down
        assertEquals("down",snake.getDirection());
    }

    @Test
    public void testWKey(){

        WKey wKey=new WKey();

        snake.setDirection("down");
        wKey.executeArena(arenaModel);
        //when down, direction doesn't change
        assertEquals("down",snake.getDirection());

        snake.setDirection("right");
        wKey.executeArena(arenaModel);
        //changes to up
        assertEquals("up",snake.getDirection());
    }
}
