package com.g44.commands;

import com.g44.model.ArenaModel;

public class ArrowDown implements Command {
    @Override
    public void executeArena(ArenaModel arena) {
        if (arena.getSnake().getDirection() != "up") {
            arena.getSnake().setDirection("down");
        }
    }
}
