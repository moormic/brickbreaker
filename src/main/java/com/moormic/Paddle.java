package com.moormic;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class Paddle {

    private final int width = 100;
    private final int height = 10;
    private Coordinate coordinate;

    void move(Direction direction) {
        switch (direction) {
            case RIGHT: coordinate.shiftX(width/10); break;
            case LEFT: coordinate.shiftX(-width/10); break;
        }
    }

}
