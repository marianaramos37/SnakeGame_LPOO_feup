package com.g44.model;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ObstaculoTest {

    @Test
    public void testConstructorandGet(){
        Obstaculo obs1 = new Obstaculo(new Position(0,0),1);

        List<Element> o1= obs1.getObstaculo();

        assertEquals(3,o1.size());
        assertEquals(0,o1.get(0).getPosition().getX());
        assertEquals(0,o1.get(0).getPosition().getY());
        assertEquals(0,o1.get(1).getPosition().getX());
        assertEquals(1,o1.get(1).getPosition().getY());
        assertEquals(1,o1.get(2).getPosition().getX());
        assertEquals(0,o1.get(2).getPosition().getY());


        Obstaculo obs2 = new Obstaculo(new Position(0,0),2);

        List<Element> o2= obs2.getObstaculo();

        assertEquals(3,o2.size());
        assertEquals(0,o2.get(0).getPosition().getX());
        assertEquals(0,o2.get(0).getPosition().getY());
        assertEquals(1,o2.get(1).getPosition().getX());
        assertEquals(0,o2.get(1).getPosition().getY());
        assertEquals(2,o2.get(2).getPosition().getX());
        assertEquals(0,o2.get(2).getPosition().getY());
    }

    @Test
    public void testGetType(){
        Obstaculo obs1 = new Obstaculo(new Position(0,0),1);
        assertEquals(1,obs1.getType());

        Obstaculo obs2 = new Obstaculo(new Position(0,0),2);
        assertEquals(2,obs2.getType());
    }

    @Test
    public void testSetPosition(){
        Obstaculo obs1 = new Obstaculo(new Position(0,0),1);
        obs1.setPosition(new Position(2,2));

        assertEquals(2,obs1.getObstaculo().get(0).getPosition().getX());
        assertEquals(2,obs1.getObstaculo().get(0).getPosition().getY());
        assertEquals(2,obs1.getObstaculo().get(1).getPosition().getX());
        assertEquals(3,obs1.getObstaculo().get(1).getPosition().getY());
        assertEquals(3,obs1.getObstaculo().get(2).getPosition().getX());
        assertEquals(2,obs1.getObstaculo().get(2).getPosition().getY());

        Obstaculo obs2 = new Obstaculo(new Position(0,0),2);
        obs2.setPosition(new Position(2,2));

        assertEquals(2,obs2.getObstaculo().get(0).getPosition().getX());
        assertEquals(2,obs2.getObstaculo().get(0).getPosition().getY());
        assertEquals(3,obs2.getObstaculo().get(1).getPosition().getX());
        assertEquals(2,obs2.getObstaculo().get(1).getPosition().getY());
        assertEquals(4,obs2.getObstaculo().get(2).getPosition().getX());
        assertEquals(4,obs2.getObstaculo().get(2).getPosition().getX());
        assertEquals(2,obs2.getObstaculo().get(2).getPosition().getY());
    }
}
