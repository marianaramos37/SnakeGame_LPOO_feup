package rules;

import com.googlecode.lanterna.TextCharacter;
import data.ArenaModel;
import gui.ArenaView;

import java.io.IOException;

public class ArenaController {
    private ArenaView gui;
    private ArenaModel arena;

    public ArenaController(ArenaView gui, ArenaModel arena) {
        this.gui = gui;
        this.arena = arena;
    }

    public void start() throws IOException {
        gui.screen.setCharacter(arena.getSnakeHeadPosition().getX(), arena.getSnakeHeadPosition().getY(), new TextCharacter('-'));
        gui.screen.setCharacter(arena.getSnakeHeadPosition().getX()-1, arena.getSnakeHeadPosition().getY(), new TextCharacter('-'));


        while (true) {

            ArenaView.COMMAND command = gui.getCommand();

            if (command == ArenaView.COMMAND.UP) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().up());
                arena.getSnake().walkSnake(arena.getSnakeHeadPosition(),'|');
            }
            if (command == ArenaView.COMMAND.RIGHT) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().right());
                arena.getSnake().walkSnake(arena.getSnakeHeadPosition(), '-');
            }
            if (command == ArenaView.COMMAND.DOWN) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().down());
                arena.getSnake().walkSnake(arena.getSnakeHeadPosition(), '|');
            }
            if (command == ArenaView.COMMAND.LEFT) {
                arena.setSnakeHeadPosition(arena.getSnakeHeadPosition().left());
                arena.getSnake().walkSnake(arena.getSnakeHeadPosition(), '-');
            }
            arena.getSnake().drawSnake(gui.screen);

            gui.drawArena(arena);
        }

    }
}
