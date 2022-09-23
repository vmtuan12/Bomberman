package bomb;

import asset.ImageGetter;
import main.GamePanel;
import obj.Object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bomb extends Object {

    private final int timeCountToExplosion = 120;
    private int timeCount = timeCountToExplosion;

    private boolean downExlosion = false;
    private boolean upExlosion = false;
    private boolean leftExlosion = false;
    private boolean rightExlosion = false;

    public Bomb() {

    }

    public Bomb(GamePanel gPanel) {
        this.img = new BufferedImage[4];
        this.x = calculatePosistionX(gPanel);
        this.y = calculatePosistionY(gPanel);
        System.out.println(this.x + " " + this.y);
        loadImg();
    }

    private void loadImg() {
        ImageGetter imageGetter = new ImageGetter();
        try {
            for(int i = 0; i < this.img.length; i++) {
                this.img[i] = ImageIO.read((new File(imageGetter.getBomb()[i])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setTimeCount(int timeCount) {
        this.timeCount = timeCount;
    }

    private int getTimeCount() {
        return timeCount;
    }

    public void bombControl(GamePanel gPanel) {
//        List<Integer> bombIndexesToBeRemoved = new ArrayList<>();
//        int pos = 0;
        for(int i = 0; i < gPanel.bombList.size(); i++) {
            Bomb b = gPanel.bombList.get(i);
            b.setTimeCount(b.getTimeCount() - 1);
            if(b.getTimeCount() == 0) {
                gPanel.bombList.remove(b);
            }
//            pos++;
        }
    }

    private int calculatePosistionX(GamePanel gPanel) {
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

    private int calculatePosistionY(GamePanel gPanel) {
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

    public void checkObstacle(GamePanel gPanel) {

        int bombCurrentCol = this.x / gPanel.getActualTileSz();
        int bombCurrentRow = this.y / gPanel.getActualTileSz();
        int bombLeftCol = (this.x - gPanel.getActualTileSz()) / gPanel.getActualTileSz();
        int bombRightCol = (this.x + gPanel.getActualTileSz()) / gPanel.getActualTileSz();
        int bombTopRow = (this.y - gPanel.getActualTileSz()) / gPanel.getActualTileSz();
        int bombBotRow = (this.y + gPanel.getActualTileSz()) / gPanel.getActualTileSz();

        int downBlock = gPanel.getTileMng().getTileMap()[bombBotRow][bombCurrentCol];
        int upBlock = gPanel.getTileMng().getTileMap()[bombTopRow][bombCurrentCol];
        int leftBlock = gPanel.getTileMng().getTileMap()[bombCurrentRow][bombLeftCol];
        int rightBlock = gPanel.getTileMng().getTileMap()[bombCurrentCol][bombRightCol];

        if(gPanel.getTileMng().getTiles()[downBlock].isImpassable()) downExlosion = true;
        if(gPanel.getTileMng().getTiles()[upBlock].isImpassable()) upExlosion = true;
        if(gPanel.getTileMng().getTiles()[leftBlock].isImpassable()) leftExlosion = true;
        if(gPanel.getTileMng().getTiles()[rightBlock].isImpassable()) rightExlosion = true;
    }
}
