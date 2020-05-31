package com.g44.model;

public abstract class MenuModel {
    protected int option;
    protected boolean selected;
    protected Position cursorPosition;

    public MenuModel(){
        option=1;
        selected=false;
        cursorPosition=new Position(13,23);
    }

    public int getOption(){
        return option;
    }

    public void setOption(int o){
        this.option=o;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Position getCursorPosition() {
        return cursorPosition;
    }

    public abstract void setInitialCursorPosition();

    public abstract void changeOption(String side);
}
