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
    private boolean loser;
    private boolean ghost;
    private String direction;

    public Snake(Position position) {
        this.headPosition = position;
        snakebody.add(length, '-');
        pos.add(length,position);
        length++;
        snakebody.add(length, '-');
        pos.add(length,new Position(position.getX()-1,position.getY()));
        velocidade=150;
        loser=false;
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
    public boolean getLoser(){
        return loser;
    }
    public void setLoser(boolean l){
        this.loser=l;
    }
    public boolean isGhost() {
        return ghost;
    }
    public void setGhost(boolean ghost) {
        this.ghost = ghost;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
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

    public void growSnake(){
        setLength(length+1);

        if(snakebody.get(length - 1) == '-'){
            snakebody.add(length ,'-');

            if (pos.get(length - 2).getX() + 1 == pos.get(length- 1).getX()) {
                pos.add(length, new Position(pos.get(length - 1).getX() + 1, pos.get(length - 1).getY()));
            }
            if (pos.get(length - 2).getX() - 1 == pos.get(getLength() - 1).getX()) {
                pos.add(length, new Position(pos.get(length - 1).getX() - 1, pos.get(length - 1).getY()));
            }


        }else{
            snakebody.add(length ,'|');

            if(pos.get(length -2).getY()+1 == pos.get(length-1).getY()){
                pos.add(length ,new Position(pos.get(length -1).getX(),pos.get(length -1).getY()+1));
            }
            if(pos.get(length -2).getY()-1 == pos.get(length-1).getY()){
                pos.add(length ,new Position(pos.get(length -1).getX(),pos.get(length -1).getY()-1));
            }
        }
    }
}