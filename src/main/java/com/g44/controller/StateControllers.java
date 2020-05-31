package com.g44.controller;

import com.g44.view.View;

import java.awt.*;
import java.io.IOException;

public abstract class StateControllers {
    View view;
    StateControllers(View v){
        this.view=v;
    }
    public abstract StateControllers run() throws IOException, FontFormatException, InterruptedException;
}
