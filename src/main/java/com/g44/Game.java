package com.g44;

import com.g44.controller.GameController;

import java.awt.*;
import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException, InterruptedException, FontFormatException {
        GameController controller = new GameController();
        controller.init();
    }
}
