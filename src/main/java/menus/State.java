package menus;

import java.io.IOException;

public abstract class State {
    public GameController gameController;

    State(GameController g){
        this.gameController=g;
    }
    public abstract void init() throws IOException;
    public abstract void doStep() throws IOException, InterruptedException;
}
