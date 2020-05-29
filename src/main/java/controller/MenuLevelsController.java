package controller;

import commands.CommandMenu;
import model.MenuLevelsModel;
import view.View;

import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class MenuLevelsController extends StateControllers{
    private MenuLevelsModel menuModel;
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

            CommandMenu command =view.getCommandMenu();

            command.executeMenu(menuModel);

            view.drawMenuLevels();

            view.drawSelecting(menuModel);

            if(menuModel.isSelected()){
                if(menuModel.getOption()==1){
                    StateControllers next=new SinglePlayerController(view,1);
                    return next.run();
                }
                if(menuModel.getOption()==2){
                    StateControllers next=new SinglePlayerController(view,2);
                    return next.run();
                }
                if(menuModel.getOption()==3){
                    StateControllers next=new SinglePlayerController(view,3);
                    return next.run();
                }
                if(menuModel.getOption()==4){
                    StateControllers next=new SinglePlayerController(view,4);
                    return next.run();
                }

            }
        }
    }

}
