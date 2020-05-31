package com.g44.commands;

import com.g44.model.ArenaModel;
import com.g44.model.MenuModel;

public interface Command {
    default void executeArena(ArenaModel arena) {

    }

    default void executeMenu(MenuModel menuModel){

    }
}
