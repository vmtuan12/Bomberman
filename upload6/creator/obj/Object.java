package creator.obj;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {

    protected BufferedImage img;
    protected String name;
    protected boolean impassable = false;
    protected int x, y;
    protected Rectangle hitbox = new Rectangle(0, 0, 48, 48);
    protected int hitboxDefaultX = 0;
    protected int hitboxDefaultY = 0;

    protected int spriteNum = 0;
    protected int spriteCounter = 0;

    public void renderImg(Graphics2D g2, GamePanel gPanel) {

        int screenX = x - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
        int screenY = y - gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
        if (checkPosition(x, y, gPanel)) {
            g2.drawImage(img, screenX, screenY, gPanel.getActualTileSz(), gPanel.getActualTileSz(), null);
        }
    }

    // render image just in the screen, not render outside the screen
    protected boolean checkPosition(int x, int y, GamePanel gPanel) {
        return x + gPanel.getActualTileSz() > gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX() &&
                x - gPanel.getActualTileSz() < gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX() &&
                y + gPanel.getActualTileSz() > gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY() &&
                y - gPanel.getActualTileSz() < gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getHitboxDefaultX() {
        return hitboxDefaultX;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public boolean isImpassable() {
        return impassable;
    }

    public void setImpassable(boolean impassable) {
        this.impassable = impassable;
    }
}
