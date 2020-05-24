package controller;

import GameStates.*;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import model.MainMenuModel;
import view.ArenaView;
import view.MenuViews;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameController {
    Screen screen;
    State state;
    private MainMenuModel menuModel;
    public ArenaView arenaView;
    public MenuViews menuViews;
    DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(60, 35));

    public void init() throws IOException, InterruptedException, FontFormatException {
        File fontFile = new File("src/main/java/files/square.ttf");
        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 10);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);

        defaultTerminalFactory.setForceAWTOverSwing(true);
        defaultTerminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = defaultTerminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        arenaView=new ArenaView(60,35,screen);
        menuViews=new MenuViews(60,35,screen);
        state=new MenuState(this,menuModel);
        state.init();
    }

    public void doStep() throws IOException, InterruptedException, FontFormatException {
        state.doStep();
    }

    public void changeToMenuState() throws IOException, InterruptedException, FontFormatException {
        state=new MenuState(this,menuModel);
        state.init();
    }

    public void changeToChooseLevelState() throws IOException, InterruptedException, FontFormatException {
        state=new MenuLevelsState(this);
        state.init();
    }

    public void changeToSPGameState(int level) throws IOException, InterruptedException, FontFormatException {
        state=new SinglePlayerState(this,level);
        state.init();
    }

    public void changeToGameOverState() throws IOException, InterruptedException, FontFormatException {
        state=new GameOverState(this);
        state.init();
    }

    public void changeToMultyPlayerState() throws IOException, InterruptedException, FontFormatException {
        state=new MultyPlayerState(this);
        state.init();
    }
    public void changeToInstructionsState() throws IOException, InterruptedException, FontFormatException {
        state=new InstructionsState(this);
        state.init();
    }
}
