package commands;

import model.ArenaModel;
import model.MenuModel;

public class ArrowLeft implements CommandArena,CommandMenu{
    @Override
    public void executeArena(ArenaModel arena) {
        if(arena.getSnake().getDirection()!="right"){
            arena.getSnake().setDirection("left");
        }
    }

    @Override
    public void executeMenu(MenuModel menuModel) {
        menuModel.changeOption("left");
    }
}
