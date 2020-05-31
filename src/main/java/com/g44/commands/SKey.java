package com.g44.commands;

import com.g44.model.ArenaModel;

public class SKey implements Command{
    @Override
    public void executeArena(ArenaModel arena) {
        if(arena.getSnake2().getDirection()!="up"){
            arena.getSnake2().setDirection("down");
        }
    }

}
