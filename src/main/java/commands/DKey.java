package commands;

import model.ArenaModel;

public class DKey extends Command implements CommandArena {
    @Override
    public void executeArena(ArenaModel arena) {
        if(arena.getSnake2().getDirection()!="left"){
            arena.getSnake2().setDirection("right");
        }
    }

}
