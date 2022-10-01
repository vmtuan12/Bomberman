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
    protected GamePanel gPanel;
    protected BufferedImage img;
    private boolean finish = false;

    public Status(GamePanel gPanel) {
        this.gPanel = gPanel;
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

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    protected void loadImg(String file, int w, int h) {
        try {
            img = ImageIO.read((new File(file)));
            BufferedImage scaledImg = new BufferedImage(w,h,img.getType());
            Graphics2D g2 = scaledImg.createGraphics();
            g2.drawImage(img,0,0,w,h,null);
            img = scaledImg;
            g2.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renderImg(Graphics2D g2) {

    }
}
