package UI;

import asset.ImageGetter;
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
    protected BufferedImage[] imgArr;
    protected ImageGetter imageGetter;
    private boolean finish = false;

    public Status(GamePanel gPanel) {
        this.gPanel = gPanel;
        imageGetter = new ImageGetter();
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

    protected void loadImg(String file) {
        try {
            img = ImageIO.read((new File(file)));
            BufferedImage scaledImg = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
            Graphics2D g2 = scaledImg.createGraphics();
            g2.drawImage(img,0,0,img.getWidth(),img.getHeight(),null);
            img = scaledImg;
            g2.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void loadImgArr(String[] file, int w, int h) {
        try {
            BufferedImage scaledImg;
            Graphics2D g2;
            for(int i = 0; i < imgArr.length; i++) {
                imgArr[i] = ImageIO.read((new File(file[i])));
                scaledImg = new BufferedImage(w,h,img.getType());
                g2 = scaledImg.createGraphics();
                g2.drawImage(imgArr[i],0,0,w,h,null);
                imgArr[i] = scaledImg;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void renderImg(Graphics2D g2) {

    }
}
