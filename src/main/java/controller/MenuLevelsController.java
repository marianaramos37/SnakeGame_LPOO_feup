package controller;

import commands.Command;
import model.MenuLevelsModel;
import model.MenuModel;
import view.View;

import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class MenuLevelsController extends StateControllers{
    private MenuModel menuModel;
    private View view;

    MenuLevelsController(View v) {
        super(v);
        view=v;
        menuModel=new MenuLevelsModel();
    }

    @Override
    public StateControllers run() throws IOException, FontFormatException, InterruptedException {
        while(true){

            sleep(200);

            Command command =view.getCommand();
            command.executeMenu(menuModel);

            view.drawMenuLevels();
            view.drawSelecting(menuModel);

            if(menuModel.isSelected()){
                int level = menuModel.getOption();
                StateControllers next=new SinglePlayerController(view,level);
                return next.run();
            }
        }
    }

}
