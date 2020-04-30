package data;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;

public class Snake extends Element{
    private Position headPosition;
    private List<Character> snakebody = new ArrayList<>();
    private List<Position> pos = new ArrayList<>();
    private int length=0;

    public Snake(Position position) {
        super(position.getX(),position.getY());
        this.headPosition = position;
        snakebody.add(length, '-');
        pos.add(length,position);
        length++;
        snakebody.add(length, '-');
        pos.add(length,new Position(position.getX()-1,position.getY()));
    }
    public List<Character> getSnakeBody(){ return snakebody; }
    public void setSnakeBody(List<Character> sb){this.snakebody=sb;}

    public List<Position> getPos(){
        return pos;
    }
    public void setPos(List<Position> pos){this.pos=pos;}

    public int getLength(){
        return length;
    }
    public void setLength(int l){
        this.length=l;
    }

    public Position getHeadPosition() {
        return headPosition;
    }
    public void setHeadPosition(Position position) {
        this.headPosition = position;
    }


    public void draw(Screen screen) {
        int index=0;
        for(Character c:snakebody){
            if(index>=pos.size()){
                pos.add(length,new Position(pos.get(length-1).getX()+1,pos.get(length-1).getY()));
            }
            screen.setCharacter(pos.get(index).getX(), pos.get(index).getY(), new TextCharacter(snakebody.get(index)));
            index++;
        }
    }

}