package GameStates;

import controller.GameController;
import view.ArenaView;

import java.awt.*;
import java.io.IOException;

public class InstructionsState extends State{
    public InstructionsState(GameController g) {
        super(g);
    }

    @Override
    public void init() throws IOException, InterruptedException, FontFormatException {
        super.gameController.menuViews.drawInstructions();
        while(true){
            ArenaView.COMMAND command = null;
            command=super.gameController.arenaView.getCommand();
            if(command == ArenaView.COMMAND.ENTER){
                this.doStep();
                break;
            }
        }
    }

    @Override
    public void doStep() throws IOException, InterruptedException, FontFormatException {
        super.gameController.changeToMenuState();
    }
}
