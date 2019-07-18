package com.moormic;

import java.awt.*;

class Ball extends Sprite {

    private int size = 10;
    private Vector vector;

    Ball(Image image, Coordinate coordinate) {
        super(image, coordinate);
        vector = new Vector(1, 1);
    }

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
        coordinate.shiftX(vector.getXDir() * size);
        coordinate.shiftY(vector.getYDir() * size);
    }

}
