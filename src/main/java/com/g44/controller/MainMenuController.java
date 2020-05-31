package com.g44.controller;

import com.g44.commands.Command;
import com.g44.model.MainMenuModel;
import com.g44.view.View;

import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class MainMenuController extends StateControllers{
    private MainMenuModel menuModel;
    private View view;

    public MainMenuController(View v) {
        super(v);
        view=v;
        menuModel=new MainMenuModel();
    }

    public MainMenuModel getMenuModel(){return menuModel;}
    public void setMenuModel(MainMenuModel menuModel){this.menuModel= menuModel;}


    @Override
    public StateControllers run() throws IOException, FontFormatException, InterruptedException {
        while(true){

            sleep(200);

            Command command =view.getCommand();
            command.executeMenu(menuModel);

            view.drawMenu();
            view.drawSelecting(menuModel);

            if(menuModel.getOption()==3){
                StateControllers next = new IntructionsController(view);
                return next;
            }
            else if(menuModel.getOption()==4){
                return null;
            }
            if(menuModel.isSelected()){
                if(menuModel.getOption()==1){
                    StateControllers next=new MenuLevelsController(view);
                    return next;
                }
                if(menuModel.getOption()==2){
                    StateControllers next=new MultiPlayerController(view);
                    return next;
                }
            }
        }
    }

}
