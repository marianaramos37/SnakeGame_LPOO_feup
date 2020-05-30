package controller;

import commands.Command;
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

            Command command =view.getCommand();

            command.executeMenu(menuModel);

            view.drawInstructions();

            if(menuModel.isSelected()){
                StateControllers next=new MainMenuController(view);
                return next.run();
            }
        }
    }
}
