import javax.swing.*;

public class MyGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game(60));
    }
}