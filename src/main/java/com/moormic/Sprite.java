package com.moormic;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.*;

@Getter
@AllArgsConstructor
abstract class Sprite {
    private Image image;
    private Coordinate coordinate;
}
