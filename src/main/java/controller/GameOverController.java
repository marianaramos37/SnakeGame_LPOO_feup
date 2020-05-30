package controller;


import commands.Command;
import model.GameOverModel;
import view.View;

import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class GameOverController extends StateControllers {
    private GameOverModel gameOverModel;
    private View view;

    GameOverController(View v) {
        super(v);
        view=v;
        gameOverModel=new GameOverModel();
    }

    @Override
    public StateControllers run() throws IOException, FontFormatException, InterruptedException, IOException, FontFormatException {
        while(true){

            sleep(200);

            Command command =view.getCommand();

            command.executeMenu(gameOverModel);

            view.drawGameOver();

            view.drawSelecting(gameOverModel);

            if(gameOverModel.isSelected()){
                if(gameOverModel.getOption()==1){
                    return null;
                }
                else if(gameOverModel.getOption()==2){
                    StateControllers next=new MainMenuController(view);
                    return next.run();
                }
            }
        }
    }
}
