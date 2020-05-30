package commands;

import model.ArenaModel;
import model.MenuModel;

public class ArrowRight  extends Command implements CommandArena, CommandMenu {
    @Override
    public void executeArena(ArenaModel arena) {
        if(arena.getSnake().getDirection()!="left"){
            arena.getSnake().setDirection("right");
        }
    }

    @Override
    public void executeMenu(MenuModel menuModel) {
        menuModel.changeOption("right");
    }
}
