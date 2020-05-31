package com.g44.rules;

import com.g44.commands.Command;
import com.g44.commands.EnterKey;
import com.g44.controller.MenuLevelsController;
import com.g44.controller.SinglePlayerController;
import com.g44.controller.StateControllers;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MenuLevelsControllerTest {

    @Test
    public void runTest() throws IOException, InterruptedException, FontFormatException {

        //Comandos mudam Level 1
        List<Command> commands = new ArrayList<>();
        commands.add(new EnterKey());

        ViewMock view = new ViewMock(commands);

        MenuLevelsController controller = new MenuLevelsController(view);
        StateControllers nextState= controller.run();

        assertEquals(SinglePlayerController.class,nextState.getClass());

    }
}
