import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Tank player1;
    private Tank player2;
    private int player1Score;
    private int player2Score;

    public GamePanel(Tank player1, Tank player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1Score = 0;
        this.player2Score = 0;
    }

    public void updateScores(int player1Score, int player2Score) {
        this.player1Score = player1Score;
        this.player2Score = player2Score;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        g.drawString("Score: " + player1Score + " : " + player2Score, 10, 10);
        player1.draw(g);
        player2.draw(g);
        player1.updateBullets(g);
        player2.updateBullets(g);
    }
}