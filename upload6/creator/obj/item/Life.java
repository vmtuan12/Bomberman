package creator.obj.item;

import creator.obj.Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Life extends Object {

    public Life() {
        this.name = "Life";
        loadImg();
    }

    private void loadImg() {
        try {
            img = ImageIO.read((new File("srcImg/item/heart.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
