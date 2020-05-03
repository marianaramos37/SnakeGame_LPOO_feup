package data;

public abstract class Element {
    Position position;

    public Element(int x, int y) {
        this.position=new Position(x,y);
    }
    public Element(Position p){
        this.position=p;
    }
    public Position getPosition(){
        return this.position;
    }
    public void setPosition(Position position){
        this.position=position;
    }

}
