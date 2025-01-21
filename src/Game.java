import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class Game extends JFrame {
    private Tank player1;
    private Tank player2;
    private GamePanel gamePanel;
    private int player1Score;
    private int player2Score;
    private Timer timer;
    private Set<Integer> keysPressed;

    public Game(int fps) {
        initializeGame();
        setTitle("ClashOfTanks");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        keysPressed = new HashSet<>();

        addKeyListener(new GameKeyListener());
    }

    private void initializeGame() {
        player1Score = 0;
        player2Score = 0;
        resetGame();
    }

    private void resetGame() {
    }

    private void showWinnerAndReset(String winner) {
    }

    private void handleMovement() {
    }

    private class GameKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            keysPressed.add(e.getKeyCode());

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                player1.fireBullet(player2, () -> {
                    if (player2.isDestroyed()) {
                        showWinnerAndReset("Player 1");
                    }
                });
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                player2.fireBullet(player1, () -> {
                    if (player1.isDestroyed()) {
                        showWinnerAndReset("Player 2");
                    }
                });
            }
            gamePanel.updateScores(player1Score, player2Score);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keysPressed.remove(e.getKeyCode());
        }

        @Override
        public void keyTyped(KeyEvent e) {}
    }
}
