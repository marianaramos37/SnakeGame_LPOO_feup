package com.g44.model;

public class GameOverModel extends MenuModel{

    public GameOverModel(){
        super();
        setInitialCursorPosition();
    }

    @Override
    public void changeOption(String side) {
        if(side=="left"){
            if(option!=1){
                option-=1;
                cursorPosition.setX(cursorPosition.getX()-32);
            }
        }
        else if(side=="right"){
            if(option!=2){
                option+=1;
                cursorPosition.setX(cursorPosition.getX()+32);
            }
        }
    }

    @Override
    public void setInitialCursorPosition() {
        cursorPosition=new Position(11,23);
    }
}
