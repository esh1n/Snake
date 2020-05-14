package com.zetcode;

public enum Direction {

    RIGHT(1),
    LEFT(-1),
    UP(-1),
    DOWN(1),
    NO_DIRECTION(0);

    private final int delta;

    Direction(int delta) {
        this.delta = delta;
    }

    public boolean isHorizontal() {
        return this == LEFT || this == RIGHT;
    }

    public boolean isVertical() {
        return this == UP || this == DOWN;
    }

    public int getDelta() {
        return delta;
    }


}
