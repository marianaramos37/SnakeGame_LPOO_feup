package model;

import java.util.ArrayList;
import java.util.List;

public class Obstaculo extends Element{
    private List<Element> obstaculo;
    public Obstaculo(Position p) {
        super(p);
        obstaculo=new ArrayList<>();
        obstaculo.add(new Wall(0,0));
        obstaculo.add(new Wall(0,1));
        obstaculo.add(new Wall(1,0));
    }

    public List<Element> getObstaculo() {
        return obstaculo;
    }
    public void setObstaculo(List<Element> obstaculo) {
        this.obstaculo = obstaculo;
    }
}
