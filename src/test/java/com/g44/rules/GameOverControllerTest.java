package com.g44.rules;

import com.g44.commands.*;
import com.g44.controller.GameOverController;
import com.g44.controller.MainMenuController;
import com.g44.controller.StateControllers;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GameOverControllerTest {
    @Test
    public void runTest() throws IOException, InterruptedException, FontFormatException {

        //Comandos mudam para estado null- exit
        List<Command> commands = new ArrayList<>();
        commands.add(new ArrowRight());
        commands.add(new ArrowLeft());
        commands.add(new SKey());
        commands.add(new EnterKey());

        ViewMock view = new ViewMock(commands);

        GameOverController controller = new GameOverController(view);
        StateControllers nextState= controller.run();

        assertNull(nextState);

        //Comandos mudam para estado mainmenu - restart
        commands.add(new ArrowRight());
        commands.add(new EnterKey());

        view = new ViewMock(commands);

        GameOverController controller2 = new GameOverController(view);
        nextState= controller.run();

        assertEquals(MainMenuController.class, nextState.getClass());

    }
}
