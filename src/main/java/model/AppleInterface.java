package model;

import java.util.concurrent.ThreadLocalRandom;

public interface AppleInterface {
    Position getPosition();
    void setPosition(Position position);
    Character getChar();
    void affect(Snake snake);
    default void changePosition(){
        setPosition(new Position(ThreadLocalRandom.current().nextInt(1, 60-1), ThreadLocalRandom.current().nextInt(1, 30 - 1)));
    }
}
