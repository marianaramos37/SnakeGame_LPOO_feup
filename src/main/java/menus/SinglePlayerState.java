package menus;

import data.ArenaModel;
import rules.ArenaController;
import rules.GameThread;

import java.io.IOException;

public class SinglePlayerState extends State{
    public ArenaController arenaController;
    public ArenaModel arenaModel;

    SinglePlayerState(GameController g,int level) throws IOException {
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
        arenaController=new ArenaController(g.gui,arenaModel);
    }

    @Override
    public void init() {

    }

    @Override
    public void doStep() throws IOException, InterruptedException {
        GameThread mov=new GameThread(arenaController);
        mov.run();
        mov.join();
        super.gameController.changeToGameOverState();
    }
}