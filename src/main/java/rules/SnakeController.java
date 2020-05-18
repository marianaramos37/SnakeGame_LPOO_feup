package rules;

import data.ArenaModel;
import data.Position;
import data.Snake;

import java.util.List;

public class SnakeController {
    private ArenaModel arena;
    private int velocidade;


    public SnakeController(ArenaModel arena,int vel){
        this.arena=arena; this.velocidade=vel;
    }

    public void growSnake(){
        Snake snake=this.arena.getSnake();
        List<Character> snakebody = this.arena.getSnake().getSnakeBody();
        List<Position> pos = this.arena.getSnake().getPos();
        int length=this.arena.getSnake().getLength();

        snake.setLength(length+1);

        if(snakebody.get(snake.getLength() - 1) == '-'){
            snakebody.add(snake.getLength() ,'-');

            if (pos.get(snake.getLength() - 2).getX() + 1 == pos.get(snake.getLength() - 1).getX()) {
                pos.add(snake.getLength(), new Position(pos.get(snake.getLength() - 1).getX() + 1, pos.get(snake.getLength() - 1).getY()));
            }
            if (pos.get(snake.getLength() - 2).getX() - 1 == pos.get(snake.getLength() - 1).getX()) {
                pos.add(snake.getLength(), new Position(pos.get(snake.getLength() - 1).getX() - 1, pos.get(snake.getLength() - 1).getY()));
            }


        }else{
            snakebody.add(snake.getLength() ,'|');

            if(pos.get(snake.getLength() -2).getY()+1 == pos.get(snake.getLength() -1).getY()){
                pos.add(snake.getLength() ,new Position(pos.get(snake.getLength() -1).getX(),pos.get(snake.getLength() -1).getY()+1));
            }
            if(pos.get(snake.getLength() -2).getY()-1 == pos.get(snake.getLength() -1).getY()){
                pos.add(snake.getLength() ,new Position(pos.get(snake.getLength() -1).getX(),pos.get(snake.getLength() -1).getY()-1));
            }
        }



        snake.setSnakeBody(snakebody);
        snake.setPos(pos);
        arena.setSnake(snake);
    }

    public void walkSnake(Position nextPosition, Character headOrientation, Snake snake){
        //Snake snake=this.arena.getSnake();
        List<Character> snakebody = snake.getSnakeBody();
        List<Position> pos = snake.getPos();

        Position previous;
        int index=0;
        for(Position p:pos){
            previous=p;
            pos.set(index,nextPosition);
            nextPosition=previous;
            index++;
        }
        index=0;
        Character previousOrientation;
        for(Character c:snakebody){
            previousOrientation=c;
            snakebody.set(index,headOrientation);
            headOrientation=previousOrientation;
            index++;
        }

        snake.setSnakeBody(snakebody);
        snake.setPos(pos);
        arena.setSnake(snake);
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public void shrink(){
        Snake snake=this.arena.getSnake();
        snake.isShrink = true;
        arena.setSnake(snake);
    }

    public void unshrink(){
        Snake snake=this.arena.getSnake();
        snake.isShrink = false;
        arena.setSnake(snake);
    }

    public void poison(){
        setVelocidade(150); //resets to initial velocity

        Snake snake=this.arena.getSnake();

        if(snake.getLength() >= 6){
            snake.setLength(snake.getLength()-5);
            snake.setPos(snake.getPos().subList(0,snake.getLength()+1));
            snake.setSnakeBody(snake.getSnakeBody().subList(0,snake.getLength()+1));
        }else{
            snake.setLength(1);
            snake.setPos(snake.getPos().subList(0,2));
            snake.setSnakeBody(snake.getSnakeBody().subList(0,2));
        }

        arena.setSnake(snake);
    }

    public void updateVelocidade(){

        if(arena.getScore().getScore() % 5 == 0 && arena.getScore().getScore() != 0 && getVelocidade() > 90){
            setVelocidade(getVelocidade()-5);
        }

    }
}
