package GameStates;

import view.ArenaView;
import controller.GameController;

import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class MenuLevelsState extends State{
    public MenuLevelsState(GameController g) {
        super(g);
    }
    private int level;
    @Override
    public void init() throws IOException, InterruptedException, FontFormatException {
        ArenaView.COMMAND command = null;
        int x=11, y=23;
        while(true){
            sleep(150);
            super.gameController.menuViews.drawMenuLevels();
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
                    level=1;
                }
                else if(x==28){
                    level=2;
                }
                else{
                    level=3;
                }
                doStep();
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
        super.gameController.changeToSPGameState(level);

    }
}
