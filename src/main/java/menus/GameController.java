package menus;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import data.ArenaModel;
import data.MainMenuModel;
import gui.ArenaView;

import java.io.IOException;

public class GameController {
    State state;
    private MainMenuModel menuModel;
    public ArenaModel arenaModel;
    public ArenaView gui;
    public void init() throws IOException {
        arenaModel= new ArenaModel(60, 30,"src/main/java/files/mapMedium.txt");
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(60, 35)).createTerminal();
        TerminalScreen screen = new TerminalScreen(terminal);
        gui = new ArenaView(60, 35,screen);
        state=new MenuState(this,menuModel);
    }
    public void doStep() throws IOException, InterruptedException {
        state.doStep();
    }
    void changeToGameState() throws IOException, InterruptedException {
        state=new GameState(this);
        state.init();
        state.doStep();
    }
}
