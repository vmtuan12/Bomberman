package creator.obj.bomb;

import event.KeyHandle;
import main.GamePanel;

import java.awt.*;
import java.util.List;

public class Bomb extends BombCreator {

    private int timeCountToExplosion = 120;

    private boolean downExlosion = true;
    private boolean upExlosion = true;
    private boolean leftExlosion = true;
    private boolean rightExlosion = true;

    public Bomb(GamePanel gPanel) {
        super(gPanel);
        this.x = calculatePosistionX();
        this.y = calculatePosistionY();
        this.impassable = true;
        System.out.println(this.x + " " + this.y);
        loadImg(imageGetter.getBomb());
    }

    public Bomb(GamePanel gPanel, int x, int y) {
        super(gPanel);
        this.x = x;
        this.y = y;
        this.impassable = true;
        loadImg(imageGetter.getBomb());
    }

    // calculate x coordinate for new bomb
    private int calculatePosistionX() {
        int playerPosXLeft = gPanel.getPlayer().getX();
        if(playerPosXLeft % gPanel.getActualTileSz() == 0) return playerPosXLeft;
        int playerPosXRight = playerPosXLeft + gPanel.getActualTileSz();
        int currentColPosX = (playerPosXRight / gPanel.getActualTileSz()) * gPanel.getActualTileSz();
        int distanceLeft = currentColPosX - playerPosXLeft;
        int distanceRight = playerPosXRight - currentColPosX;
        if(distanceLeft > distanceRight) return currentColPosX - gPanel.getActualTileSz();
        else if(distanceLeft < distanceRight) return currentColPosX;
        else {
            if(gPanel.getPlayer().getDirection() == 2) {
                return currentColPosX - gPanel.getActualTileSz();
            }
            else return currentColPosX;
        }
    }

    // calculate y coordinate for new bomb
    private int calculatePosistionY() {
        int playerPosYTop = gPanel.getPlayer().getY();
        if(playerPosYTop % gPanel.getActualTileSz() == 0) return playerPosYTop;
        int playerPosYBot = playerPosYTop + gPanel.getActualTileSz();
        int currentRowPosY = (playerPosYBot / gPanel.getActualTileSz()) * gPanel.getActualTileSz();
        int distanceTop = currentRowPosY - playerPosYTop;
        int distanceBot = playerPosYBot - currentRowPosY;
        if(distanceTop > distanceBot) return currentRowPosY - gPanel.getActualTileSz();
        else if(distanceTop < distanceBot) return currentRowPosY;
        else {
            if(gPanel.getPlayer().getDirection() == 1) {
                return currentRowPosY - gPanel.getActualTileSz();
            }
            else return currentRowPosY;
        }
    }

    // check obstacle 4 directions
    public void checkObstacle() {

        int bombCurrentCol = this.x / gPanel.getActualTileSz();
        int bombCurrentRow = this.y / gPanel.getActualTileSz();
        int bombLeftCol = (this.x - gPanel.getActualTileSz()) / gPanel.getActualTileSz();
        int bombRightCol = (this.x + gPanel.getActualTileSz()) / gPanel.getActualTileSz();
        int bombTopRow = (this.y - gPanel.getActualTileSz()) / gPanel.getActualTileSz();
        int bombBotRow = (this.y + gPanel.getActualTileSz()) / gPanel.getActualTileSz();

        int downBlock = gPanel.tileMng.getTileMap()[bombBotRow][bombCurrentCol];
        int upBlock = gPanel.tileMng.getTileMap()[bombTopRow][bombCurrentCol];
        int leftBlock = gPanel.tileMng.getTileMap()[bombCurrentRow][bombLeftCol];
        int rightBlock = gPanel.tileMng.getTileMap()[bombCurrentRow][bombRightCol];

        if(gPanel.tileMng.getTiles()[downBlock].isImpassable()) {
            downExlosion = false;
        }
        if(gPanel.tileMng.getTiles()[upBlock].isImpassable()) {
            upExlosion = false;
        }
        if(gPanel.tileMng.getTiles()[leftBlock].isImpassable()) {
            leftExlosion = false;
        }
        if(gPanel.tileMng.getTiles()[rightBlock].isImpassable()) {
            rightExlosion = false;
        }

        Explosion explosion = new Explosion(gPanel,this.x,this.y,downExlosion,upExlosion,leftExlosion,rightExlosion);
        gPanel.explosionList.add(explosion);

        // break box
        if(gPanel.tileMng.getTiles()[downBlock].isBreakable()) {
            gPanel.tileMng.setBlockType(bombBotRow, bombCurrentCol, 0);
        }
        if(gPanel.tileMng.getTiles()[upBlock].isBreakable()) {
            gPanel.tileMng.setBlockType(bombTopRow, bombCurrentCol, 0);
        }
        if(gPanel.tileMng.getTiles()[leftBlock].isBreakable()) {
            gPanel.tileMng.setBlockType(bombCurrentRow, bombLeftCol, 0);
        }
        if(gPanel.tileMng.getTiles()[rightBlock].isBreakable()) {
            gPanel.tileMng.setBlockType(bombCurrentRow, bombRightCol, 0);
        }
        System.out.println(downExlosion + " " + upExlosion + " " + leftExlosion + " " + rightExlosion);
        System.out.println(this.x + " " + this.y);
    }

    // check bomb time
    public void bombControl(List<Bomb> bombList) {
//        List<Integer> bombIndexesToBeRemoved = new ArrayList<>();
//        int pos = 0;
        for(int i = 0; i < bombList.size(); i++) {
//            System.out.println("size: " + gPanel.bombList.size());
            Bomb b = bombList.get(i);
            if(gPanel.getGameState() == gPanel.gamePlayed) b.timeCountToExplosion--;
            if(b.timeCountToExplosion == 0) {
                b.checkObstacle();
                bombList.remove(b);
                i--;
            }
//            pos++;
        }
    }

    public void renderPlayerBomb(Graphics2D g2, KeyHandle key) {
        if(key.isPlaceBomb()) {
            if(gPanel.bombList.size() == 0 || gPanel.getPlayer().isUnlimitedBomb()) {
                gPanel.bombList.add(new Bomb(gPanel));
                key.setPlaceBomb(false);
            }
        }
        bombControl(gPanel.bombList);
        if(gPanel.bombList.size() != 0) {
            for(Bomb b : gPanel.bombList) {
                b.renderImg(g2,b.x,b.y,3);
            }
        }
    }

    public void renderBossBomb(Graphics2D g2) {
        bombControl(gPanel.bossBombList);
        if(gPanel.bossBombList.size() != 0) {
            for(Bomb b : gPanel.bossBombList) {
                b.renderImg(g2,b.x,b.y,3);
            }
        }
    }

    @Override
    public void action() {

    }
}
