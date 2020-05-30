package controller;


import model.ArenaModel;
import model.Snake;

public class ArenaController {
    private ArenaModel arenaModel;

    public ArenaController(ArenaModel arenaModel) {
        this.arenaModel= arenaModel;
    }

    public void movement(Snake snake) {
        /*if(snake.getDirection()=="left"){
            snake.setPosition(snake.getPosition().left());
            snake.walkSnake( '-');
        }
        if(snake.getDirection()=="right"){
            snake.setPosition(snake.getPosition().right());
            snake.walkSnake( '-');
        }
        if(snake.getDirection()=="up"){
            snake.setPosition(snake.getPosition().up());
            snake.walkSnake('|');
        }
        if(snake.getDirection()=="down"){
            snake.setPosition(snake.getPosition().down());
            snake.walkSnake( '|');
        }*/
    }
}

