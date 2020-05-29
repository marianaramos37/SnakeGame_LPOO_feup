package controller;

import commands.CommandMenu;
import model.MainMenuModel;
import view.View;

import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class IntructionsController extends StateControllers {
    private MainMenuModel menuModel;
    private View view;

    IntructionsController(View v) {
        super(v);
        view =v;
        menuModel=new MainMenuModel();
    }

    @Override
    public StateControllers run() throws IOException, FontFormatException, InterruptedException {
        while(true){

            sleep(200);

            CommandMenu command =view.getCommandMenu();

            command.executeMenu(menuModel);

            view.drawInstructions();

            if(menuModel.isSelected()){
                StateControllers next=new MainMenuController(view);
                return next.run();
            }
        }
    }
}
