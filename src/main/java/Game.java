import controller.GameController;

import java.awt.*;
import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException, InterruptedException, FontFormatException {
        GameController controller = new GameController();
        controller.init();
    }
}
