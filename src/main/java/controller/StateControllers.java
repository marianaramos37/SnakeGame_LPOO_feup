package controller;

import view.View;

import java.awt.*;
import java.io.IOException;

public abstract class StateControllers {
    View view;
    StateControllers(View v){
        this.view=v;
    }
    public abstract StateControllers run() throws IOException, FontFormatException, InterruptedException;
}
