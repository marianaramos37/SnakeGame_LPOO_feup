package com.g44.commands;

import com.g44.model.ArenaModel;
import com.g44.model.MenuModel;

public class ArrowLeft implements Command{
    @Override
    public void executeArena(ArenaModel arena) {
        if(arena.getSnake().getDirection()!="right"){
            arena.getSnake().setDirection("left");
        }
    }

    @Override
    public void executeMenu(MenuModel menuModel) {
        menuModel.changeOption("left");
    }
}
