package creator.map;

import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage img;
    private boolean impassable = false;
    private boolean breakable = false;

    public boolean isImpassable() {
        return impassable;
    }

    public void setImpassable(boolean impassable) {
        this.impassable = impassable;
    }

    public boolean isBreakable() {
        return breakable;
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }
}
