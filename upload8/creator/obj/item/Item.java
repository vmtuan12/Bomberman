package creator.obj.item;

import creator.obj.Object;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Item extends Object {

    protected BufferedImage img;
    protected int id;
    public Item(int id, String file, GamePanel gPanel) {
        this.gPanel = gPanel;
        this.id = id;
        loadImg(file);
    }

    private void loadImg(String file) {
        try {
            BufferedImage scaledImg;
            Graphics2D g2;
            img = ImageIO.read((new File(file)));
            scaledImg = new BufferedImage(gPanel.getActualTileSz(),gPanel.getActualTileSz(),img.getType());
            g2 = scaledImg.createGraphics();
            g2.drawImage(img,0,0,gPanel.getActualTileSz(),gPanel.getActualTileSz(),null);
            img = scaledImg;
            g2.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renderImg(Graphics2D g2, GamePanel gPanel) {

        int screenX = x - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
        int screenY = y - gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
        if (checkPosition(x, y, gPanel)) {
            g2.drawImage(img, screenX, screenY, gPanel.getActualTileSz(), gPanel.getActualTileSz(), null);
        }
    }

    @Override
    public void action() {

    }

    public int getId() {
        return id;
    }
}
