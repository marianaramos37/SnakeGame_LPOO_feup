package GameStates;

import controller.*;
import model.ArenaModel;

import java.awt.*;
import java.io.IOException;

public class MultyPlayerState extends State{
    public ArenaController arenaController;
    public ArenaModel arenaModel;

    public MultyPlayerState(GameController g) throws IOException {
        super(g);
        arenaModel=new ArenaModel(60, 30);
        arenaController=new ArenaController(g.arenaView,g.menuViews,arenaModel);
    }

    @Override
    public void init() throws IOException, InterruptedException, FontFormatException {
        GameThread2 mov2= new GameThread2(arenaController);
        mov2.start();
        mov2.join();
        this.doStep();
    }

    @Override
    public void doStep() throws IOException, InterruptedException, FontFormatException {
        super.gameController.changeToGameOverState();
    }
}
