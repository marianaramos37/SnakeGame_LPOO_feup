package com.g44.commands;

import com.g44.model.ArenaModel;
import com.g44.model.MenuModel;

public class NullCommand implements Command{

    @Override
    public void executeMenu(MenuModel menuModel) {}

    @Override
    public void executeArena(ArenaModel arenaModel) {}
}
