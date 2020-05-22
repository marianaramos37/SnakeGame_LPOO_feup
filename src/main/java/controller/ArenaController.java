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
            arena.getSnake().setPosition(arena.getSnake().getPosition().up());
            arena.walkSnake(arena.getSnake().getPosition(),'|',arena.getSnake());
        }
        if (command == ArenaView.COMMAND.RIGHT) {
            arena.getSnake().setPosition(arena.getSnake().getPosition().right());
            arena.walkSnake(arena.getSnake().getPosition(),'-',arena.getSnake());
        }
        if (command == ArenaView.COMMAND.DOWN) {
            arena.getSnake().setPosition(arena.getSnake().getPosition().down());
            arena.walkSnake(arena.getSnake().getPosition(),'|',arena.getSnake());
        }
        if (command == ArenaView.COMMAND.LEFT) {
            arena.getSnake().setPosition(arena.getSnake().getPosition().left());
            arena.walkSnake(arena.getSnake().getPosition(),'-',arena.getSnake());
        }
        if(command==ArenaView.COMMAND.ESC){
            arena.endGame();
        }
        else{
            if (prevcommand == ArenaView.COMMAND.UP) {
                arena.getSnake().setPosition(arena.getSnake().getPosition().up());
                arena.walkSnake(arena.getSnake().getPosition(),'|',arena.getSnake());
            }
            if (prevcommand == ArenaView.COMMAND.RIGHT) {
                arena.getSnake().setPosition(arena.getSnake().getPosition().right());
                arena.walkSnake(arena.getSnake().getPosition(),'-',arena.getSnake());
            }
            if (prevcommand == ArenaView.COMMAND.DOWN) {
                arena.getSnake().setPosition(arena.getSnake().getPosition().down());
                arena.walkSnake(arena.getSnake().getPosition(),'|',arena.getSnake());
            }
            if (prevcommand == ArenaView.COMMAND.LEFT) {
                arena.getSnake().setPosition(arena.getSnake().getPosition().left());
                arena.walkSnake(arena.getSnake().getPosition(),'-',arena.getSnake());
            }
            if(prevcommand==ArenaView.COMMAND.ESC){
                arena.endGame();
            }
        }
    }

    public void movement2(ArenaView.COMMAND command,ArenaView.COMMAND prevcommand, ArenaModel arena) throws IOException {
        if (command == ArenaView.COMMAND.UP2) {
            arena.getSnake2().setPosition(arena.getSnake2().getPosition().up());
            arena.walkSnake(arena.getSnake2().getPosition(),'|',arena.getSnake2());
        }
        if (command == ArenaView.COMMAND.RIGHT2) {
            arena.getSnake2().setPosition(arena.getSnake2().getPosition().right());
            arena.walkSnake(arena.getSnake2().getPosition(),'-',arena.getSnake2());
        }
        if (command == ArenaView.COMMAND.DOWN2) {
            arena.getSnake2().setPosition(arena.getSnake2().getPosition().down());
            arena.walkSnake(arena.getSnake2().getPosition(),'|',arena.getSnake2());
        }
        if (command == ArenaView.COMMAND.LEFT2) {
            arena.getSnake2().setPosition(arena.getSnake2().getPosition().left());
            arena.walkSnake(arena.getSnake2().getPosition(),'-',arena.getSnake2());
        }
        else{
            if (prevcommand == ArenaView.COMMAND.UP2) {
                arena.getSnake2().setPosition(arena.getSnake2().getPosition().up());
                arena.walkSnake(arena.getSnake2().getPosition(),'|',arena.getSnake2());
            }
            if (prevcommand == ArenaView.COMMAND.RIGHT2) {
                arena.getSnake2().setPosition(arena.getSnake2().getPosition().right());
                arena.walkSnake(arena.getSnake2().getPosition(),'-',arena.getSnake2());
            }
            if (prevcommand == ArenaView.COMMAND.DOWN2) {
                arena.getSnake2().setPosition(arena.getSnake2().getPosition().down());
                arena.walkSnake(arena.getSnake2().getPosition(),'|',arena.getSnake2());
            }
            if (prevcommand == ArenaView.COMMAND.LEFT2) {
                arena.getSnake2().setPosition(arena.getSnake2().getPosition().left());
                arena.walkSnake(arena.getSnake2().getPosition(),'-',arena.getSnake2());
            }
        }
    }



}
