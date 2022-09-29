package creator.obj.bomb;

import asset.ImageGetter;
import main.GamePanel;
import creator.obj.Object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Explosion extends Object {

    private final BufferedImage[] explosionImg;
    private final Rectangle[] deadCross = new Rectangle[2];
    private boolean down, up, left, right;
    private int explosionTime = 60;
    GamePanel gPanel;

    public Explosion(GamePanel gPanel) {
        this.gPanel = gPanel;
        explosionImg = new BufferedImage[4];
        loadImg();
    }

    public Explosion(GamePanel gPanel, int x, int y, boolean down, boolean up, boolean left, boolean right) {
        explosionImg = new BufferedImage[4];
        this.gPanel = gPanel;
        this.x = x;
        this.y = y;
        this.down = down;
        this.up = up;
        this.left = left;
        this.right = right;
        deadCross[0] = new Rectangle(-48,0,144,48);
        deadCross[1] = new Rectangle(0,-48,48,144);
        loadImg();
    }

    private void loadImg() {
        ImageGetter imageGetter = new ImageGetter();
        try {
            for(int i = 0; i < this.explosionImg.length; i++) {
                this.explosionImg[i] = ImageIO.read((new File(imageGetter.getExpl()[i])));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renderImg(Graphics2D g2, int x, int y) {
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 0) spriteNum = 1;
            else if (spriteNum == 1) spriteNum = 2;
            else if (spriteNum == 2) spriteNum = 3;
            else if (spriteNum == 3) spriteNum = 0;
            spriteCounter = 0;
        }

        int screenX = x - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
        int screenY = y - gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
        if (checkPosition(x, y, gPanel)) {
            g2.drawImage(explosionImg[spriteNum], screenX, screenY, gPanel.getActualTileSz(), gPanel.getActualTileSz(), null);
        }
    }

    public void explosionControl() {
//        List<Integer> bombIndexesToBeRemoved = new ArrayList<>();
//        int pos = 0;
        for(int i = 0; i < gPanel.explosionList.size(); i++) {
            Explosion e = gPanel.explosionList.get(i);
            e.explosionTime--;
            if(e.explosionTime == 0) {
                gPanel.explosionList.remove(e);
                i--;
            }
//            pos++;
        }
    }

    public void explode(Graphics2D g2) {
        renderImg(g2,this.x,this.y);
//        updateHitBoxPosition();
        if(down)  {
            renderImg(g2,this.x,this.y + gPanel.getActualTileSz());
        }
        if(up)  {
            renderImg(g2,this.x,this.y - gPanel.getActualTileSz());
        }
        if(left) {
            renderImg(g2, this.x - gPanel.getActualTileSz(), this.y);
        }
        if(right) {
            renderImg(g2, this.x + gPanel.getActualTileSz(), this.y);
        }
    }

    public void renderExplosion(Graphics2D g2) {
        explosionControl();
        if(gPanel.explosionList.size() != 0) {
            for(Explosion e : gPanel.explosionList) {
                e.explode(g2);
            }
        }
    }

    public void updateHitBoxPosition() {
        this.deadCross[0].x += this.x;
        this.deadCross[0].y += this.y;
        this.deadCross[1].x += this.x;
        this.deadCross[1].y += this.y;
    }

    public void resetHitBoxPosition() {
        this.deadCross[0].x = -48;
        this.deadCross[0].y = 0;
        this.deadCross[1].x = 0;
        this.deadCross[1].y = -48;
    }

    public Rectangle[] getDeadCross() {
        return deadCross;
    }

    //    public void checkDown(GamePanel gPanel) {
//        int obstacle1 = gPanel.getTileMng().getTileMap()[bombBotRow][bombLeftCol];
//        int obstacle2 = gPanel.getTileMng().getTileMap()[bombBotRow][bombRightCol];
//        if (gPanel.getTileMng().getTiles()[obstacle1].isImpassable() || gPanel.getTileMng().getTiles()[obstacle2].isImpassable()) {
//
//        }
//    }
}
