package GameStates;

import view.ArenaView;
import controller.GameController;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class GameOverState  extends State{
    public GameOverState(GameController g) {
        super(g);
    }

    @Override
    public void init() throws IOException, InterruptedException {
        super.gameController.gui.drawGameOver();
        ArenaView.COMMAND command = null;
        int x=11, y=23; //single , x=45 -> buildMaps
        while(true){
            sleep(150);
            super.gameController.gui.drawGameOver();
            command=super.gameController.gui.getCommand();
            super.gameController.gui.drawSelecting(x,y);
            if(command == ArenaView.COMMAND.LEFT){
                super.gameController.gui.screen.clear();
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
                    super.gameController.gui.screen.stopScreen();
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
    public void doStep() throws IOException, InterruptedException {
        super.gameController.changeToMenuState();
    }
}
