package bomb;

import asset.ImageGetter;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Explosion extends Object {

    private final BufferedImage[] explosionImg;

    public Explosion() {
        explosionImg = new BufferedImage[3];
        loadImg();
    }

    public Explosion(int x, int y) {
        explosionImg = new BufferedImage[3];
        this.x = x;
        this.y = y;
        loadImg();
    }

    private void loadImg() {
        ImageGetter imageGetter = new ImageGetter();
        try {
            for(int i = 0; i < this.explosionImg.length; i++) {
                this.explosionImg[i] = ImageIO.read((new File(imageGetter.getExplosion()[i])));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    //    public void checkDown(GamePanel gPanel) {
//        int obstacle1 = gPanel.getTileMng().getTileMap()[bombBotRow][bombLeftCol];
//        int obstacle2 = gPanel.getTileMng().getTileMap()[bombBotRow][bombRightCol];
//        if (gPanel.getTileMng().getTiles()[obstacle1].isImpassable() || gPanel.getTileMng().getTiles()[obstacle2].isImpassable()) {
//
//        }
//    }
}
