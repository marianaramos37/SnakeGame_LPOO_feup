package view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.MainMenuModel;

import java.awt.*;
import java.io.IOException;

public class MenuViews extends View{

    public MenuViews(int width, int height, Screen screen) throws IOException {
        super(width, height, screen);
    }

    public void drawMenu(MainMenuModel menuModel) throws IOException, FontFormatException {
        try {
            screen.clear();
            TextGraphics graphics = screen.newTextGraphics();
            graphics.setBackgroundColor(TextColor.Factory.fromString("#66012E"));
            graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(60, 35), ' ');
            graphics.putString(27,10,menuModel.getTitle(),SGR.BOLD);
            graphics.putString(28,30,menuModel.getEsqTitle(),SGR.ITALIC);
            graphics.putString(28,32,"Press I to see the instructions",SGR.ITALIC);
            graphics.putString(12,19,menuModel.getSingleTitle().substring(0,4));
            graphics.putString(7,21,menuModel.getSingleTitle().substring(4,17));
            graphics.putString(42,19,menuModel.getMultyTitle().substring(0,4));
            graphics.putString(37,21,menuModel.getMultyTitle().substring(4,16));
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawMenuLevels(){
        try {
            screen.clear();
            TextGraphics graphics = screen.newTextGraphics();
            graphics.setBackgroundColor(TextColor.Factory.fromString("#2d0115"));
            graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(60, 35), ' ');
            graphics.putString(10,20,"EASY");
            graphics.putString(10,21,"LEVEL");
            graphics.putString(25,20,"MEDIUM");
            graphics.putString(25,21,"LEVEL");
            graphics.putString(40,20,"HARD");
            graphics.putString(40,21,"LEVEL");

            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawSelecting(int x, int y) {
        try {
            TextGraphics graphics = screen.newTextGraphics();
            graphics.setBackgroundColor(TextColor.Factory.fromString("#66012E"));
            graphics.putString(x,y,"o",SGR.BOLD);
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawGameOver() {
        try {
            screen.clear();
            TextGraphics graphics = screen.newTextGraphics();
            graphics.setBackgroundColor(TextColor.Factory.fromString("#790000"));
            graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(60, 35), ' ');
            graphics.putString(25,10,"GAME OVER",SGR.BLINK,SGR.BOLD);
            graphics.putString(10,20,"EXIT");
            graphics.putString(40,20,"REPLAY");
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawResults(int a, int b) throws IOException {
        String aa=String.valueOf(a);
        String bb=String.valueOf(b);
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#450b24"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(60, 35), ' ');
        graphics.putString(15,20, aa);
        graphics.putString(40,20, bb);
        graphics.putString(12,18,"SNAKE 1");
        graphics.putString(37,18,"SNAKE 2");
        screen.refresh();
    }

    public void drawInstructions() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#360118"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(60, 35), ' ');
        graphics.putString(1,2,"Single Player",SGR.BOLD);
        graphics.putString(2,4,"Use the keys UP/RIGHT/LEFT/DOWN to move the snake around");
        graphics.putString(2,6,"the arena, but be careful, never touch the wallsss!");
        graphics.putString(2,8,"You can eat the apples to gain points and grow your body: ");
        graphics.putString(4,10,"-> o are the regular apples ");
        graphics.putString(4,12,"-> S are the SSSSSpecial ones, your body shrinkssss ");
        graphics.putString(4,14,"and you get fasssster");
        graphics.putString(4,16,"-> ~ are the rotten ones, you lose points but they can ");
        graphics.putString(4,18,"be really useful once you want to sssslow down!");
        graphics.putString(2,20,"Final tip: the walls move");

        graphics.putString(1,22,"Multi Player",SGR.BOLD);
        graphics.putString(2,24,"Grab a friend and come fight them in a multiple round ");
        graphics.putString(2,26,"snake game, they can use the keys W/A/D/S to play. Try to");
        graphics.putString(2,28,"kill their snake by putting your body in front of them!");
        graphics.putString(2,30,"Win 1 point per round! First to get to 3 wins the game!");
        graphics.putString(12,32,"GOOD LUCK, Press ENTER to go back",SGR.BOLD);
        screen.refresh();
    }

}
