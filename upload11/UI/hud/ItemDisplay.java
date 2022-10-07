package UI.hud;

import UI.Status;
import main.GamePanel;

import java.awt.*;

public class ItemDisplay extends Status {
    public ItemDisplay(GamePanel gPanel, int x, int y) {
        super(gPanel);
        setX(x);
        setY(y);
    }

    @Override
    public void renderImg(Graphics2D g2) {

    }
}
