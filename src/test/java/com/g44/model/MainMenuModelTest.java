package com.g44.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainMenuModelTest {

    @Test
    public void testMainMenuModel(){
        MainMenuModel mainMenuModel = new MainMenuModel();
        mainMenuModel.setInitialCursorPosition();
        assertEquals(new Position(13,23),mainMenuModel.getCursorPosition());

        Position initialPosition = mainMenuModel.getCursorPosition();

        mainMenuModel.changeOption("left");
        assertEquals(1, mainMenuModel.getOption());
        assertEquals(initialPosition,mainMenuModel.getCursorPosition());

        initialPosition.setX(initialPosition.getX()+30);

        mainMenuModel.changeOption("right");
        assertEquals(2,mainMenuModel.getOption());
        assertEquals(initialPosition,mainMenuModel.getCursorPosition());

        mainMenuModel.changeOption("right");
        mainMenuModel.changeOption("right");
        mainMenuModel.changeOption("right");
        mainMenuModel.changeOption("right");
        mainMenuModel.changeOption("right");
        assertEquals(2,mainMenuModel.getOption());


        mainMenuModel.changeOption("left");
        assertEquals(1, mainMenuModel.getOption());

    }
}
