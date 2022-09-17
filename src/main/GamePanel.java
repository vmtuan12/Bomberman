package main;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int tileSz = 16;
    final int scale = 3;
    final int actualTileSz = tileSz * scale;
    final int maxCol = 17;
    final int maxRow = 13;
    final int screenW = actualTileSz * maxCol; // 816
    final int screenH = actualTileSz * maxRow; // 624

    Thread gameThread;

    KeyHandle key = new KeyHandle();

    private int playerX = 100;
    private int playerY = 100;

    private final int fps = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenW, screenH));
        this.setDoubleBuffered(true); // improve game's rendering performance
        this.setBackground(Color.blue);
        this.addKeyListener(key);
        this.setFocusable(true); // gamepanel focus to receive key input
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double interval = 1000000000/fps;
        double draw = System.nanoTime() + interval;
        while(gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = (draw - System.nanoTime())/1000000;
                if(remainingTime < 0) remainingTime = 0;
                Thread.sleep((long) remainingTime);
                draw += interval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if(key.isUp()) playerY -= 5;
        if(key.isDown()) playerY += 5;
        if(key.isLeft()) playerX -= 5;
        if(key.isRight()) playerX += 5;

    }

    @Override
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g2 = (Graphics2D) g1;
        g2.fillRect(playerX,playerY,200,100);
        g2.setColor(Color.black);
        g2.dispose();
    }
}
