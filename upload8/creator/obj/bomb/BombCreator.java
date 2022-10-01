package creator.obj.bomb;

import asset.ImageGetter;
import creator.obj.Object;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BombCreator extends Object {

    protected int spriteNum = 0;
    protected int spriteCounter = 0;
    protected BufferedImage[] img;
    protected GamePanel gPanel;
    protected ImageGetter imageGetter = new ImageGetter();

    public BombCreator(GamePanel gPanel) {
        this.gPanel = gPanel;
        img = new BufferedImage[4];

    }

    protected void loadImg(String[] file) {
        try {
            for(int i = 0; i < this.img.length; i++) {
                this.img[i] = ImageIO.read((new File(file[i])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renderImg(Graphics2D g2, int x, int y, int spriteSpeed) {
        spriteCounter++;
        if (spriteCounter > spriteSpeed) {
            if (spriteNum == 0) spriteNum = 1;
            else if (spriteNum == 1) spriteNum = 2;
            else if (spriteNum == 2) spriteNum = 3;
            else if (spriteNum == 3) spriteNum = 0;
            spriteCounter = 0;
        }

        int screenX = x - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
        int screenY = y - gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
        if (checkPosition(x, y, gPanel)) {
            g2.drawImage(img[spriteNum], screenX, screenY, gPanel.getActualTileSz(), gPanel.getActualTileSz(), null);
        }
    }

    @Override
    public void action() {

    }
}
