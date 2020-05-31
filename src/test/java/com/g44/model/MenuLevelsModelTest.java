package com.g44.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MenuLevelsModelTest {

    @Test
    public void testMenuLevelsModel(){
        MenuLevelsModel menuLevelsModel = new MenuLevelsModel();
        menuLevelsModel.setInitialCursorPosition();
        assertEquals(new Position(6,23),menuLevelsModel.getCursorPosition());

        Position initialPosition = menuLevelsModel.getCursorPosition();

        menuLevelsModel.changeOption("left");
        assertEquals(1, menuLevelsModel.getOption());
        assertEquals(initialPosition,menuLevelsModel.getCursorPosition());

        initialPosition.setX(initialPosition.getX()+15);

        menuLevelsModel.changeOption("right");
        assertEquals(2,menuLevelsModel.getOption());
        assertEquals(initialPosition,menuLevelsModel.getCursorPosition());

        menuLevelsModel.changeOption("right");
        menuLevelsModel.changeOption("right");
        menuLevelsModel.changeOption("right");
        menuLevelsModel.changeOption("right");
        menuLevelsModel.changeOption("right");
        assertEquals(4,menuLevelsModel.getOption());


        menuLevelsModel.changeOption("left");
        assertEquals(3, menuLevelsModel.getOption());

    }
}
