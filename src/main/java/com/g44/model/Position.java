package com.g44.model;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position up() {
        return new Position(this.x, this.y - 1);
    }

    public Position right() {
        return new Position(this.x + 1, this.y);
    }

    public Position down() {
            return new Position(this.x, this.y + 1);
    }

    public Position left() {
            return new Position(this.x - 1, this.y);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null) return false;

        if (getClass() != o.getClass()) return false;

        Position p = (Position) o;
        return x == p.getX() && y == p.getY();
    }
}
