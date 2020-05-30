package commands;

import model.ArenaModel;
import model.MenuModel;

public abstract class Command {
    CommandArena commandArena;
    CommandMenu commandMenu;

    public void executeArena(ArenaModel arena) {
        commandArena.executeArena(arena);
    }

    public void executeMenu(MenuModel menuModel) {
        commandMenu.executeMenu(menuModel);
    }
}
