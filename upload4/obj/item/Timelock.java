package obj.item;

import obj.Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Timelock extends Object {

    public Timelock() {
        this.name = "timelock";
        loadImg();
    }

    private void loadImg() {
        try {
            img = ImageIO.read((new File("srcImg/item/timelock.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
