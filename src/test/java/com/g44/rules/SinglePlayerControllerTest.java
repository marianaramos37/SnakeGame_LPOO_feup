package com.g44.rules;

import com.g44.commands.*;
import com.g44.controller.SinglePlayerController;
import com.g44.controller.StateControllers;
import com.g44.model.ArenaModel;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;

public class SinglePlayerControllerTest {
    @Test
    public void runTest() throws IOException, InterruptedException, FontFormatException {

        ArenaModel arena= new ArenaMock();

        //Comandos mudam para estado null- exit
        List<Command> commands = new ArrayList<>();
        commands.add(new ArrowRight());
        commands.add(new ArrowLeft());
        commands.add(new SKey());
        commands.add(new EnterKey());

        ViewMock view = new ViewMock(commands);

        SinglePlayerController controller = new SinglePlayerController(view,1);
        StateControllers nextState = controller.run();

        assertNull(nextState);
    }
}
