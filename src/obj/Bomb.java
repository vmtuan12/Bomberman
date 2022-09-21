package obj;

import asset.ImageGetter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Bomb extends Object {

    public Bomb() {
        this.name = "bomb";
        loadImg();

    }

    private void loadImg() {
        ImageGetter imageGetter = new ImageGetter();
        try {
            this.img = ImageIO.read((new File(imageGetter.getBomb())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
