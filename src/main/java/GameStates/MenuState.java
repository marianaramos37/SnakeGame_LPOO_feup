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
        int x=11, y=23; //single , x=45 -> buildMaps
        while(true){
            sleep(150);
            super.gameController.menuViews.drawMenu(menuModel);
            command=super.gameController.arenaView.getCommand();
            super.gameController.menuViews.drawSelecting(x,y);
            if(command == ArenaView.COMMAND.LEFT){
                super.gameController.arenaView.screen.clear();
                if(x!=11){
                    x-=17;
                }
            }
            if(command == ArenaView.COMMAND.RIGHT){
                if(x!=45){
                    x+=17;
                }
            }
            if(command == ArenaView.COMMAND.ENTER){
                if(x==11){
                    this.doStep();
                    break;
                }
                if(x==28){
                    this.doStep2();
                }
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
}
