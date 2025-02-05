package com.company.factory;


public class FactoryParams {
    private final String name;
    private final int x;
    private final int y;

    public FactoryParams(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
