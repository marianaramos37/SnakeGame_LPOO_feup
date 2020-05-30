package commands;

import model.ArenaModel;

public class SKey extends Command implements CommandArena {
    @Override
    public void executeArena(ArenaModel arena) {
        if(arena.getSnake2().getDirection()!="up"){
            arena.getSnake2().setDirection("down");
        }
    }


}
