package GameStates;

import controller.GameController;

import java.awt.*;
import java.io.IOException;

public abstract class State {
    public GameController gameController;

    State(GameController g){
        this.gameController=g;
    }

    public abstract void init() throws IOException, InterruptedException, FontFormatException;
    public abstract void doStep() throws IOException, InterruptedException, FontFormatException;
}
