package main;

import gameChar.Player;
import keyHandle.KeyHandle;
import tile.TileMng;

import java.awt.*;

public class GamePanel extends GamePanelValues implements Runnable {

    private Thread gameThread;

    private final KeyHandle key = new KeyHandle();

    private final TileMng tileMng = new TileMng(this);
    private final Player player = new Player(key, this);

    private final CollisionDectection collisionDectection = new CollisionDectection(this);

    public Player getPlayer() {
        return player;
    }

    public TileMng getTileMng() {
        return tileMng;
    }

    public CollisionDectection getCollisionDectection() {
        return collisionDectection;
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenW, screenH));
        this.setDoubleBuffered(true); // improve game's rendering performance
        this.setBackground(Color.black);
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

    private void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g2 = (Graphics2D) g1;
        tileMng.renderImg(g2);
        player.renderImg(g2);
        g2.dispose();
    }
}
