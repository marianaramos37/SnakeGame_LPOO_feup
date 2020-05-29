package controller;

import model.ArenaModel;

public class ArenaController {
    private ArenaModel arenaModel;

    public ArenaController(ArenaModel arenaModel) {
        this.arenaModel= arenaModel;
    }

    public ArenaModel getArenaModel(){
        return arenaModel;
    }

    public void movement() {
        if(arenaModel.getSnake().getDirection()=="left"){
            arenaModel.setSnakeHeadPosition(arenaModel.getSnakeHeadPosition().left());
            arenaModel.walkSnake(arenaModel.getSnakeHeadPosition(), '-',arenaModel.getSnake());
        }
        if(arenaModel.getSnake().getDirection()=="right"){
            arenaModel.setSnakeHeadPosition(arenaModel.getSnakeHeadPosition().right());
            arenaModel.walkSnake(arenaModel.getSnakeHeadPosition(), '-',arenaModel.getSnake());
        }
        if(arenaModel.getSnake().getDirection()=="up"){
            arenaModel.setSnakeHeadPosition(arenaModel.getSnakeHeadPosition().up());
            arenaModel.walkSnake(arenaModel.getSnakeHeadPosition(),'|',arenaModel.getSnake());
        }
        if(arenaModel.getSnake().getDirection()=="down"){
            arenaModel.setSnakeHeadPosition(arenaModel.getSnakeHeadPosition().down());
            arenaModel.walkSnake(arenaModel.getSnakeHeadPosition(), '|',arenaModel.getSnake());
        }
        if(arenaModel.getSnake2().getDirection()=="left"){
            arenaModel.setSnakeHead2Position(arenaModel.getSnakeHead2Position().left());
            arenaModel.walkSnake(arenaModel.getSnakeHead2Position(),'-',arenaModel.getSnake2());
        }
        if(arenaModel.getSnake2().getDirection()=="right"){
            arenaModel.setSnakeHead2Position(arenaModel.getSnakeHead2Position().right());
            arenaModel.walkSnake(arenaModel.getSnakeHead2Position(),'-',arenaModel.getSnake2());
        }
        if(arenaModel.getSnake2().getDirection()=="up"){
            arenaModel.setSnakeHead2Position(arenaModel.getSnakeHead2Position().up());
            arenaModel.walkSnake(arenaModel.getSnakeHead2Position(),'|',arenaModel.getSnake2());
        }
        if(arenaModel.getSnake2().getDirection()=="down"){
            arenaModel.setSnakeHead2Position(arenaModel.getSnakeHead2Position().down());
            arenaModel.walkSnake(arenaModel.getSnakeHead2Position(),'|',arenaModel.getSnake2());
        }
    }


}

