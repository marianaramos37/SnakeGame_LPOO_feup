package model;

public abstract interface AppleInterface {
    Position getPosition();
    void setPosition(Position position);
    Character getChar();
    public void affect(Snake snake);
}
