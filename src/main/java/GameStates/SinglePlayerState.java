package GameStates;

import model.ArenaModel;
import controller.ArenaController;
import controller.GameController;
import controller.GameThread;

import java.awt.*;
import java.io.IOException;

public class SinglePlayerState extends State{
    public ArenaController arenaController;
    public ArenaModel arenaModel;

    public SinglePlayerState(GameController g, int level) throws IOException {
        super(g);
        if(level==1){
            arenaModel=new ArenaModel(60, 30);
        }
        else if(level==2){
            arenaModel= new ArenaModel(60, 30,"src/main/java/files/mapMedium.txt");
        }
        else if(level==3){
            arenaModel= new ArenaModel(60, 30,"src/main/java/files/mapHard.txt");
        }
        arenaController=new ArenaController(g.arenaView,g.menuViews,arenaModel);
    }

    @Override
    public void init() throws IOException, InterruptedException, FontFormatException {
        GameThread mov=new GameThread(arenaController);
        mov.start();
        mov.join();
        this.doStep();
    }

    @Override
    public void doStep() throws IOException, InterruptedException, FontFormatException {
        super.gameController.changeToGameOverState();
    }
}