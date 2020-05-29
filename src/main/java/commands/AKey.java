package commands;

import model.ArenaModel;

public class AKey implements CommandArena {

    @Override
    public void executeArena(ArenaModel arenaModel) {
        if(arenaModel.getSnake2().getDirection()!="right"){
            arenaModel.getSnake2().setDirection("left");
        }
    }

}
