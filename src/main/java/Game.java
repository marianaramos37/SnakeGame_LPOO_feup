import menus.GameController;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException, InterruptedException {

        /*ArenaModel arena = new ArenaModel(60, 30,"src/main/java/files/mapMedium.txt");

        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(60, 35)).createTerminal();
        TerminalScreen screen = new TerminalScreen(terminal);
        ArenaView gui = new ArenaView(60, 35,screen);

        ArenaController controller = new ArenaController(gui, arena);
        controller.starting(controller);*/

        GameController controller = new GameController();
        controller.init();
       // controller.arenaModel.buildWalls();
       // controller.gui.drawArena(controller.arenaModel);
        controller.doStep();
    }
}
