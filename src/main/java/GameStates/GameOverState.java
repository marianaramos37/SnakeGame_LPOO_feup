package GameStates;

import view.ArenaView;
import controller.GameController;

import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class GameOverState  extends State{
    public GameOverState(GameController g) {
        super(g);
    }

    @Override
    public void init() throws IOException, InterruptedException, FontFormatException {
        super.gameController.menuViews.drawGameOver();
        ArenaView.COMMAND command = null;
        int x=11, y=23; //single , x=45 -> buildMaps
        while(true){
            sleep(150);
            super.gameController.menuViews.drawGameOver();
            command=super.gameController.arenaView.getCommand();
            super.gameController.menuViews.drawSelecting(x,y);
            if(command == ArenaView.COMMAND.LEFT){
                super.gameController.arenaView.screen.clear();
                if(x!=11){
                    x-=32;
                }
            }
            if(command == ArenaView.COMMAND.RIGHT){
                if(x!=43){
                    x+=32;
                }
            }
            if(command == ArenaView.COMMAND.ENTER){
                if(x==11){
                    super.gameController.arenaView.screen.stopScreen();
                    break;
                }
                else{
                    doStep();
                    break;
                }
            }
        }

    }

    @Override
    public void doStep() throws IOException, InterruptedException, FontFormatException {
        super.gameController.changeToMenuState();
    }
}
