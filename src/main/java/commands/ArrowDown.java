package commands;

import model.ArenaModel;

public class ArrowDown extends Command implements CommandArena {
    @Override
    public void executeArena(ArenaModel arena) {
        if (arena.getSnake().getDirection() != "up") {
            arena.getSnake().setDirection("down");
        }
    }
}
