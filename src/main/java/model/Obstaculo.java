package model;

import java.util.ArrayList;
import java.util.List;

public class Obstaculo extends Element{
    private List<Element> obstaculo;
    private int type;

    public Obstaculo(Position p,int t) {
        super(p);
        this.type=t;
        if(t==1){
            obstaculo=new ArrayList<>();
            obstaculo.add(new Wall(0,0));
            obstaculo.add(new Wall(0,1));
            obstaculo.add(new Wall(1,0));
        }
        else{
            obstaculo=new ArrayList<>();
            obstaculo.add(new Wall(0,0));
            obstaculo.add(new Wall(1,0));
            obstaculo.add(new Wall(2,0));
        }
    }

    /*
    obstaculo type 1: _|
    obstaculo type 2: ___
     */

    public List<Element> getObstaculo() {
        return obstaculo;
    }

    public int getType(){
        return type;
    }

    @Override
    public void setPosition(Position position){
        if(this.getType()==1){
            obstaculo.get(0).setPosition(position);
            obstaculo.get(1).setPosition(new Position(position.getX(),position.getY()+1));
            obstaculo.get(2).setPosition(new Position(position.getX()+1,position.getY()));
        }
        else{
            obstaculo.get(0).setPosition(position);
            obstaculo.get(1).setPosition(new Position(position.getX()+1,position.getY()));
            obstaculo.get(2).setPosition(new Position(position.getX()+2,position.getY()));
        }
    }
}
