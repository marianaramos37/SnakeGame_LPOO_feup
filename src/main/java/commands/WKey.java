package commands;

import model.ArenaModel;

public class WKey implements CommandArena {
    @Override
    public void executeArena(ArenaModel arena) {
        if(arena.getSnake2().getDirection()!="down"){
            arena.getSnake2().setDirection("up");
        }
    }

}
