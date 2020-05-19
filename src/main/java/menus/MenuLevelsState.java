package menus;

import gui.ArenaView;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class MenuLevelsState extends State{
    MenuLevelsState(GameController g) {
        super(g);
    }

    @Override
    public void init() throws IOException {
        //arenaController=new ArenaController(super.gameController.gui,super.gameController.arenaModel);
    }

    @Override
    public void doStep() throws IOException, InterruptedException {
        ArenaView.COMMAND command = null;
        int x=11, y=23;
        int level;
        while(true){
            sleep(150);
            super.gameController.gui.drawMenuLevels();
            command=super.gameController.gui.getCommand();
            super.gameController.gui.drawSelecting(x,y);
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
                    level=1;
                }
                else if(x==28){
                    level=2;
                }
                else{
                    level=3;
                }
                super.gameController.changeToSPGameState(level);
                break;
            }
            if(command == ArenaView.COMMAND.ESC){
                super.gameController.gui.screen.stopScreen();
                break;
            }
        }

    }
}
