package com.g44.commands;

import com.g44.model.ArenaModel;

public class WKey implements Command{
    @Override
    public void executeArena(ArenaModel arena) {
        if(arena.getSnake2().getDirection()!="down"){
            arena.getSnake2().setDirection("up");
        }
    }

}
