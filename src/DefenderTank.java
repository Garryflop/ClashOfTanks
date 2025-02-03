package com.company;

import java.awt.*;

public class DefenderTank extends Tank {

    public DefenderTank(String name, int x, int y) {
        // Защитник – 150 единиц здоровья
        super(name, x, y, Color.BLUE, 250);
    }

    @Override
    public int getDamage() {
        return 15; // Меньший урон
    }

    @Override
    public int getSpeed() {
        return 5; // Медленная скорость
    }
}
