package commands;

import model.ArenaModel;

public class AKey extends Command implements CommandArena {

    @Override
    public void executeArena(ArenaModel arenaModel) {
        if(arenaModel.getSnake2().getDirection()!="right"){
            arenaModel.getSnake2().setDirection("left");
        }
    }

}
