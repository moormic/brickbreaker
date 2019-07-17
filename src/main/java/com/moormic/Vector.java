package com.moormic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class Vector {
    private int xDir;
    private int yDir;

    void invertX() {
        xDir *= -1;
    }

    void invertY() {
        yDir *= -1;
    }
}
