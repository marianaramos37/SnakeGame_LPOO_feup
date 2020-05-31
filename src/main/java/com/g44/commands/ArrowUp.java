package com.g44.commands;

import com.g44.model.ArenaModel;

public class ArrowUp implements Command {
    @Override
    public void executeArena(ArenaModel arena) {
        if(arena.getSnake().getDirection()!="down"){
            arena.getSnake().setDirection("up");
        }
    }
}
