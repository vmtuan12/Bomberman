package main;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

    public MyFrame() {
        setFrame();
    }

    private void setFrame() {
        GamePanel gamePanel = new GamePanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Bomberman cua tuan dep trai");
        this.setResizable(false);
        this.add(gamePanel);
        this.pack();
        // not specify the location of window -> displayed at center of the screen
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        gamePanel.playMusic(0);
        gamePanel.setGameState(gamePanel.gameMenu);
        gamePanel.startGame();
    }
}
