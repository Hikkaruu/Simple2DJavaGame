package main;

import javax.swing.JFrame;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Gierka");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        gamePanel.config.loadConfig();

        window.pack();
        gamePanel.playMusic(0);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
