package commands;

import model.MenuModel;

public class EscKey implements CommandMenu {

    @Override
    public void executeMenu(MenuModel menuModel) {
        menuModel.setOption(4);
    }
}
