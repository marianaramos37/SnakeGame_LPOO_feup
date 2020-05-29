package controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import view.View;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameController {
    Screen screen;
    StateControllers state;

    private View view;

    DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(60, 35));

    public View getView(){
        return view;
    }

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

        view=new View(60,35,screen);

        state=new MainMenuController(view);

        while(state!=null){
            state=state.run();
        }

        screen.close();
    }

}
