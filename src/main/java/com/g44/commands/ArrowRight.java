package com.g44.commands;

import com.g44.model.ArenaModel;
import com.g44.model.MenuModel;

public class ArrowRight implements Command{
    @Override
    public void executeArena(ArenaModel arena) {
        if(arena.getSnake().getDirection()!="left"){
            arena.getSnake().setDirection("right");
        }
    }

    @Override
    public void executeMenu(MenuModel menuModel) {
        menuModel.changeOption("right");
    }
}
