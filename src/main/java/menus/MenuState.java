package menus;

import data.MainMenuModel;
import gui.ArenaView;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class MenuState extends State{
    MainMenuModel menuModel;
    MenuState(GameController g, MainMenuModel m) {
        super(g);
        menuModel=new MainMenuModel();
    }

    @Override
    public void init() throws IOException {
        //arenaController=new ArenaController(super.gameController.gui,super.gameController.arenaModel);
    }

    @Override
    public void doStep() throws IOException, InterruptedException {
        ArenaView.COMMAND command = null;
        int x=11, y=23; //single , x=45 -> buildMaps
        while(true){
            sleep(150);
            super.gameController.gui.drawMenu(menuModel);
            command=super.gameController.gui.getCommand();
            super.gameController.gui.drawSelecting(menuModel,x,y);
            if(command == ArenaView.COMMAND.LEFT){
                super.gameController.gui.screen.clear();
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
                    super.gameController.changeToGameState();
                    break;
                }
            }
            if(command == ArenaView.COMMAND.ESC){
                super.gameController.gui.screen.stopScreen();
                break;
            }
        }

    }

}
