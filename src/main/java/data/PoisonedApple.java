package data;

public class PoisonedApple implements AppleInterface{

    private Position pos;

    public PoisonedApple(int x, int y){this.pos = new Position(x,y);}

    @Override
    public Position getPosition(){
        return pos;
    }

    @Override
    public void setPosition(Position position){
        this.pos=position;
    }

    @Override
    public Character getChar(){
        return '~';
    }

}
