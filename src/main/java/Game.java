import data.ArenaModel;
import gui.ArenaView;
import rules.ArenaController;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        //ArenaModel arena = new ArenaModel(60, 30);
        //ArenaModel arena = new ArenaModel(60, 30,"C:\\Users\\shit\\OneDrive\\Documentos\\LPOO\\proj\\src\\main\\java\\files\\mapMedium.txt");
        ArenaModel arena = new ArenaModel(60, 30,"C:\\Users\\shit\\OneDrive\\Documentos\\LPOO\\proj\\src\\main\\java\\files\\mapHard.txt");
        ArenaView gui = new ArenaView(60, 35);

        ArenaController controller = new ArenaController(gui, arena);
        //controller.start();
        controller.mov(controller);
    }
}
