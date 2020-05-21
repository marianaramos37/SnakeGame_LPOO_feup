package rules;

import controller.ArenaController;
import model.ArenaModel;
import model.Position;
import view.ArenaView;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class ArenaControllerTest {

    @Test
    public void movementTest() throws IOException, IOException {
        //Confirmair este teste mais tarde
        ArenaModel arena=new ArenaModel(20,20);
        ArenaView gui= new ArenaView(20,20);
        ArenaController arenaController= new ArenaController(gui,arena);

        ArenaView.COMMAND cmd=  ArenaView.COMMAND.DOWN;
        ArenaView.COMMAND prevcmd= ArenaView.COMMAND.DOWN;

        arenaController.movement(cmd,prevcmd,arena);

        arena.walkSnake(arena.getSnakeHeadPosition(), '|',arena.getSnake());

        assertEquals(new Position(10, 11), arena.getSnake().getPosition());
    }

}
