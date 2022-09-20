package gameChar;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    public BufferedImage[] up;
    public BufferedImage[] down;
    public BufferedImage[] left;
    public BufferedImage[] right;

    public int direction;
    /* 0 = down
       1 = up
       2 = left
       3 = right
     */
    public int spriteCounter = 0;
    public int spriteNum = 0;

}
