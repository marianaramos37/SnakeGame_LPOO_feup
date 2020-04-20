package rules;

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
        while (true) {
            gui.drawArena(arena);

            ArenaView.COMMAND command = gui.getCommand();

            if (command == ArenaView.COMMAND.UP)
                arena.setSnakePosition(arena.getSnakePosition().up());
            if (command == ArenaView.COMMAND.RIGHT)
                arena.setSnakePosition(arena.getSnakePosition().right());
            if (command == ArenaView.COMMAND.DOWN)
                arena.setSnakePosition(arena.getSnakePosition().down());
            if (command == ArenaView.COMMAND.LEFT)
                arena.setSnakePosition(arena.getSnakePosition().left());
        }

    }
}
