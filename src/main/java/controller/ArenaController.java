package controller;

import view.ArenaView;
import model.ArenaModel;

import java.io.IOException;

public class ArenaController {
    private ArenaView gui;
    private ArenaModel arena;

    public ArenaController(ArenaView gui, ArenaModel arena) {
        this.gui = gui;
        this.arena = arena;
    }

    public ArenaView getArenaView(){
        return gui;
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

}
