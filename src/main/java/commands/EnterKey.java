package commands;

import model.MenuModel;

public class EnterKey implements CommandMenu{
    @Override
    public void executeMenu(MenuModel menuModel) {
        menuModel.setSelected(true);
    }

}
