package com.moormic;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.*;

@Getter
public class Brick extends Sprite {

    public Brick(Image image, Coordinate coordinate) {
        super(image, coordinate);
    }
}
