package bomb;

import asset.ImageGetter;
import main.GamePanel;
import obj.Object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Explosion extends Object {

    private boolean upBlock, downBlock, leftBlock, rightBlock;

    public Explosion() {
        img = new BufferedImage[3];
        loadImg();
    }

    public Explosion(int x, int y) {
        img = new BufferedImage[3];
        this.x = x;
        this.y = y;
        loadImg();
    }

    private void loadImg() {
        ImageGetter imageGetter = new ImageGetter();
        try {
            for(int i = 0; i < this.img.length; i++) {
                this.img[i] = ImageIO.read((new File(imageGetter.getExplosion()[i])));
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
