package com.moormic;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Coordinate {
    private int x;
    private int y;

    void shiftX(int x) {
        this.x += x;
    }

    void shiftY(int y) {
        this.y += y;
    }

}
