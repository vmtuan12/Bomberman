package bomb;

import asset.ImageGetter;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bomb extends Object {

    private final BufferedImage[] bombImg;
    private final BufferedImage[] explosionImg;

    private int timeCountToExplosion = 120;
    private int explosionTime = 60;

    private boolean downExlosion = true;
    private boolean upExlosion = true;
    private boolean leftExlosion = true;
    private boolean rightExlosion = true;

    GamePanel gPanel;

    public Bomb(GamePanel gPanel) {
        this.gPanel = gPanel;
        this.bombImg = new BufferedImage[4];
        this.explosionImg = new BufferedImage[3];
        this.x = calculatePosistionX();
        this.y = calculatePosistionY();
        System.out.println(this.x + " " + this.y);
        loadImg();
    }

    private void loadImg() {
        ImageGetter imageGetter = new ImageGetter();
        try {
            for(int i = 0; i < this.bombImg.length; i++) {
                this.bombImg[i] = ImageIO.read((new File(imageGetter.getBomb()[i])));
            }
            for(int i = 0; i < this.explosionImg.length; i++) {
                this.explosionImg[i] = ImageIO.read((new File(imageGetter.getExplosion()[i])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renderImg(Graphics2D g2) {
        spriteCounter++;
        if (spriteCounter > 3) {
            if (spriteNum == 0) spriteNum = 1;
            else if (spriteNum == 1) spriteNum = 2;
            else if (spriteNum == 2) spriteNum = 3;
            else if (spriteNum == 3) spriteNum = 0;
            spriteCounter = 0;
        }

        int screenX = x - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
        int screenY = y - gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
        if (checkPosition(x, y, gPanel)) {
            g2.drawImage(bombImg[spriteNum], screenX, screenY, gPanel.getActualTileSz(), gPanel.getActualTileSz(), null);
        }
    }

    public void bombControl() {
//        List<Integer> bombIndexesToBeRemoved = new ArrayList<>();
//        int pos = 0;
        for(int i = 0; i < gPanel.bombList.size(); i++) {
            Bomb b = gPanel.bombList.get(i);
            b.timeCountToExplosion--;
            if(b.timeCountToExplosion == 0) {
                b.checkObstacle();
                gPanel.bombList.remove(b);
            }
//            pos++;
        }
    }

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

    public void checkObstacle() {

        int bombCurrentCol = this.x / gPanel.getActualTileSz();
        int bombCurrentRow = this.y / gPanel.getActualTileSz();
        int bombLeftCol = (this.x - gPanel.getActualTileSz()) / gPanel.getActualTileSz();
        int bombRightCol = (this.x + gPanel.getActualTileSz()) / gPanel.getActualTileSz();
        int bombTopRow = (this.y - gPanel.getActualTileSz()) / gPanel.getActualTileSz();
        int bombBotRow = (this.y + gPanel.getActualTileSz()) / gPanel.getActualTileSz();

        System.out.println(this.x + " " + this.y);

        int downBlock = gPanel.getTileMng().getTileMap()[bombBotRow][bombCurrentCol];
        int upBlock = gPanel.getTileMng().getTileMap()[bombTopRow][bombCurrentCol];
        int leftBlock = gPanel.getTileMng().getTileMap()[bombCurrentRow][bombLeftCol];
        int rightBlock = gPanel.getTileMng().getTileMap()[bombCurrentRow][bombRightCol];

        if(gPanel.getTileMng().getTiles()[downBlock].isImpassable()) downExlosion = false;
        if(gPanel.getTileMng().getTiles()[upBlock].isImpassable()) upExlosion = false;
        if(gPanel.getTileMng().getTiles()[leftBlock].isImpassable()) leftExlosion = false;
        if(gPanel.getTileMng().getTiles()[rightBlock].isImpassable()) rightExlosion = false;
        System.out.println(downExlosion + " " + upExlosion + " " + leftExlosion + " " + rightExlosion);

    }

    private void renderImg(Graphics2D g2, BufferedImage img, int x, int y) {
        int screenX = x - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
        int screenY = y - gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
        if (checkPosition(x, y, gPanel)) {
            g2.drawImage(img, screenX, screenY, gPanel.getActualTileSz(), gPanel.getActualTileSz(), null);
        }
    }

}
