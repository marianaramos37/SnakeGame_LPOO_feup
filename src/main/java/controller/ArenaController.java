package controller;

import model.Snake;
import view.ArenaView;
import model.ArenaModel;
import view.View;

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
        Snake snake = arena.getSnake();
        if (command == ArenaView.COMMAND.UP) {
            snake.setPosition(arena.getSnake().getPosition().up());
            arena.setSnake(snake);
            arena.walkSnake(arena.getSnake().getPosition(),'|',arena.getSnake());
            return;
        }
        else if (command == ArenaView.COMMAND.RIGHT) {
            snake.setPosition(arena.getSnake().getPosition().right());
            arena.setSnake(snake);
            arena.walkSnake(arena.getSnake().getPosition(),'-',arena.getSnake());
            return;
        }
        else if (command == ArenaView.COMMAND.DOWN) {
            snake.setPosition(arena.getSnake().getPosition().down());
            arena.setSnake(snake);
            arena.walkSnake(arena.getSnake().getPosition(),'|',arena.getSnake());
            return;
        }
        else if (command == ArenaView.COMMAND.LEFT) {
            snake.setPosition(arena.getSnake().getPosition().left());
            arena.setSnake(snake);
            arena.walkSnake(arena.getSnake().getPosition(),'-',arena.getSnake());
            return;
        }
        else if(command==ArenaView.COMMAND.ESC){
            arena.endGame();
            return;
        }
        else{
            if (prevcommand == ArenaView.COMMAND.UP) {
                snake.setPosition(arena.getSnake().getPosition().up());
                arena.setSnake(snake);
                arena.walkSnake(arena.getSnake().getPosition(),'|',arena.getSnake());
                return;
            }
            if (prevcommand == ArenaView.COMMAND.RIGHT) {
                snake.setPosition(arena.getSnake().getPosition().right());
                arena.setSnake(snake);
                arena.walkSnake(arena.getSnake().getPosition(),'-',arena.getSnake());
                return;
            }
            if (prevcommand == ArenaView.COMMAND.DOWN) {
                snake.setPosition(arena.getSnake().getPosition().down());
                arena.setSnake(snake);
                arena.walkSnake(arena.getSnake().getPosition(),'|',arena.getSnake());
                return;
            }
            if (prevcommand == ArenaView.COMMAND.LEFT) {
                snake.setPosition(arena.getSnake().getPosition().left());
                arena.setSnake(snake);
                arena.walkSnake(arena.getSnake().getPosition(),'-',arena.getSnake());
                return;
            }
            if(prevcommand==ArenaView.COMMAND.ESC){
                arena.endGame();
                return;
            }
        }
    }

    public void movement2(ArenaView.COMMAND command,ArenaView.COMMAND prevcommand, ArenaModel arena) throws IOException {
        Snake snake2 = arena.getSnake2();
        if (command == ArenaView.COMMAND.UP2) {
            snake2.setPosition(arena.getSnake2().getPosition().up());
            arena.setSnake2(snake2);
            arena.walkSnake(arena.getSnake2().getPosition(),'|',arena.getSnake2());
            return;
        }
        if (command == ArenaView.COMMAND.RIGHT2) {
            snake2.setPosition(arena.getSnake2().getPosition().right());
            arena.setSnake2(snake2);
            arena.walkSnake(arena.getSnake2().getPosition(),'-',arena.getSnake2());
            return;
        }
        if (command == ArenaView.COMMAND.DOWN2) {
            snake2.setPosition(arena.getSnake2().getPosition().down());
            arena.setSnake2(snake2);
            arena.walkSnake(arena.getSnake2().getPosition(),'|',arena.getSnake2());
            return;
        }
        if (command == ArenaView.COMMAND.LEFT2) {
            snake2.setPosition(arena.getSnake2().getPosition().left());
            arena.setSnake2(snake2);
            arena.walkSnake(arena.getSnake2().getPosition(),'-',arena.getSnake2());
            return;
        }
        else{
            if (prevcommand == ArenaView.COMMAND.UP2) {
                snake2.setPosition(arena.getSnake2().getPosition().up());
                arena.setSnake2(snake2);
                arena.walkSnake(arena.getSnake2().getPosition(),'|',arena.getSnake2());
                return;
            }
            if (prevcommand == ArenaView.COMMAND.RIGHT2) {
                snake2.setPosition(arena.getSnake2().getPosition().right());
                arena.setSnake2(snake2);
                arena.walkSnake(arena.getSnake2().getPosition(),'-',arena.getSnake2());
                return;
            }
            if (prevcommand == ArenaView.COMMAND.DOWN2) {
                snake2.setPosition(arena.getSnake2().getPosition().down());
                arena.setSnake2(snake2);
                arena.walkSnake(arena.getSnake2().getPosition(),'|',arena.getSnake2());
                return;
            }
            if (prevcommand == ArenaView.COMMAND.LEFT2) {
                snake2.setPosition(arena.getSnake2().getPosition().left());
                arena.setSnake2(snake2);
                arena.walkSnake(arena.getSnake2().getPosition(),'-',arena.getSnake2());
                return;
            }
        }
    }



}
