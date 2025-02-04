package com.company;

import java.awt.*;

public class AssaultTank extends Tank{

    public AssaultTank(String name, int x, int y) {
        super(name, x, y, Color.RED, 150);

    }

    @Override
    public int getDamage() {
        return 25; // Штурмовик наносит 25 урона
    }

    @Override
    public int getSpeed() {
        return 10; // Скорость движения 10 (например)
    }
}
