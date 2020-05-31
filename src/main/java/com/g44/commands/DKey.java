package com.g44.commands;

import com.g44.model.ArenaModel;

public class DKey implements Command{
    @Override
    public void executeArena(ArenaModel arena) {
        if(arena.getSnake2().getDirection()!="left"){
            arena.getSnake2().setDirection("right");
        }
    }

}
