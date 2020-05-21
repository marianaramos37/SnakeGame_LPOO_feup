import menus.GameController;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException, InterruptedException {
        GameController controller = new GameController();
        controller.init();
    }
}
