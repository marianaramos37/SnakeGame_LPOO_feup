package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameOverModelTest {
    @Test
    public void testGameOverModel(){
        GameOverModel gameOverModel = new GameOverModel();

        //test common model methods
        assertFalse(gameOverModel.isSelected());

        gameOverModel.setOption(2);
        assertEquals(2,gameOverModel.getOption());
        gameOverModel.setOption(1);

        gameOverModel.setSelected(true);
        assertTrue(gameOverModel.selected);
        gameOverModel.setSelected(false);

        gameOverModel.setInitialCursorPosition();
        assertEquals(new Position(11,23),gameOverModel.getCursorPosition());

        Position initialPosition = gameOverModel.getCursorPosition();

        gameOverModel.changeOption("left");
        assertEquals(1, gameOverModel.getOption());
        assertEquals(initialPosition,gameOverModel.getCursorPosition());

        initialPosition.setX(initialPosition.getX()+32);

        gameOverModel.changeOption("right");
        assertEquals(2,gameOverModel.getOption());
        assertEquals(initialPosition,gameOverModel.getCursorPosition());

        gameOverModel.changeOption("right");
        gameOverModel.changeOption("right");
        gameOverModel.changeOption("right");
        gameOverModel.changeOption("right");
        gameOverModel.changeOption("right");
        assertEquals(2,gameOverModel.getOption());


        gameOverModel.changeOption("left");
        assertEquals(1, gameOverModel.getOption());

    }
}
