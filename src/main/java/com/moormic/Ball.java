package com.moormic;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class Ball {

    private final int radius = 5;
    private Coordinate coordinate;
    private Vector vector;

    void deflect(Direction direction) {
        switch (direction) {
            case UP:
            case DOWN:
                vector.invertY(); break;
            case LEFT:
            case RIGHT:
                vector.invertX(); break;
        }
    }

    void move() {
        coordinate.shiftX(vector.getXDir() * radius * 2);
        coordinate.shiftY(vector.getYDir() * radius * 2);
    }

}
