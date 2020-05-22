package model;

import java.util.ArrayList;
import java.util.List;

public class Snake{
    private Position headPosition;
    private List<Character> snakebody = new ArrayList<>();
    private List<Position> pos = new ArrayList<>();
    private int length=0;
    private boolean isShrink = false;
    private int velocidade;

    public Snake(Position position) {
        this.headPosition = position;
        snakebody.add(length, '-');
        pos.add(length,position);
        length++;
        snakebody.add(length, '-');
        pos.add(length,new Position(position.getX()-1,position.getY()));
        velocidade=150;
    }


    public List<Character> getSnakeBody(){ return snakebody; }
    public void setSnakeBody(List<Character> sb){this.snakebody=sb;}

    public List<Position> getPos(){
        return pos;
    }
    public void setPos(List<Position> pos){this.pos=pos;}

    public Position getPosition() {
        return headPosition;
    }
    public void setPosition(Position headPosition) {
        this.headPosition = headPosition;
    }

    public int getLength(){
        return length;
    }
    public void setLength(int l){
        this.length=l;
    }

    public boolean getShrink(){return isShrink;}

    public int getVelocidade() {
        return velocidade;
    }
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public void shrink(){
        this.isShrink = true;
    }
    public void unshrink(){
        this.isShrink = false;
    }

    public void poison(){
        setVelocidade(150); //resets to initial velocity

        if(this.length >= 6){
            this.length-=5;
            this.pos=this.pos.subList(0,this.length+1);
            this.snakebody=this.snakebody.subList(0,this.length+1);
        }else{
            this.length=1;
            this.pos=this.pos.subList(0,2);
            this.snakebody=this.snakebody.subList(0,2);
        }
    }

}