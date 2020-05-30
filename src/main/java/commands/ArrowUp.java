package commands;

import model.ArenaModel;

public class ArrowUp extends Command implements CommandArena {
    @Override
    public void executeArena(ArenaModel arena) {
        if(arena.getSnake().getDirection()!="down"){
            arena.getSnake().setDirection("up");
        }
    }
}
