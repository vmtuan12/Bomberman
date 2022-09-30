package UI;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Status {
    private int x;
    private int y;
    private int w;
    private int h;
    protected GamePanel gPanel;
    protected BufferedImage img;

    public Status(GamePanel gPanel, int w, int h) {
        this.gPanel = gPanel;
        this.w = w;
        this.h = h;
    }

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

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void renderImg(Graphics2D g2) {
        int screenX = x - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
        int screenY = y - gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
        g2.drawImage(img, screenX, screenY, w, h, null);
    }

    protected void loadImg(String file) {
        try {
            img = ImageIO.read((new File(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
