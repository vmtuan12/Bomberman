package creator.obj;

import main.GamePanel;

import java.awt.*;

public abstract class Object {

    protected boolean impassable = false;
    protected int x, y;
    protected int screenX, screenY;
    protected Rectangle hitbox = new Rectangle(0, 0, 48, 48);
    protected int hitboxDefaultX = 0;
    protected int hitboxDefaultY = 0;

    protected GamePanel gPanel;

    public Object() {

    }

    // render image just in the screen, not render outside the screen
    protected boolean checkPosition(int x, int y, GamePanel gPanel) {
        return x + gPanel.getActualTileSz() > gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX() &&
                x - gPanel.getActualTileSz() < gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX() &&
                y + gPanel.getActualTileSz() > gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY() &&
                y - gPanel.getActualTileSz() < gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
    }

    public abstract void action();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getHitboxDefaultX() {
        return hitboxDefaultX;
    }

    public int getHitboxDefaultY() {
        return hitboxDefaultY;
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
