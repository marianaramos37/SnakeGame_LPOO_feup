package data;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;

public class Snake{
    private Position headPosition;
    private List<Character> snake = new ArrayList<>();
    private List<Position> pos = new ArrayList<>();
    private int length=0;

    public Snake(Position position) {
        this.headPosition = position;
        snake.add(length, '-');
        pos.add(length,position);
        length++;
        snake.add(length, '-');
        pos.add(length,new Position(position.getX()-1,position.getY()));
    }

    public Position getHeadPosition() {
        return headPosition;
    }

    public void setHeadPosition(Position position) {
        this.headPosition = position;
    }

    public void growSnake(){
        length++;

        if(snake.get(length - 1) == '-'){
            snake.add(length,'-');

            if(pos.get(length-2).getX()+1 == pos.get(length-1).getX()){
                pos.add(length,new Position(pos.get(length-1).getX()+1,pos.get(length-1).getY()));
            }
            if(pos.get(length-2).getX()-1 == pos.get(length-1).getX()){
                pos.add(length,new Position(pos.get(length-1).getX()-1,pos.get(length-1).getY()));
            }

        }else{
            snake.add(length,'|');

            if(pos.get(length-2).getY()+1 == pos.get(length-1).getY()){
                pos.add(length,new Position(pos.get(length-1).getX(),pos.get(length-1).getY()+1));
            }
            if(pos.get(length-2).getY()-1 == pos.get(length-1).getY()){
                pos.add(length,new Position(pos.get(length-1).getX(),pos.get(length-1).getY()-1));
            }
        }
    }

    public void walkSnake(Position nextPosition, Character headOrientation){
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
        for(Character c:snake){
            previousOrientation=c;
            snake.set(index,headOrientation);
            headOrientation=previousOrientation;
            index++;
        }


    }

    public void drawSnake(Screen screen){
        int index=0;
        for(Character c:snake){
            screen.setCharacter(pos.get(index).getX(), pos.get(index).getY(), new TextCharacter(snake.get(index)));
            index++;
        }
    }

}