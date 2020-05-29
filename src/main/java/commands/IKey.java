package commands;

import model.MenuModel;

public class IKey implements CommandMenu {

    @Override
    public void executeMenu(MenuModel menuModel) {
        menuModel.setOption(3);
    }
}
