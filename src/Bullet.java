import java.awt.*;

public class Bullet {
    private int x;
    private int y;
    private int speed;
    private int angle;
    private Color color;
    private Tank target;
    private Runnable onHit;

    public Bullet(int x, int y, int angle, Color color, Tank target, Runnable onHit) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.speed = 15;
        this.color = color;
        this.target = target;
        this.onHit = onHit;
    }

    public void move() {
    }

    public boolean isOutOfBounds() {
        return true;
    }

    public boolean hasHitTarget() {
        return true;
    }

    public void draw(Graphics g) {
    }
}
