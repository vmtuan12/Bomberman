package main;

import UI.ItemTimelock;
import creator.obj.bomb.Bomb;
import creator.obj.bomb.Explosion;
import creator.gameChar.Player;
import creator.obj.ObjCreator;
import creator.map.TileMng;
import event.CollisionDectection;
import event.KeyHandle;

import java.awt.*;

public class GamePanel extends GamePanelValues implements Runnable {

    public GamePanel() {
        key = new KeyHandle(this);
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

    public void setup() {
        objCreator.createObj();
        playMusic(0);
        gameState = gameMenu;
    }

    private void update() {
        if(gameState == gamePlayed) {
            player.update();
        }
        if(gameState == gamePaused) {

        }
    }

    @Override
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g2 = (Graphics2D) g1;
        if(gameState == gameMenu) {

        } else {
            tileMng.renderImg(g2);
            for(int i = 0; i < objCreator.getSz(); i++) {
                if(items[i] != null) items[i].renderImg(g2,this);
            }
            player.renderImg(g2);
            bomb.renderBomb(g2,key);
            collisionDectection.checkExplosionZone(player);
            explosion.renderExplosion(g2);
            player.pickUpItem(collisionDectection.checkCollisionObj(player,true));
        }
        g2.dispose();
    }

    public void playMusic(int i) {
        musicPlayer.setFile(i);
        musicPlayer.play();
        musicPlayer.loop();
    }

    public void playSound(int i) {
        soundPlayer.setFile(i);
        soundPlayer.play();
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
