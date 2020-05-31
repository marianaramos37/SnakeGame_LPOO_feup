package com.g44.controller;

import com.g44.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArenaMock extends ArenaModel {

    public ArenaMock() throws IOException {
        super(13,8);
    }

    @Override
    public Snake getSnake() {
        Snake s=new Snake(new Position(1,1));
        return s;
    }
    @Override
    public List<Obstaculo> getObstaculos() {
        Obstaculo obj= new Obstaculo(new Position(4,3),1);
        List<Obstaculo> obstaculos= new ArrayList<>();
        obstaculos.add(obj);
        return  obstaculos;
    }
    @Override
    public List<AppleInterface> getApples() {
        Apple apple=new Apple(6,2);
        SpecialApple specialAplle= new SpecialApple(6,1);
        List<AppleInterface> apples= new ArrayList<>();
        apples.add(apple); apples.add(specialAplle);
        return  apples;
    }
    @Override
    public Snake getSnake2() {
        Snake s=new Snake(new Position(1,1));
        return s;
    }


}
