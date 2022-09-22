package tile;

import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage img;
    private boolean impassable = false;

    public boolean isImpassable() {
        return impassable;
    }

    public void setImpassable(boolean impassable) {
        this.impassable = impassable;
    }
}