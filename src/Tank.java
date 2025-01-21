import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Tank {
    private String name;
    private int x;
    private int y;
    private int health;
    private Color color;
    private int angle;
    private ArrayList<Bullet> bullets;

    public Tank(String name, int x, int y, Color color) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.health = 100;
        this.color = color;
        this.angle = 0;
        this.bullets = new ArrayList<>();
    }

    public void moveForward() {
    }

    public void moveBackward() {
    }

    public void rotateLeft() {
    }

    public void rotateRight() {
    }

    public void fireBullet(Tank opponent, Runnable onOpponentDefeat) {
    }

    public void updateBullets(Graphics g) {
    }

    public void takeDamage(int damage) {
        setHealth(health - damage);
    }

    public boolean isDestroyed() {
        return health <= 0;
    }

    public void draw(Graphics g) {
    }

    public void setHealth(int health) {
        if (health <= 0) {
            this.health = 0;
        }
        this.health = health;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}