package view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
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
            graphics.putString(27,10,menuModel.getTitle());
            graphics.putString(32,32,menuModel.getEsqTitle());
            graphics.putString(10,20,menuModel.getSingleTitle().substring(0,4));
            graphics.putString(5,21,menuModel.getSingleTitle().substring(4,17));
            graphics.putString(27,20,menuModel.getMultyTitle().substring(0,4));
            graphics.putString(22,21,menuModel.getMultyTitle().substring(4,16));
            graphics.putString(40,20,menuModel.getContrucaoTitle().substring(0,5));
            graphics.putString(39,21,menuModel.getContrucaoTitle().substring(5,10));
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawMenuLevels(){
        try {
            screen.clear();
            TextGraphics graphics = screen.newTextGraphics();
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
            TextCharacter c1 = new TextCharacter('o');
            screen.setCharacter(x,y, c1);
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
            graphics.fillRectangle(
                    new TerminalPosition(0, 0),
                    new TerminalSize(60, 30),
                    ' '
            );
            graphics.putString(25,10,"GAME OVER");
            graphics.putString(10,20,"EXIT");
            graphics.putString(40,20,"REPLAY");
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawResults(int a, int b) throws IOException {
        TextCharacter c1,c2;
        switch (a) {
            case (1): c1 = new TextCharacter('1');
            case(2): c1= new TextCharacter('2');
            case(3): c1= new TextCharacter('3');
                break;
            default:
                c1=new TextCharacter('0');
        }
        switch (b) {
            case (1): c2 = new TextCharacter('1');
            case(2): c2= new TextCharacter('2');
            case(3): c2= new TextCharacter('3');
                break;
            default:
                c2= new TextCharacter('0');
        }
        screen.clear();
        screen.setCharacter(10,10, c1);
        screen.setCharacter(20,10, c2);
        screen.refresh();
    }

}
