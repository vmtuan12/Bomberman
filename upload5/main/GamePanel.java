package main;

import obj.bomb.Bomb;
import obj.bomb.Explosion;
import gameChar.Player;
import obj.ObjCreator;
import tile.TileMng;

import java.awt.*;

public class GamePanel extends GamePanelValues implements Runnable {

    public GamePanel() {
        tileMng = new TileMng(this);
        player = new Player(key, this);
        collisionDectection = new CollisionDectection(this);
        objCreator = new ObjCreator(this);
        bomb = new Bomb(this);
        explosion = new Explosion(this);
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
        double remainingTime;
        while(gameThread != null) {
            update();
            repaint();

            try {
                remainingTime = (draw - System.nanoTime())/1000000;
                if(remainingTime < 0) remainingTime = 0;
                Thread.sleep((long) remainingTime);
                draw += interval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void createObj() {
        objCreator.createObj();
    }

    private void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g2 = (Graphics2D) g1;
        tileMng.renderImg(g2);
        for(int i = 0; i < objCreator.getSz(); i++) {
            if(objects[i] != null) objects[i].renderImg(g2,this);
        }
        player.renderImg(g2);
        //renderBomb(g2);
        bomb.renderBomb(g2,key);
        explosion.renderExplosion(g2);
        g2.dispose();
    }

    /*
    private void renderBomb(Graphics2D g2) {
        if(key.isPlaceBomb()) {
            bombList.add(new Bomb(this));
            key.setPlaceBomb(false);
        }
        bomb.bombControl();
        if(bombList.size() != 0) {
            for(Bomb b : bombList) {
                b.renderImg(g2);
            }
        }
        explosion.explosionControl();
        if(explosionList.size() != 0) {
            for(Explosion e : explosionList) {
                e.explode(g2);
            }
        }
    }
     */
}
