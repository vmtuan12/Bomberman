package creator.gameChar;

import asset.ImageGetter;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Entity {
    protected int x, y;
    protected int speed;
    protected Rectangle hitbox;
    protected int hitboxDefaultX;
    protected int hitboxDefaultY;
    protected boolean collided = false;
    GamePanel gPanel;

    protected BufferedImage[] up;
    protected BufferedImage[] down;
    protected BufferedImage[] left;
    protected BufferedImage[] right;

    protected int direction;
    /* 0 = down
       1 = up
       2 = left
       3 = right
     */
    protected int spriteCounter = 0;
    protected int spriteNum = 0;

    protected int life;
    protected int maxLife;

    public Entity(GamePanel gPanel,int x, int y, int hitboxDefaultX, int hitboxDefaultY,
                  int sprite, int speed, int direction, int life, int maxLife) {
        this.gPanel = gPanel;
        this.x = x;
        this.y = y;
        this.hitboxDefaultX = hitboxDefaultX;
        this.hitboxDefaultY = hitboxDefaultY;
        down = new BufferedImage[sprite];
        up = new BufferedImage[sprite];
        left = new BufferedImage[sprite];
        right = new BufferedImage[sprite];
        this.speed = speed;
        this.direction = direction;
        this.life = life;
        this.maxLife = maxLife;
    }

    protected void getImage(String file, int sprite) {
        try {
            BufferedImage scaledImg;
            Graphics2D g2;
            int[] posX = {0, 16, 32, 48};
            int posY = 0;
            for (int i = 0; i < sprite; i++) {
                down[i] = ImageIO.read(new File(file));
                down[i] = down[i].getSubimage(posX[0], posY, gPanel.getTileSz(), gPanel.getTileSz());
                up[i] = ImageIO.read(new File(file));
                up[i] = up[i].getSubimage(posX[1], posY, gPanel.getTileSz(), gPanel.getTileSz());
                left[i] = ImageIO.read(new File(file));
                left[i] = left[i].getSubimage(posX[2], posY, gPanel.getTileSz(), gPanel.getTileSz());
                right[i] = ImageIO.read(new File(file));
                right[i] = right[i].getSubimage(posX[3], posY, gPanel.getTileSz(), gPanel.getTileSz());

                scaledImg = new BufferedImage(gPanel.getActualTileSz(),gPanel.getActualTileSz(),down[i].getType());
                g2 = scaledImg.createGraphics();
                g2.drawImage(down[i],0,0,gPanel.getActualTileSz(),gPanel.getActualTileSz(),null);
                down[i] = scaledImg;
                scaledImg = new BufferedImage(gPanel.getActualTileSz(),gPanel.getActualTileSz(),up[i].getType());
                g2 = scaledImg.createGraphics();
                g2.drawImage(up[i],0,0,gPanel.getActualTileSz(),gPanel.getActualTileSz(),null);
                up[i] = scaledImg;
                scaledImg = new BufferedImage(gPanel.getActualTileSz(),gPanel.getActualTileSz(),left[i].getType());
                g2 = scaledImg.createGraphics();
                g2.drawImage(left[i],0,0,gPanel.getActualTileSz(),gPanel.getActualTileSz(),null);
                left[i] = scaledImg;
                scaledImg = new BufferedImage(gPanel.getActualTileSz(),gPanel.getActualTileSz(),right[i].getType());
                g2 = scaledImg.createGraphics();
                g2.drawImage(right[i],0,0,gPanel.getActualTileSz(),gPanel.getActualTileSz(),null);
                right[i] = scaledImg;
                g2.dispose();

                posY += gPanel.getTileSz();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public int getHitboxDefaultX() {
        return hitboxDefaultX;
    }

    public int getY() {
        return y;
    }

    public int getHitboxDefaultY() {
        return hitboxDefaultY;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitboxX(int x) {
        this.hitbox.x = x;
    }

    public void setHitboxY(int y) {
        this.hitbox.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    public int getLife() {
        return life;
    }
}
