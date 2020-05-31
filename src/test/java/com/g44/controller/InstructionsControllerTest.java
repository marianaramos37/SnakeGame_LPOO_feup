package com.g44.controller;

import com.g44.commands.ArrowRight;
import com.g44.commands.Command;
import com.g44.commands.EnterKey;
import com.g44.commands.SKey;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InstructionsControllerTest {

    @Test
    public void runTest() throws IOException, InterruptedException, FontFormatException {

        //Comandos mudam para estado mainMenu
        List<Command> commands = new ArrayList<>();
        commands.add(new ArrowRight());
        commands.add(new SKey());
        commands.add(new EnterKey());

        ViewMock view = new ViewMock(commands);

        IntructionsController controller = new IntructionsController(view);
        StateControllers nextState= controller.run();

       assertEquals(MainMenuController.class,nextState.getClass());

    }
}
