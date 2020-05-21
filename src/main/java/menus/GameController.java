package menus;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import data.MainMenuModel;
import gui.ArenaView;

import java.io.IOException;

public class GameController {
    State state;
    private MainMenuModel menuModel;
    public ArenaView gui;
    public void init() throws IOException, InterruptedException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(60, 35)).createTerminal();
        TerminalScreen screen = new TerminalScreen(terminal);
        gui = new ArenaView(60, 35,screen);
        state=new MenuState(this,menuModel);
        state.init();
    }
    public void doStep() throws IOException, InterruptedException {
        state.doStep();
    }
    void changeToMenuState() throws IOException, InterruptedException {
        state=new MenuState(this,menuModel);
        state.init();
    }
    void changeToChooseLevelState() throws IOException, InterruptedException {
        state=new MenuLevelsState(this);
        state.init();
    }
    void changeToSPGameState(int level) throws IOException, InterruptedException {
        state=new SinglePlayerState(this,level);
        state.init();
    }
    void changeToGameOverState() throws IOException, InterruptedException {
        state=new GameOverState(this);
        state.init();
    }
}
