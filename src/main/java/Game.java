import data.ArenaModel;
import gui.ArenaView;
import rules.ArenaController;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        ArenaModel arena = new ArenaModel(60, 30);
        ArenaView gui = new ArenaView(60, 30);

        ArenaController controller = new ArenaController(gui, arena);
        controller.Tarefa1();
        //controller.start();
    }
}
