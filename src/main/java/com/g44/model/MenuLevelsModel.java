package com.g44.model;

public class MenuLevelsModel extends MenuModel {

    public MenuLevelsModel(){
        super();
        setInitialCursorPosition();
    }

    @Override
    public void changeOption(String side) {
        if(side=="left"){
            if(option!=1){
                option-=1;
                cursorPosition.setX(cursorPosition.getX()-15);
            }
        }
        else if(side=="right"){
            if(option!=4){
                option+=1;
                cursorPosition.setX(cursorPosition.getX()+15);
            }
        }

    }

    @Override
    public void setInitialCursorPosition() {
        cursorPosition=new Position(6,23);
    }
}
