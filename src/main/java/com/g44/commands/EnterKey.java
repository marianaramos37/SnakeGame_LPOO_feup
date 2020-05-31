package com.g44.commands;

import com.g44.model.MenuModel;

public class EnterKey implements Command{
    @Override
    public void executeMenu(MenuModel menuModel) {
        menuModel.setSelected(true);
    }

}
