package com.moormic;

import lombok.Setter;

import java.awt.*;

class Paddle extends Sprite {

    @Setter
    private Direction direction;

    public Paddle(Image image, Coordinate coordinate) {
        super(image, coordinate);
    }


    private void move() {

    }

}
