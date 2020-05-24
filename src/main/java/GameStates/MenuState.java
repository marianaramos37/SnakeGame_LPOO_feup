package GameStates;

import model.MainMenuModel;
import view.ArenaView;
import controller.GameController;

import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class MenuState extends State{
    MainMenuModel menuModel;
    public MenuState(GameController g, MainMenuModel m) {
        super(g);
        menuModel=new MainMenuModel();
    }

    @Override
    public void init() throws IOException, InterruptedException, FontFormatException {
        ArenaView.COMMAND command = null;
        int x=13, y=23; //single
        while(true){
            sleep(150);
            super.gameController.menuViews.drawMenu(menuModel);
            command=super.gameController.arenaView.getCommand();
            super.gameController.menuViews.drawSelecting(x,y);
            if(command == ArenaView.COMMAND.LEFT){
                super.gameController.arenaView.screen.clear();
                if(x!=13){
                    x-=30;
                }
            }
            if(command == ArenaView.COMMAND.RIGHT){
                if(x!=43){
                    x+=30;
                }
            }
            if(command == ArenaView.COMMAND.ENTER){
                if(x==13){
                    this.doStep();
                    break;
                }
                if(x==43){
                    this.doStep2();
                }
            }
            if(command == ArenaView.COMMAND.I){
                this.doStep3();
                break;
            }
            if(command == ArenaView.COMMAND.ESC){
                super.gameController.arenaView.screen.stopScreen();
                break;
            }
        }
    }

    @Override
    public void doStep() throws IOException, InterruptedException, FontFormatException {
        super.gameController.changeToChooseLevelState();
    }
    public void doStep2() throws IOException, InterruptedException, FontFormatException {
        super.gameController.changeToMultyPlayerState();
    }
    public void doStep3() throws IOException, InterruptedException, FontFormatException {
        super.gameController.changeToInstructionsState();
    }
}
