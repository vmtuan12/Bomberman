package main;

import UI.hud.PlayerLife;
import UI.hud.ScoreDisplay;
import UI.menu.MenuBackground;
import UI.menu.MenuButton;
import creator.gameChar.monster.MonsterCreator;
import creator.gameChar.monster.monsterAI.PathFinder;
import creator.obj.item.ItemCreator;
import creator.obj.bomb.Bomb;
import creator.obj.bomb.Explosion;
import creator.gameChar.player.Player;
import creator.map.TileMng;
import event.CollisionDectection;
import event.KeyHandle;

import java.awt.*;

public class GamePanel extends GamePanelValues implements Runnable {

    public GamePanel() {
        key = new KeyHandle(this);
        tileMng = new TileMng(this);
        player = new Player(key, this,1);
        monsterCreator = new MonsterCreator(this);
        pathFinder = new PathFinder(this);
        itemHUDDisplay = new PlayerLife(this);
        collisionDectection = new CollisionDectection(this);
        itemCreator = new ItemCreator(this);
        bomb = new Bomb(this);
        explosion = new Explosion(this);
        menuBackground = new MenuBackground(this);
        menuButton = new MenuButton(this);
        scoreDisplay = new ScoreDisplay(this);

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

    public void setupNewgameWithoutMusic() {
        items.clear();
        itemCreator.createObj();
        bombList.clear();
        explosionList.clear();
        menuButton.getButton().playerName = new StringBuilder();
        tileMng = new TileMng(this);
        monsterCreator.createMonster(tileMng.getMapCode());
//        gameState = gameMenu;
    }

    public void setupNextMap() {
        items.clear();
        bombList.clear();
        explosionList.clear();
        tileMng = new TileMng(this, tileMng.getMapCode());
        player.setX(player.getStartX());
        player.setY(player.getStartY());
        player.setLife(player.getMaxLife());
        itemCreator.createObj();
        monsterCreator.createMonster(tileMng.getMapCode());
        mapChangingPhaseTimeCount = mapChangingPhaseTime;
        gameState = gameChangeMap;
    }

    private void update() {
        if(gameState == gamePlayed) {
            collisionDectection.checkExplosionZoneForMonster(monsters);
            monsterCreator.update();
            player.update();
            collisionDectection.checkExplosionZoneForPlayer(player);
//            player.pickUpItem(collisionDectection.checkCollisionObj(player,true));
            collisionDectection.checkGameWin();
        }
        if(gameState == gamePaused) {

        }
    }

    @Override
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g2 = (Graphics2D) g1;

        if(gameState != gamePlayed && gameState != gamePaused) {
            menuBackground.renderImg(g2);
            menuButton.renderButton(g2);
        } else {
            tileMng.renderImg(g2);
            for(int i = 0; i < items.size(); i++) {
                items.get(i).renderImg(g2,this);
            }
            itemHUDDisplay.renderImg(g2);
            player.renderImg(g2);
//            for(int i = 0; i < monsters.size(); i++) {
//                monsters.get(i).renderImg(g2);
//                if(monsters.get(i).getLife() <= 0) {
//                    monsters.remove(i);
//                    i--;
//                }
//            }
            monsterCreator.renderImg(g2);
            bomb.renderPlayerBomb(g2,key);
            // boss
            bomb.renderBossBomb(g2);
            explosion.renderExplosion(g2);
            scoreDisplay.renderScore(g2,this.score);
            if(gameState == gamePaused) menuButton.gamePaused(g2);
        }
        g2.dispose();
    }

    public void playMusic(int i) {
        musicPlayer.setFile(i);
        musicPlayer.play();
        musicPlayer.loop();
    }

    public void stopMusic() {
        musicPlayer.stop();
    }

    public void playSound(int i) {
        soundPlayer.setFile(i);
        soundPlayer.play();
    }

}
