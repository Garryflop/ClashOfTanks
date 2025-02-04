package com.company.models;

import com.company.Bullet;
import com.company.singleton.SoundManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Tank {
    private String name;
    private int x;
    private int y;
    private int health;
    private Color color;
    private int angle;
    private ArrayList<Bullet> bullets;


    public Tank(String name, int x, int y, Color color, int health) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.health = health;
        this.color = color;
        this.angle = 0;
        this.bullets = new ArrayList<>();
    }

    // Абстрактный метод для получения урона танка (различается для каждого типа)
    public abstract int getDamage();

    // Абстрактный метод для получения скорости движения танка
    public abstract int getSpeed();

    public void moveForward() {
        double rad = Math.toRadians(angle);
        int speed = getSpeed();
        x = Math.max(0, Math.min(460, x + (int) (Math.cos(rad) * speed)));
        y = Math.max(0, Math.min(460, y + (int) (Math.sin(rad) * speed)));
    }

    public void moveBackward() {
        double rad = Math.toRadians(angle);
        int speed = getSpeed();
        x = Math.max(0, Math.min(460, x - (int) (Math.cos(rad) * speed)));
        y = Math.max(0, Math.min(460, y - (int) (Math.sin(rad) * speed)));
    }

    public void rotateLeft() {
        angle = (angle - 5 + 360) % 360;
    }

    public void rotateRight() {
        angle = (angle + 5) % 360;
    }

    public void fireBullet(Tank opponent, Runnable onOpponentDefeat) {
        double rad = Math.toRadians(angle);
        int bulletX = (int) (x + 20 + Math.cos(rad) * 20);
        int bulletY = (int) (y + 20 + Math.sin(rad) * 20);
        bullets.add(new Bullet(bulletX, bulletY, angle, color, opponent, onOpponentDefeat, getDamage()));
        SoundManager.getInstance().playSound("com/company/resources/sounds/TankShoot.wav");
    }

    public void updateBullets(Graphics g) {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.move();
            bullet.draw(g);
            if (bullet.isOutOfBounds() || bullet.hasHitTarget()) {
                iterator.remove();
            }
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }

    public boolean isDestroyed() {
        return health <= 0;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);

        int centerX = x + 20;
        int centerY = y + 20;

        g2d.translate(centerX, centerY);
        g2d.rotate(Math.toRadians(angle));

        g2d.fillRect(-20, -20, 40, 40);

        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, 0, 20, 0);

        g2d.dispose();

        g.setColor(color);
        g.drawString(name + " (" + health + ")", x, y - 10);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}