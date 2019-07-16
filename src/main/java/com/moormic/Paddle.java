package com.moormic;

import lombok.Setter;

class Paddle extends Sprite {

    @Setter
    private Direction direction;

    private void move() {

    }


    enum Direction {
        LEFT,
        RIGHT
    }
}
