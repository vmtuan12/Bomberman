package creator.gameChar.player;

import creator.gameChar.Entity;
import main.GamePanel;
import event.KeyHandle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    private final int boostedSpeedTime = 150;
    private int boostedSpeedTimeCount = 150;
    private boolean unlimitedBomb = false;
    private final int unlimitedBombTime = 120;
    private int unlimitedBombTimeCount = unlimitedBombTime;
    private final KeyHandle key;

    private final int screenX;
    private final int screenY;
    private final int startX = 11*48;
    private final int startY = 19*48;
    private int charType;
    private BufferedImage deadImg;

    public Player(KeyHandle key, GamePanel gPanel, int charType) {
        super(gPanel,692,672,8,16,
                7,4, 0,3,3);
        hitbox = new Rectangle(hitboxDefaultX, hitboxDefaultY,
                gPanel.getActualTileSz() - 16, gPanel.getActualTileSz() - 20);
        this.key = key;
        this.charType = charType;
        screenX = gPanel.getScreenW() / 2 - gPanel.getActualTileSz() / 2;
        screenY = gPanel.getScreenH() / 2 - gPanel.getActualTileSz() / 2;
        blurTime = 120;
        blurTimeCount = blurTime;
        getImage();
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    private void getImage() {
        super.getImage(imageGetter.getChars()[charType], 4, gPanel.getActualTileSz());
        try {
            deadImg = ImageIO.read(new File(imageGetter.getCharDead()[charType]));
            BufferedImage scaledImg = new BufferedImage(gPanel.getActualTileSz(),gPanel.getActualTileSz()
                                                        ,deadImg.getType());
            Graphics2D g2 = scaledImg.createGraphics();
            g2.drawImage(deadImg,0,0,gPanel.getActualTileSz(),gPanel.getActualTileSz(),null);
            deadImg = scaledImg;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // check key event, set direction, update sprites
    @Override
    public void update() {

        if (key.isUp() || key.isDown() || key.isRight() || key.isLeft()) {

            if (key.isUp()) {
                direction = 1;
            } else if (key.isDown()) {
                direction = 0;
            } else if (key.isLeft()) {
                direction = 2;
            } else if (key.isRight()) {
                direction = 3;
            }
            System.out.println(screenX + " " + screenY + " " + x + " " + y);

            collided = false;
//            gPanel.getCollisionDectection().checkMapCollision(this);
            gPanel.getCollisionDectection().checkCollisionBomb(this,gPanel.bombList);
            gPanel.getCollisionDectection().checkCollisionBomb(this,gPanel.bossBombList);
            if(gPanel.getCollisionDectection().checkCollisionMonster(this,gPanel.getMonsters()) != -1) {
                if(!blur) {
                    life--;
                    blur = true;
                }
            }

            if (!collided) {
                if (direction == 0) y += speed;
                else if (direction == 1) y -= speed;
                else if (direction == 2) x -= speed;
                else if (direction == 3) x += speed;
            }

            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 0) spriteNum = 1;
                else if (spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum = 3;
                else if (spriteNum == 3) spriteNum = 0;
                spriteCounter = 0;
            }
        }

        pickUpItem(gPanel.getCollisionDectection().checkCollisionObj(this,true));
        if (blur) {
            blurTimeCount--;
            if(blurTimeCount == 0) {
                blur = false;
                blurTimeCount = blurTime;
            }
        }
        if(unlimitedBomb) {
            unlimitedBombTimeCount--;
            if(unlimitedBombTimeCount == 0) {
                unlimitedBomb = false;
                unlimitedBombTimeCount = unlimitedBombTime;
            }
        }
    }

    private void pickUpItem(int index) {
        countBoostedSpeedTime();
        if(index != -1) {
//            gPanel.getItems()[index].action();
            gPanel.getItems().get(index).action();
//            gPanel.deleteObj(index);
            gPanel.getItems().remove(index);
        }
    }

    @Override
    public void renderImg(Graphics2D g2) {
//        Image img = Toolkit.getDefaultToolkit().getImage("Special2.png");
//        g2.drawImage(img, this.x, this.y, gPanel.getActualTileSz(), gPanel.getActualTileSz(), null);

        if(life <= 0) {
            img = deadImg;
        } else {
            if (direction == 0) {
                if (key.isDown()) img = down[spriteNum];
                else img = down[0];
            } else if (direction == 1) {
                if (key.isUp()) img = up[spriteNum];
                else img = up[0];
            } else if (direction == 2) {
                if (key.isLeft()) img = left[spriteNum];
                else img = left[0];
            } else {
                if (key.isRight()) img = right[spriteNum];
                else img = right[0];
            }
        }

        if(blur) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
        }
        if(img != null) g2.drawImage(img, screenX, screenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
    }

    private void countBoostedSpeedTime() {
        if (speed == 7) {
            boostedSpeedTimeCount -= 1;
            if (boostedSpeedTimeCount == 0) {
                speed = 4;
                boostedSpeedTimeCount = boostedSpeedTime;
            }
        }
    }

    public void setUnlimitedBomb(boolean unlimitedBomb) {
        this.unlimitedBomb = unlimitedBomb;
    }

    public boolean isUnlimitedBomb() {
        return unlimitedBomb;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
}
