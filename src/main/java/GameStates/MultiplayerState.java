package GameStates;

import controller.ArenaController;
import controller.GameController;
import controller.GameThread;
import model.ArenaModel;

import java.io.IOException;

public class MultiplayerState extends State {

    public ArenaController arenaController;
    public ArenaModel arenaModel;

    public MultiplayerState(GameController g) throws IOException {
        super(g);

        arenaModel=new ArenaModel(60, 30); //new arena model and controller for multiplayer??

        arenaController=new ArenaController(g.gui,arenaModel);
    }

    @Override
    public void init() throws IOException, InterruptedException {
        GameThread mov=new GameThread(arenaController);
        mov.run();
        mov.join();
        this.doStep();  //uma ronda for now
    }

    @Override
    public void doStep() throws IOException, InterruptedException {
        super.gameController.changeToGameOverState();
    }
}
