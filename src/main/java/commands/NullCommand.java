package commands;

import model.ArenaModel;
import model.MenuModel;

public class NullCommand extends Command implements CommandMenu, CommandArena {

    @Override
    public void executeMenu(MenuModel menuModel) {}

    @Override
    public void executeArena(ArenaModel arenaModel) {}
}
