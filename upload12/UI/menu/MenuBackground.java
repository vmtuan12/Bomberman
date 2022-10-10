package UI.menu;

import UI.Status;
import main.GamePanel;

import java.awt.*;

public class MenuBackground extends Status {
    private final int distance = 3;

    public MenuBackground(GamePanel gPanel) {
        super(gPanel);
        loadImg(imageGetter.getMenuBackground(),1920,624);
        setX(0);
        setY(0);
    }

    public void renderImg(Graphics2D g2) {

        setX(getX() - distance);
        g2.drawImage(img, getX(), getY(), null);
        g2.drawImage(img, getX() + img.getWidth(), getY(), null);
        if(getX() <= -img.getWidth()) setX(0);
    }
}
