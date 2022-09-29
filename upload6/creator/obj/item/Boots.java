package creator.obj.item;

import creator.obj.Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Boots extends Object {

    public Boots() {
        this.name = "Boots";
        loadImg();
    }

    private void loadImg() {
        try {
            img = ImageIO.read((new File("srcImg/item/boots.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
