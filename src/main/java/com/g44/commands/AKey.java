package com.g44.commands;

import com.g44.model.ArenaModel;

public class AKey implements Command {

    @Override
    public void executeArena(ArenaModel arenaModel) {
        if(arenaModel.getSnake2().getDirection()!="right"){
            arenaModel.getSnake2().setDirection("left");
        }
    }

}
