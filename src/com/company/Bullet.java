package com.company;

import java.awt.*;

public class Bullet {
    private int x;
    private int y;
    private int speed;
    private int angle;
    private Color color;
    private Tank target;
    private Runnable onHit;
    private int damage;

    public Bullet(int x, int y, int angle, Color color, Tank target, Runnable onHit, int damage) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.speed = 15;
        this.color = color;
        this.target = target;
        this.onHit = onHit;
        this.damage = damage;
    }

    public void move() {
        double rad = Math.toRadians(angle);
        x += (int) (Math.cos(rad) * speed);
        y += (int) (Math.sin(rad) * speed);
    }

    public boolean isOutOfBounds() {
        return x < 0 || x > 500 || y < 0 || y > 500;
    }

    public boolean hasHitTarget() {
        if (Math.abs(x - target.getX()) < 40 && Math.abs(y - target.getY()) < 40) {
            target.takeDamage(damage);
            if (target.isDestroyed()) {
                onHit.run();
            }
            return true;
        }
        return false;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - 5, y - 5, 10, 10);
    }
}