import com.googlecode.lanterna.screen.TerminalScreen;
import data.ArenaModel;
import gui.ArenaView;
import rules.ArenaController;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        ArenaModel arena = new ArenaModel(60, 30);
        TerminalScreen screen = null;
        ArenaView gui = new ArenaView(60, 35,screen);

        ArenaController controller = new ArenaController(gui, arena);
        //controller.start();
        controller.mov(controller);
    }
}
