package controller;

import commands.Command;
import model.MainMenuModel;
import view.View;

import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class MainMenuController extends StateControllers{
    private MainMenuModel menuModel;
    private View view;

    MainMenuController(View v) {
        super(v);
        view=v;
        menuModel=new MainMenuModel();
    }

    @Override
    public StateControllers run() throws IOException, FontFormatException, InterruptedException {
        while(true){

            sleep(200);

            Command command =view.getCommand();

            command.executeMenu(menuModel);

            view.drawMenu(menuModel);
            view.drawSelecting(menuModel);

            if(menuModel.getOption()==3){
                StateControllers next = new IntructionsController(view);
                return next.run();
            }
            else if(menuModel.getOption()==4){
                return null;
            }
            if(menuModel.isSelected()){
                if(menuModel.getOption()==1){
                    StateControllers next=new MenuLevelsController(view);
                    return next.run();
                }
                if(menuModel.getOption()==2){
                    StateControllers next=new MultiPlayerController(view);
                    return next.run();
                }
            }
        }
    }

}
