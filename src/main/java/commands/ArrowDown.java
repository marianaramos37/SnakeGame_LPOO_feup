package commands;

import model.ArenaModel;

public class ArrowDown implements CommandArena {
    @Override
    public void executeArena(ArenaModel arena) {
        if (arena.getSnake().getDirection() != "up") {
            arena.getSnake().setDirection("down");
        }
    }
}
