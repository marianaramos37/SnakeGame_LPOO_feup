package com.g44.controller;

import com.g44.commands.Command;
import com.g44.commands.NullCommand;
import com.g44.model.ArenaModel;
import com.g44.model.Element;
import com.g44.model.MenuModel;
import com.g44.model.Snake;
import com.g44.view.View;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewMock extends View {
    private List<Command> commands = new ArrayList<>();

    public ViewMock(List<Command> c) throws IOException, FontFormatException {
        super(0,0, null);
        commands=c;
    }

    @Override
    public Command getCommand(){
        if(commands.size()==0) return new NullCommand();
        Command command=commands.get(0);
        commands.remove(0);
        return command;
    }

    @Override
    public void drawMenu(){ }
    @Override
    public void drawSelecting(MenuModel menuModel){ }
    @Override
    public void drawMenuLevels(){ }
    @Override
    public void drawGameOver() { }
    @Override
    public void drawResults(int a, int b) throws IOException { }
    @Override
    public void drawInstructions() throws IOException { }
    @Override
    public void drawSnake(Snake snake) { }
    @Override
    public void drawElements(List<Element> walls) { }
    @Override
    public void drawApples(ArenaModel arena) { }
    @Override
    public void drawScores(ArenaModel arena){ }
    @Override
    public void drawArena(ArenaModel arena, int n) { }

}
