package obj;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {

    protected BufferedImage img;
    protected String name;
    protected boolean collided = false;
    protected int x, y;

    protected int spriteNum = 0;
    protected int spriteCounter = 0;

    public void renderImg(Graphics2D g2, GamePanel gPanel) {

        int screenX = x - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
        int screenY = y - gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
        if (checkPosition(x, y, gPanel)) {
            g2.drawImage(img, screenX, screenY, gPanel.getActualTileSz(), gPanel.getActualTileSz(), null);
        }
    }

    protected boolean checkPosition(int x, int y, GamePanel gPanel) {
        // render image just in the screen, not render outside the screen
        return x + gPanel.getActualTileSz() > gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX() &&
                x - gPanel.getActualTileSz() < gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX() &&
                y + gPanel.getActualTileSz() > gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY() &&
                y - gPanel.getActualTileSz() < gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
