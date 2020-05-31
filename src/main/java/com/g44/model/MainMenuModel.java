package com.g44.model;

public class MainMenuModel extends MenuModel{

    public MainMenuModel(){
        super();
        setInitialCursorPosition();
    }

    @Override
    public void changeOption(String side) {
        if(side=="left"){
            if(option!=1){
                option-=1;
                cursorPosition.setX(cursorPosition.getX()-30);
            }
        }
        else if(side=="right"){
            if(option!=2){
                option+=1;
                cursorPosition.setX(cursorPosition.getX()+30);
            }
        }

    }

    @Override
    public void setInitialCursorPosition() {
        cursorPosition=new Position(13,23);
    }
}
