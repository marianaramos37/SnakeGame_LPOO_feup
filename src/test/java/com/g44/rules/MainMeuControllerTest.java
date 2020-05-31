package com.g44.rules;

import com.g44.commands.*;
import com.g44.controller.*;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MainMeuControllerTest {
    @Test
    public void runTest() throws IOException, InterruptedException, FontFormatException {

        //Comandos mudam para estado null
        List<Command> commands = new ArrayList<>();
        commands.add(new ArrowRight());
        commands.add(new EscKey());

        ViewMock view = new ViewMock(commands);

        MainMenuController controller = new MainMenuController(view);
        StateControllers nextState= controller.run();

        assertNull(nextState);

        //Comandos mudam para estado MenuLevels
        commands.add(new ArrowRight());
        commands.add(new ArrowLeft());
        commands.add(new EnterKey());
        view= new ViewMock(commands);

        MainMenuController controller2 = new MainMenuController(view);
        nextState= controller2.run();

        assertEquals(MenuLevelsController.class,nextState.getClass());

        //Comandos mudam para MultyPlayerState
        commands.add(new ArrowDown());
        commands.add(new ArrowUp());
        commands.add(new ArrowLeft());
        commands.add(new ArrowRight());
        commands.add(new EnterKey());
        view= new ViewMock(commands);

        MainMenuController controller3 = new MainMenuController(view);
        nextState= controller3.run();

        assertEquals(MultiPlayerController.class,nextState.getClass());

        //Comandos mudam para Instructions
        commands.add(new ArrowRight());
        commands.add(new IKey());
        view= new ViewMock(commands);

        MainMenuController controller4 = new MainMenuController(view);
        nextState= controller4.run();

        assertEquals(IntructionsController.class,nextState.getClass());
    }
}
