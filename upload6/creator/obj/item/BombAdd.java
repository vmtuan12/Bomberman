package creator.obj.item;

import creator.obj.Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BombAdd extends Object {

    public BombAdd() {
        loadImg();
    }

    private void loadImg() {
        try {
            img = ImageIO.read((new File("srcImg/item/bomb.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
