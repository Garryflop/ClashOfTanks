package com.company.models;

import java.awt.*;

public class ScoutTank extends Tank {

    public ScoutTank(String name, int x, int y) {
        // Разведчик – 80 единиц здоровья
        super(name, x, y, Color.GREEN, 80);
    }

    @Override
    public int getDamage() {
        return 20; // Средний урон
    }

    @Override
    public int getSpeed() {
        return 15; // Высокая скорость
    }
}
