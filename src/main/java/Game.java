import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import data.ArenaModel;
import gui.ArenaView;
import rules.ArenaController;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {

        ArenaModel arena = new ArenaModel(60, 30,"C:\\Users\\Mariana Ramos\\Desktop\\2ano\\2semestre\\lpoo\\proj\\src\\main\\java\\files\\mapHard.txt");

        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(60, 35)).createTerminal();
        TerminalScreen screen = new TerminalScreen(terminal);
        ArenaView gui = new ArenaView(60, 35,screen);

        ArenaController controller = new ArenaController(gui, arena);
        //controller.start();
        controller.mov(controller);
    }
}
