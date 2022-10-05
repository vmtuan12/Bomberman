package creator.gameChar.player;

import creator.gameChar.Entity;
import main.GamePanel;
import event.KeyHandle;

import java.awt.*;

public class Player extends Entity {

    private final int boostedSpeedTime = 150;
    private int boostedSpeedTimeCount = 150;
    private boolean unlimitedBomb = false;
    private final int unlimitedBombTime = 120;
    private int unlimitedBombTimeCount = unlimitedBombTime;
    private final KeyHandle key;

    private final int screenX;
    private final int screenY;
    private int charType;

    public Player(KeyHandle key, GamePanel gPanel, int charType) {
        super(gPanel,48,48,8,16,
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
        super.getImage(imageGetter.getChars()[charType], 4);
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
            gPanel.getCollisionDectection().checkMapCollision(this);
            gPanel.getCollisionDectection().checkCollisionBomb(this);
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

    public void pickUpItem(int index) {
        countBoostedSpeedTime();
        if(index != -1) {
            gPanel.getItems()[index].action();
            gPanel.deleteObj(index);
        }
    }

    @Override
    public void renderImg(Graphics2D g2) {
//        Image img = Toolkit.getDefaultToolkit().getImage("Special2.png");
//        g2.drawImage(img, this.x, this.y, gPanel.getActualTileSz(), gPanel.getActualTileSz(), null);

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

        if(blur) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
        }
        if(img != null) g2.drawImage(img, screenX, screenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
    }

    public void countBoostedSpeedTime() {
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
}
