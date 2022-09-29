package creator.gameChar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    protected int x, y;
    protected int speed;
    protected Rectangle hitbox;
    protected int hitboxDefaultX;
    protected int hitboxDefaultY;
    protected boolean collided = false;

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

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }

}
