package controller;

import model.ArenaModel;
import view.ArenaView;
import view.MenuViews;

import java.io.IOException;

public class ArenaController {
    private ArenaView arenaView;
    private MenuViews menuViews;
    private ArenaModel arena;

    public ArenaController(ArenaView gui,MenuViews m, ArenaModel arena) {
        this.arenaView = gui;
        this.menuViews=m;
        this.arena = arena;
    }

    public ArenaView getArenaView(){
        return arenaView;
    }
    public MenuViews getMenusViews(){
        return menuViews;
    }
    public ArenaModel getArenaModel(){
        return arena;
    }


    public void movement(ArenaView.COMMAND command,ArenaView.COMMAND prevcommand, ArenaModel arena) throws IOException {
        if (command == ArenaView.COMMAND.UP) {
            arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().up());
            arena.walkSnake(arena.getSnakeHeadPosition(),'|',arena.getSnake());
        }
        if (command == ArenaView.COMMAND.RIGHT) {
            arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().right());
            arena.walkSnake(arena.getSnakeHeadPosition(), '-',arena.getSnake());
        }
        if (command == ArenaView.COMMAND.DOWN) {
            arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().down());
            arena.walkSnake(arena.getSnakeHeadPosition(), '|',arena.getSnake());
        }
        if (command == ArenaView.COMMAND.LEFT) {
            arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().left());
            arena.walkSnake(arena.getSnakeHeadPosition(), '-',arena.getSnake());
        }
        if(command==ArenaView.COMMAND.ESC){
            arena.endGame();
        }
        if(command==null){
            if (prevcommand == ArenaView.COMMAND.UP) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().up());
                arena.walkSnake(arena.getSnakeHeadPosition(),'|',arena.getSnake());
            }
            if (prevcommand == ArenaView.COMMAND.RIGHT) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().right());
                arena.walkSnake(arena.getSnakeHeadPosition(), '-',arena.getSnake());
            }
            if (prevcommand == ArenaView.COMMAND.DOWN) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().down());
                arena.walkSnake(arena.getSnakeHeadPosition(), '|',arena.getSnake());
            }
            if (prevcommand == ArenaView.COMMAND.LEFT) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().left());
                arena.walkSnake(arena.getSnakeHeadPosition(), '-',arena.getSnake());
            }
            if(prevcommand==ArenaView.COMMAND.ESC){
                arena.endGame();
            }
        }
    }

    public void movement2(ArenaView.COMMAND command,ArenaView.COMMAND prevcommand, ArenaModel arena) throws IOException {
        if (command == ArenaView.COMMAND.UP2) {
            arena.setSnakeHead2Position(arena.getSnakeHead2Position().up());
            arena.walkSnake(arena.getSnakeHead2Position(),'|',arena.getSnake2());
        }
        if (command == ArenaView.COMMAND.RIGHT2) {
            arena.setSnakeHead2Position(arena.getSnakeHead2Position().right());
            arena.walkSnake(arena.getSnakeHead2Position(),'-',arena.getSnake2());
        }
        if (command == ArenaView.COMMAND.DOWN2) {
            arena.setSnakeHead2Position(arena.getSnakeHead2Position().down());
            arena.walkSnake(arena.getSnakeHead2Position(),'|',arena.getSnake2());
        }
        if (command == ArenaView.COMMAND.LEFT2) {
            arena.setSnakeHead2Position(arena.getSnakeHead2Position().left());
            arena.walkSnake(arena.getSnakeHead2Position(),'-',arena.getSnake2());
        }
        else{
            if (prevcommand == ArenaView.COMMAND.UP2) {
                arena.setSnakeHead2Position(arena.getSnakeHead2Position().up());
                arena.walkSnake(arena.getSnakeHead2Position(),'|',arena.getSnake2());
            }
            if (prevcommand == ArenaView.COMMAND.RIGHT2) {
                arena.setSnakeHead2Position(arena.getSnakeHead2Position().right());
                arena.walkSnake(arena.getSnakeHead2Position(),'-',arena.getSnake2());
            }
            if (prevcommand == ArenaView.COMMAND.DOWN2) {
                arena.setSnakeHead2Position(arena.getSnakeHead2Position().down());
                arena.walkSnake(arena.getSnakeHead2Position(),'|',arena.getSnake2());
            }
            if (prevcommand == ArenaView.COMMAND.LEFT2) {
                arena.setSnakeHead2Position(arena.getSnakeHead2Position().left());
                arena.walkSnake(arena.getSnakeHead2Position(),'-',arena.getSnake2());
            }
        }
    }



}

