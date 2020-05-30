package view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import commands.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class View {
    public Screen screen;

    public View(int width, int height, Screen screen) throws IOException {
        this.screen=screen;
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    public void drawMenu() {
        try {
            screen.clear();
            TextGraphics graphics = screen.newTextGraphics();
            graphics.setBackgroundColor(TextColor.Factory.fromString("#66012E"));
            graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(60, 35), ' ');
            graphics.putString(27,10,"SNAKE", SGR.BOLD);
            graphics.putString(28,30,"Press ESC to leave",SGR.ITALIC);
            graphics.putString(28,32,"Press I to see the instructions",SGR.ITALIC);
            graphics.putString(12,19,"PLAY");
            graphics.putString(8,21,"SINGLEPLAYER");
            graphics.putString(42,19,"PLAY");
            graphics.putString(38,21,"MULTIPLAYER");
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
            graphics.putString(5,20,"EASY");
            graphics.putString(5,21,"LEVEL");
            graphics.putString(19,20,"MEDIUM");
            graphics.putString(19,21,"LEVEL");
            graphics.putString(34,20,"HARD");
            graphics.putString(34,21,"LEVEL");
            graphics.putString(46,20,"ULTRA-HARD");
            graphics.putString(49,21,"LEVEL");

            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawSelecting(MenuModel menuModel) {
        try {
            TextGraphics graphics = screen.newTextGraphics();
            graphics.setBackgroundColor(TextColor.Factory.fromString("#66012E"));
            graphics.putString(menuModel.getCursorPosition().getX(),menuModel.getCursorPosition().getY(),"o",SGR.BOLD);
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

    public void drawSnake(Snake snake) {
        int index=0;
        TextGraphics graphics = screen.newTextGraphics();
        if(!snake.getShrink()) {
            for (Character c : snake.getSnakeBody()) {
                if (index >= snake.getPos().size()) {
                    snake.getPos().add(snake.getLength(), new Position(snake.getPos().get(snake.getLength() - 1).getX() + 1, snake.getPos().get(snake.getLength() - 1).getY()));
                }
                if(!snake.isGhost())
                    graphics.setCharacter(snake.getPos().get(index).getX(), snake.getPos().get(index).getY(), new TextCharacter(snake.getSnakeBody().get(index)));
                else graphics.putString(snake.getPos().get(index).getX(), snake.getPos().get(index).getY(), String.valueOf(snake.getSnakeBody().get(index)), SGR.BLINK);
                index++;
            }
        }else{
            for (Character c : snake.getSnakeBody().subList(0,2)) {
                if (index >= snake.getPos().size()) {
                    snake.getPos().add(snake.getLength(), new Position(snake.getPos().get(snake.getLength() - 1).getX() + 1, snake.getPos().get(snake.getLength() - 1).getY()));
                }
                if(!snake.isGhost())
                    graphics.setCharacter(snake.getPos().get(index).getX(), snake.getPos().get(index).getY(), new TextCharacter(snake.getSnakeBody().get(index)));
                else graphics.putString(snake.getPos().get(index).getX(), snake.getPos().get(index).getY(), String.valueOf(snake.getSnakeBody().get(index)), SGR.BLINK);
                index++;
            }
        }
    }

    public void drawElements(List<Element> walls) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#790000"));
        for(Element wall:walls) {
            screen.setCharacter(wall.getPosition().getX(), wall.getPosition().getY(), new TextCharacter('#'));
        }
    }


    public void drawApples(ArenaModel arena) {
        List<AppleInterface> apples=arena.getApples();
        for(AppleInterface apple:apples)
            screen.setCharacter(apple.getPosition().getX(),apple.getPosition().getY(), new TextCharacter(apple.getChar()));
    }

    public void drawScores(ArenaModel arena){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.putString(5,32,"SCORE: ");
        graphics.putString(12,32,String.valueOf(arena.getScore()));
        graphics.putString(40,32,"TOP SCORE: ");
        graphics.putString(51,32,String.valueOf(arena.getTopScore().getScore()));
    }

    public void drawArena(ArenaModel arena, int n) {
        try {
            TextGraphics graphics = screen.newTextGraphics();
            screen.clear();

            if(n==1){
                drawApples(arena);
                drawSnake(arena.getSnake());
                drawScores(arena);
            }
            else{
                drawApples(arena);
                graphics.putString(5,32,"Snake 2");
                graphics.putString(45,32,"Snake 1");
                drawSnake(arena.getSnake());
                drawSnake(arena.getSnake2());
            }

            List<Element> elements = new ArrayList<>();
            elements.addAll(arena.getWalls());

            for(Obstaculo o: arena.getObstaculos()){
                elements.addAll(o.getObstaculo());
            }

            drawElements(elements);

            screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Command getCommand() throws IOException {
        KeyStroke key = screen.pollInput();
        if (key != null) {
            if (key.getKeyType() == KeyType.ArrowUp) {
                return new ArrowUp();
            }
            if (key.getKeyType() == KeyType.ArrowRight) {
                return new ArrowRight();
            }
            if (key.getKeyType() == KeyType.ArrowDown) {
                return new ArrowDown();
            }
            if (key.getKeyType() == KeyType.ArrowLeft) {
                return new ArrowLeft();
            }
            if (key.getCharacter() == 'w') {
                return new WKey();
            }
            if (key.getCharacter() == 'd') {
                return new DKey();
            }
            if (key.getCharacter() == 's') {
                return new SKey();
            }
            if (key.getCharacter() == 'a') {
                return new AKey();
            }
            if (key.getKeyType() == KeyType.Escape) {
                return new EscKey();
            }
            if (key.getKeyType() == KeyType.Enter) {
                return new EnterKey();
            }
            if (key.getCharacter() == 'i') {
                return new IKey();
            }
        }

        return new NullCommand();
    }
}
