package com.company;

import com.company.controllers.interfaces.IUserController;
import com.company.models.User;
import com.company.repositories.UserRepository;
import com.company.repositories.interfaces.IUserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class Game extends JFrame {
    private final IUserRepository repository;
    private Tank player1;
    private Tank player2;
    private GamePanel gamePanel;
    private int player1Score;
    private int player2Score;
    private User user1;
    private User user2;
    private Timer timer;
    private Set<Integer> keysPressed;

    public Game(int fps, int id1,int id2, IUserRepository repository) {
        initializeGame();
        setTitle("com.company.Tank Battle");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        keysPressed = new HashSet<>();
        this.repository = repository;
        User user1 = repository.getUser(id1);
        User user2 = repository.getUser(id2);
        addKeyListener(new GameKeyListener());
    }

    private void initializeGame() {
        player1Score = 0;
        player2Score = 0;
        resetGame();
    }

    private void resetGame() {
        player1 = new Tank(user1.getNickname(), 50, 50, Color.RED);
        player2 = new Tank(user2.getNickname(), 400, 400, Color.BLUE);
        gamePanel = new GamePanel(player1, player2);

        getContentPane().removeAll();
        add(gamePanel);
        revalidate();
        repaint();

        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(1000 / 60, e -> {
            handleMovement();
            gamePanel.repaint();
        });
        timer.start();
    }

    private void showWinnerAndReset(String winner) {
        timer.stop();
        if (winner.equals("Player 1")) {
            player1Score++;
        } else {
            player2Score++;
        }
        JOptionPane.showMessageDialog(this, winner + " wins!", "com.company.Game Over", JOptionPane.INFORMATION_MESSAGE);
        resetGame();
    }

    private void handleMovement() {
        if (keysPressed.contains(KeyEvent.VK_W)) player1.moveForward();
        if (keysPressed.contains(KeyEvent.VK_S)) player1.moveBackward();
        if (keysPressed.contains(KeyEvent.VK_A)) player1.rotateLeft();
        if (keysPressed.contains(KeyEvent.VK_D)) player1.rotateRight();

        if (keysPressed.contains(KeyEvent.VK_UP)) player2.moveForward();
        if (keysPressed.contains(KeyEvent.VK_DOWN)) player2.moveBackward();
        if (keysPressed.contains(KeyEvent.VK_LEFT)) player2.rotateLeft();
        if (keysPressed.contains(KeyEvent.VK_RIGHT)) player2.rotateRight();
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