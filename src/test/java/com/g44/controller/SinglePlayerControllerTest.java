package com.g44.controller;

import com.g44.commands.ArrowLeft;
import com.g44.commands.ArrowRight;
import com.g44.commands.Command;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SinglePlayerControllerTest {
    @Test
    public void runTest() throws IOException, InterruptedException, FontFormatException {

        //Comandos mudam para estado null- exit
        List<Command> commands = new ArrayList<>();
        commands.add(new ArrowRight());
        commands.add(new ArrowLeft());

        ViewMock view = new ViewMock(commands);

        SinglePlayerController controller = new SinglePlayerController(view,1);
        controller.run();

        assertEquals("right",controller.getArena().getSnake().getDirection());
    }
}
