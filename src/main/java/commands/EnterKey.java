package commands;

import model.MenuModel;

public class EnterKey extends Command implements CommandMenu{
    @Override
    public void executeMenu(MenuModel menuModel) {
        menuModel.setSelected(true);
    }

}
