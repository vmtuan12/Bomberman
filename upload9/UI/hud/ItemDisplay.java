package UI.hud;

import UI.Status;
import main.GamePanel;

import java.awt.*;

public class ItemDisplay extends Status {
    public ItemDisplay(GamePanel gPanel, String file, int x, int y) {
        super(gPanel);
        loadImg(file,gPanel.getActualTileSz(),gPanel.getActualTileSz());
        setX(x);
        setY(y);
    }

    @Override
    public void renderImg(Graphics2D g2) {

    }
}
