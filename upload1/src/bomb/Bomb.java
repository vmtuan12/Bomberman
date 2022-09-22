package bomb;

import asset.ImageGetter;
import main.GamePanel;
import obj.Object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bomb extends Object {

    private final int timeCountToExplosion = 120;
    private int timeCount = timeCountToExplosion;

    public Bomb() {
        this.img = new BufferedImage[4];
        loadImg();
    }

    public Bomb(int x, int y) {
        this.img = new BufferedImage[4];
        this.x = x;
        this.y = y;
        loadImg();
    }

    private void loadImg() {
        ImageGetter imageGetter = new ImageGetter();
        try {
            this.img[0] = ImageIO.read((new File(imageGetter.getBomb()[0])));
            this.img[1] = ImageIO.read((new File(imageGetter.getBomb()[1])));
            this.img[2] = ImageIO.read((new File(imageGetter.getBomb()[2])));
            this.img[3] = ImageIO.read((new File(imageGetter.getBomb()[3])));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setTimeCount(int timeCount) {
        this.timeCount = timeCount;
    }

    private int getTimeCount() {
        return timeCount;
    }

    public void bombControl(Graphics2D g2, GamePanel gPanel) {
        List<Integer> bombIndexesToBeRemoved = new ArrayList<>();
        int pos = 0;
        for(int i = 0; i < gPanel.bombList.size(); i++) {
            Bomb b = gPanel.bombList.get(i);
            b.setTimeCount(b.getTimeCount() - 1);
            if(b.getTimeCount() == 0) {
                gPanel.bombList.remove(b);
            }
            pos++;
        }
    }

}
