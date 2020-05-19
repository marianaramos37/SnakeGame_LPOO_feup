package rules;

import data.*;
import gui.ArenaView;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

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

    public void setArenaView(ArenaView v){
        this.gui=v;
    }
    public void setArenaModel(ArenaModel m){
        this.arena=m;
    }




    public void movement(ArenaView.COMMAND command,ArenaView.COMMAND prevcommand, ArenaModel arena, SnakeController snake) throws IOException {
        if (command == ArenaView.COMMAND.UP) {
            arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().up());
            snake.walkSnake(arena.getSnakeHeadPosition(),'|',arena.getSnake());
        }
        if (command == ArenaView.COMMAND.RIGHT) {
            arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().right());
            snake.walkSnake(arena.getSnakeHeadPosition(), '-',arena.getSnake());
        }
        if (command == ArenaView.COMMAND.DOWN) {
            arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().down());
            snake.walkSnake(arena.getSnakeHeadPosition(), '|',arena.getSnake());
        }
        if (command == ArenaView.COMMAND.LEFT) {
            arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().left());
            snake.walkSnake(arena.getSnakeHeadPosition(), '-',arena.getSnake());
        }
        if(command==ArenaView.COMMAND.ESC){
            arena.endGame();
        }
        if(command==null){
            if (prevcommand == ArenaView.COMMAND.UP) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().up());
                snake.walkSnake(arena.getSnakeHeadPosition(),'|',arena.getSnake());
            }
            if (prevcommand == ArenaView.COMMAND.RIGHT) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().right());
                snake.walkSnake(arena.getSnakeHeadPosition(), '-',arena.getSnake());
            }
            if (prevcommand == ArenaView.COMMAND.DOWN) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().down());
                snake.walkSnake(arena.getSnakeHeadPosition(), '|',arena.getSnake());
            }
            if (prevcommand == ArenaView.COMMAND.LEFT) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().left());
                snake.walkSnake(arena.getSnakeHeadPosition(), '-',arena.getSnake());
            }
            if(prevcommand==ArenaView.COMMAND.ESC){
                arena.endGame();
            }
        }
    }

}
